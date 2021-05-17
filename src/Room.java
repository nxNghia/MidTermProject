import java.util.*;

public class Room {
    private int length;
    private int width;
    private int height;
    private int numberCameras;
    private int numberObstacles;

    public Coordinate[] getCoordinates() {
        return coordinates;
    }

    private final Coordinate[] coordinates;
    private ArrayList<Camera> cameras;
    private ArrayList<Obstacle> obstacles;

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getNumberCameras(){
        return numberCameras;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setNumberCameras(int numberCameras) {
        this.numberCameras = numberCameras;
    }

    public Room(int length, int width, int height, int numberCameras) {
        int index = 0;
        setWidth(width);
        setLength(length);
        setHeight(height);
        setNumberCameras(numberCameras);

        coordinates = new Coordinate[length * width * height];

        for (int i = 0; i < length; ++i)
        {
            for (int j = 0; j < width; ++j)
            {
                for (int t = 0; t < height; ++t)
                {
                    coordinates[index++] = new Coordinate(i, j, t, length, width);
                }
            }
        }
    }

    public void camerasPlacement(float deepVision, float widthVision, float angle) {
        int x;
        int y;

        Scanner sn = new Scanner(System.in);
        numberCameras = sn.nextInt();

        for (int i = 0; i < numberCameras; ++i) {
            x = sn.nextInt();
            y = sn.nextInt();

            cameras.add(new Camera(x, y, getHeight(), deepVision, widthVision, angle, this));
        }
    }

    public void caculateShadow()
    {
        for (Coordinate coordinate : coordinates)
        {
            if(cameras!=null)
            coordinate.beSeen(cameras, obstacles);
        }
    }

    //limit: số lượng camera tối đa được đặt
    //reset: true nếu cần xóa đi tất cả camera và đặt từ đầu; false nếu tính toán dựa trên camera hiện tại
    public int caculateCamera(int limit, boolean reset)
    {
        ArrayList<Camera> cameras2 = new ArrayList<>(); //dùng để đánh dấu những camera sẽ thêm vào chỉ trong hàm này
        for (int i = 0; i < getLength(); ++i)
        {
            for (int j = 0; j < getWidth(); ++i)
            {
                if(Camera.findCamera(i, j, cameras) == null)
                {
                    Camera tmp = new Camera(i, j, getHeight(), cameras.get(0).getDeepVision(), cameras.get(0).getWidthVision(),
                            cameras.get(0).getAngle(), this);
                    cameras2.add(tmp);
                    cameras.add(tmp);
                }
            }
        }

        caculateShadow();   //

        //đã có length * width camera => loại bớt để phù hợp vs đề bài
        ArrayList<Coordinate> copy_coordiantes = new ArrayList<>(Arrays.asList(coordinates));

        if(!reset)
        {
            for (Coordinate coordinate : copy_coordiantes)
            {
                int camera_id = coordinate.getSeenByCameras().indexOf("1");
                if(Camera.findCamera(camera_id, cameras2) == null)
                    copy_coordiantes.remove(coordinate);
            }
        }

        int result = 0;

        //Thuật toán nhóm theo từng nhóm sao cho số camera = limit
        //Tính tổng số tọa độ mà mỗi camera nhìn được
        List<Integer> sum = new ArrayList<Integer>();
        for (int i = 0; i < coordinates.length; ++i)
            sum.add(0);

        int group = 0;
        int max = 0;

        while(group <= limit && max != copy_coordiantes.size())
        {
            for (Coordinate coordinate : copy_coordiantes) {
                for (int j = 0; j < coordinate.getSeenByCameras().length(); ++j) {
                    int tmp = sum.get(j);
                    tmp += coordinate.getSeenByCameras().charAt(j);
                    sum.set(j, tmp);
                }
            }

            //Tìm được nhóm mới
            if(Collections.max(sum) != 0)
            {
                ++group;

                copy_coordiantes.removeIf
                        (coordinate -> coordinate.getSeenByCameras().charAt(sum.indexOf(Collections.max(sum))) == '1');

                for (Coordinate coordinate : copy_coordiantes)
                {
                    if(coordinate.getSeenByCameras().charAt(sum.indexOf(Collections.max(sum))) == '1')
                    {
                        copy_coordiantes.remove(coordinate);

                        //nếu camera có ID bằng số thứ tự của cột max của dãy sum => camera cần phải đặt thêm
                        if (Camera.findCamera
                                (sum.indexOf(Collections.max(sum)) + 1, cameras2) != null)
                        {
                            System.out.println("Lap them camera o toa do: x = " + coordinate.getX()
                                    + ", y = " + coordinate.getY());
                        }
                    }
                }
            }else{
                break;
            }
        }

        //Xóa những camera đã thêm trong hàm này
        for (Camera camera : cameras)
        {
            for (Camera camera1 : cameras2)
            {
                if(camera.getID() == camera1.getID())
                {
                    cameras.remove(camera);
                    break;
                }
            }
        }

        return result;
    }
}
