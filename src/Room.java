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

    public void setNumberObstacles(int numberObstacles) {
        this.numberObstacles = numberObstacles;
    }

    public Room(int length, int width, int height, int numberCameras, int numberObstacles) {
        //test git pull
        //test git pull again
        int index = 0;
        setWidth(width);
        setLength(length);
        setHeight(height);
        setNumberCameras(numberCameras);
        setNumberObstacles(numberObstacles);

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

    public void camerasPlacement(double deepVision, double widthVision,double lengthVision, float angle) {
        int x;
        int y;

        Scanner sn = new Scanner(System.in);
        numberCameras = sn.nextInt();

        for (int i = 0; i < numberCameras; ++i) {
            x = sn.nextInt();
            y = sn.nextInt();

            cameras.add(new Camera(x, y, getHeight(), deepVision, widthVision, lengthVision,angle, this));
        }
    }

    public void obstaclesPlacement(Coordinate bottom1, Coordinate bottom2, Coordinate bottom3, Coordinate bottom4, int height)
    {
        Coordinate top1 = Coordinate.getCoordinate(bottom1.getX(), bottom1.getY(), bottom1.getZ() + height, getCoordinates());
        Coordinate top2 = Coordinate.getCoordinate(bottom2.getX(), bottom2.getY(), bottom2.getZ() + height, getCoordinates());
        Coordinate top3 = Coordinate.getCoordinate(bottom3.getX(), bottom3.getY(), bottom3.getZ() + height, getCoordinates());
        Coordinate top4 = Coordinate.getCoordinate(bottom4.getX(), bottom4.getY(), bottom4.getZ() + height, getCoordinates());

//        obstacles.add(new Obstacle());
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
        //thêm camera trên tường tại những vị trí trống
        for (int i = 0; i < getWidth(); ++i)
        {
            if(Camera.findCamera(i, 0, cameras) == null)
            {
                Camera camera = new Camera(i, 0, getHeight(), cameras.get(0).getDeepVision(),
                        cameras.get(0).getWidthVision(),cameras.get(0).getLengthVision(), cameras.get(0).getAngle(), this);
                cameras.add(camera);
                cameras2.add(camera);
            }

            if(Camera.findCamera(i, getLength(), cameras) == null)
            {
                Camera camera = new Camera(i, getLength(), getHeight(), cameras.get(0).getDeepVision(),
                        cameras.get(0).getWidthVision(),cameras.get(0).getLengthVision(), cameras.get(0).getAngle(), this);
                cameras.add(camera);
                cameras2.add(camera);
            }
        }

        for (int i = 0; i < getLength(); ++i)
        {
            if(Camera.findCamera(0, i, cameras) == null)
            {
                Camera camera = new Camera(0, i, getHeight(), cameras.get(0).getDeepVision(),
                        cameras.get(0).getWidthVision(), cameras.get(0).getLengthVision(),cameras.get(0).getAngle(), this);
                cameras.add(camera);
                cameras2.add(camera);
            }

            if(Camera.findCamera(getWidth(), i, cameras) == null)
            {
                Camera camera = new Camera(getWidth(), i, getHeight(), cameras.get(0).getDeepVision(),
                        cameras.get(0).getWidthVision(), cameras.get(0).getLengthVision(),cameras.get(0).getAngle(), this);
                cameras.add(camera);
                cameras2.add(camera);
            }
        }

        caculateShadow();   //

        //đã có length * width camera => loại bớt để phù hợp vs đề bài
        ArrayList<Coordinate> copy_coordiantes = new ArrayList<>(Arrays.asList(coordinates));

        if(!reset)
        {
            for (Coordinate coordinate : copy_coordiantes)
            {
                int camera_id = coordinate.getSeenByCameras_().indexOf(true);
                if(Camera.findCamera(camera_id, cameras2) == null)
                    copy_coordiantes.remove(coordinate);
            }
        }

        int result = 0;
//
//        //Thuật toán nhóm theo từng nhóm sao cho số camera = limit
//        //Tính tổng số tọa độ mà mỗi camera nhìn được
//        List<Integer> sum = new ArrayList<Integer>();
//        for (int i = 0; i < coordinates.length; ++i)
//        {
//            int count = 0;
//            for (Coordinate coordinate : copy_coordiantes)
//            {
//                if(coordinate.getSeenByCameras_().set(i, true)) {
//                    ++count;
//                }
//            }
//            sum.add(count);
//        }
//
//        int group = 0;
//
//        //cần viết lại tránh remove trong list
//        while(group <= limit && copy_coordiantes.size() != 0 && Collections.max(sum) != 0)
//        {
//            //Tìm được nhóm mới
//            ++group;
//
//            for (Coordinate coordinate : copy_coordiantes)
//            {
//                if(coordinate.getSeenByCameras_().get(sum.indexOf(Collections.max(sum))))
//                {
//                    //sửa lại dãy sum
//                    for (int i = 0; i < coordinate.getSeenByCameras_().size(); ++i)
//                    {
//                        if(coordinate.getSeenByCameras_().get(i))
//                        {
//                            int tmp = sum.get(i);
//                            --tmp;
//                            sum.set(i, tmp);
//                        }
//                    }
//
//                    //Nếu ID của camera nằm trong nhóm cameras2 => đây là camera mới thêm vào
//                    if (Camera.findCamera
//                            (sum.indexOf(Collections.max(sum)) + 1, cameras2) != null)
//                    {
//                        System.out.println("Lap them camera o toa do: x = " + coordinate.getX()
//                                + ", y = " + coordinate.getY());
//                    }
//                    copy_coordiantes.remove(coordinate);
//                }
//            }
//        }
//
//        //Xóa những camera đã thêm trong hàm này
//        for (Camera camera : cameras)
//        {
//            for (Camera camera1 : cameras2)
//            {
//                if(camera.getID() == camera1.getID())
//                {
//                    cameras.remove(camera);
//                    break;
//                }
//            }
//        }

        return result;
    }
    public double RateOfSighting()
    {
        ArrayList<Coordinate> insideObstacle = new ArrayList<>();
        int count = 0;
        for(Coordinate coordinate: coordinates)
        {
            for(Obstacle obstacle: obstacles)
            {
                if(coordinate.getX()>=obstacle.getBottom1().get(0)&&coordinate.getX()<=obstacle.getBottom2().get(0)
                &&coordinate.getY()>=obstacle.getBottom2().get(1)&&coordinate.getY()<=obstacle.getBottom3().get(1)
                &&coordinate.getZ()>=obstacle.getBottom1().get(2)&&coordinate.getZ()<=obstacle.getTop1().get(2))
                {
                    insideObstacle.add(coordinate);
                    break;
                }
                else
                {
                    coordinate.beSeen(cameras,obstacles);
                    if(coordinate.isCanSee())
                        count++;
                }
            }
        }
        int qtyCoordinateOutside = length*width*height - insideObstacle.size();
        return (double)count/qtyCoordinateOutside;
    }
    public void addCamera(Camera camera)
    {
        if(this.cameras==null)
        {
            ArrayList<Camera> temp = new ArrayList<Camera>();
            this.cameras=temp;
        }
        this.cameras.add(camera);
    }
    public void addObstacle(Obstacle obstacle)
    {
        if(this.obstacles==null)
        {
            ArrayList<Obstacle> temp = new ArrayList<Obstacle>();
            this.obstacles=temp;
        }
        this.obstacles.add(obstacle);
    }
}
