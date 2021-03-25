import java.util.ArrayList;

public class Room {
    private int length;
    private int width;
    private int height;
    private int numberCameras;
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

    public void camerasPlacement(int x, int y, float deepVision, float widthVision, float angle)
    {
        if(Camera.findCamera(x, y, cameras) == null)
        {
            cameras.add(new Camera(x, y, deepVision, widthVision, angle));
        }
    }

    public void caculateShadow()
    {
        for (Coordinate coordinate : coordinates)
        {
            coordinate.beSeen(cameras, obstacles);
        }
    }

    public int caculateCamera(int limit)
    {
        ArrayList<Camera> cameras2 = new ArrayList<>(); //dùng để đánh dấu những camera sẽ thêm vào chỉ trong hàm này
        for (int i = 0; i < getLength(); ++i)
        {
            for (int j = 0; j < getWidth(); ++i)
            {
                if(Camera.findCamera(i, j, cameras) == null)
                {
                    Camera tmp = new Camera(i, j, cameras.get(0).getDeepVision(), cameras.get(0).getWidthVision(),
                            cameras.get(0).getAngle());
                    cameras2.add(tmp);
                    cameras.add(tmp);
                }
            }
        }

        caculateShadow();

        //đã có length * width camera => loại bớt để phù hợp vs đề bài

        for (int i = 0; i < coordinates.length - 1; ++i)
        {
            for (int j = i + 1; j < coordinates.length; ++j)
            {
                if(coordinates[i].getSeenByCameras().compareTo(coordinates[j].getSeenByCameras()) < 0)
                {
                    Coordinate.swap(coordinates[i], coordinates[j]);
                }
            }
        }

        int result = 0;

        //Thuật toán nhóm theo từng nhóm sao cho số camera = limit

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
