import java.util.ArrayList;
import java.util.List;

public class Coordinate {
    private int x;
    private int y;
    private int z;
    private StringBuilder seenByCameras;    //cho biết điểm này có thể nhìn thấy bởi camera nào

    public Coordinate(int x, int y, int z, int length, int width) {
        setX(x);
        setY(y);
        setZ(z);

        seenByCameras = new StringBuilder(length * width);
        //ban đầu set tất cả phần tử của chuỗi bằng 0
        for (int i = 0; i < seenByCameras.length(); ++i)
        {
            seenByCameras.setCharAt(i, '0');
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setSeenByCameras(StringBuilder seenByCameras) {
        this.seenByCameras = seenByCameras;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public StringBuilder getSeenByCameras() {
        return seenByCameras;
    }

    //3 số đầu là tọa độ vector, 3 số sau là điểm mà vector đi qua
    //x = a' * t + a
    //y = b' * t + b
    //z = c' * t + c
    public ArrayList<Integer> getVector(Coordinate destination)
    {
        ArrayList<Integer> result = new ArrayList<>();

        result.add(destination.getX() - getX());    //a'
        result.add(destination.getY() - getY());    //b'
        result.add(destination.getZ() - getZ());    //c'
        result.add(destination.getX());             //a
        result.add(destination.getY());             //b
        result.add(destination.getZ());             //c

        return result;
    }

    //Lấy bình phương khoảng cách từ this đến destination
    public int getDistances(Coordinate destination)
    {
        int _x = (destination.getX() - getX()) * (destination.getX() - getX());
        int _y = (destination.getY() - getY()) * (destination.getY() - getY());
        int _z = (destination.getZ() - getZ()) * (destination.getZ() - getZ());

        return _x + _y + _z;
    }

    //hàm swap giá trị của hai biến Coordinate
    public static void swap(Coordinate c1, Coordinate c2)
    {
        int tmp = c1.getX();
        c1.setX(c2.getX());
        c2.setX(tmp);

        tmp = c1.getY();
        c1.setY(c2.getY());
        c2.setY(tmp);

        tmp = c1.getZ();
        c1.setZ(c2.getZ());
        c2.setZ(tmp);

        StringBuilder tmp2 = c1.getSeenByCameras();
        c1.setSeenByCameras(c2.getSeenByCameras());
        c2.setSeenByCameras(tmp2);
    }

    //sau hàm này xác định được chuỗi seenByCamera của từng coordinate
    public void beSeen(ArrayList<Camera> cameras, ArrayList<Obstacle> obstacles)
    {
        for (Camera camera : cameras)
        {
            for (Obstacle obstacle : obstacles)
            {
                if(obstacle.getTop1().getZ() > getZ())  //chỉ xét những điểm nào nằm dưới chiều cao của vật
                {
                    //do something

                    //nếu cắt => chỉnh lại seenByCameras của this => break;
                }
            }
        }
    }
}
