import java.util.ArrayList;

public class Camera {
    private static int CameraNumber;    //số lượng camera
    private int id;
    private int x;
    private int y;

    private int z;

    private Coordinate position;
    private double deepVision;   //tầm xa của camera
    private double widthVision;  //độ rộng của vision
    private double lengthVision;  // độ dài của Vision

    private int wall;

    //= 0 thì ở trần, = 1 thì ở tường x = 0, = 2 thì ở tường y = 0, = 3 thì ở tường x = length,= 4 thì ở tường y = width
    private float angle;

    public Camera(int x, int y, int z, double deepVision, double widthVision,double lengthVision, float angle, Room r) {
        ++CameraNumber;
        setX(x);
        setY(y);
        setZ(z);
        setDeepVision(deepVision);
        setLengthVision(lengthVision);
        setID(CameraNumber);
        Coordinate camPosition = Coordinate.getCoordinate(x, y, z, r.getCoordinates());
        if(camPosition != null)
        {
            setPosition(camPosition);
        }
        if(z==r.getHeight())
            setWall(0);
        else if(x==0)
            setWall(1);
        else if(x==r.getLength())
            setWall(3);
        else if(y==0)
            setWall(2);
        else if(y==r.getWidth())
            setWall(4);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getDeepVision() { return deepVision; }

    public int getID() {
        return id;
    }

    public double getWidthVision() { return widthVision; }

    public float getAngle() { return angle; }

    public void setID(int id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDeepVision(double deepVision) {
        this.deepVision = deepVision;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public static Camera findCamera(int x, int y, ArrayList<Camera> cameras)    //tìm camera theo x, y trong cameras
    {
        for (Camera c : cameras)
        {
            if(c.getX() == x && c.getY() == y)
            {
                return c;
            }
        }
        return null;
    }
    public static Camera findCamera(int id, ArrayList<Camera> cameras)
    {
        for (Camera c : cameras)
        {
            if(c.getID() == id)
            {
                return c;
            }
        }
        return null;
    }
    public double getLengthVision() {
        return lengthVision;
    }
    public int getWall() {
        return wall;
    }

    public void setWall(int wall) {
        this.wall = wall;
    }

    public void setLengthVision(double lengthVision) {
        this.lengthVision = lengthVision;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
