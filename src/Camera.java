import java.util.ArrayList;

public class Camera {
    private static int CameraNumber;    //số lượng camera
    private int id;
    private int x;
    private int y;
    private Coordinate position;
    private double deepVision;   //tầm xa của camera
    private double widthVision;  //độ rộng của vision

    private boolean inWall;// =  true thì camera trên tường,false thì trên trần nhà.

    private float angle;

    public Camera(int x, int y, int z, double deepVision, double widthVision, float angle, Room r) {
        ++CameraNumber;
        setX(x);
        setY(y);
        setDeepVision(deepVision);
        setID(CameraNumber);
        Coordinate camPosition = Coordinate.getCoordinate(x, y, z, r.getCoordinates());
        if(camPosition != null)
        {
            setPosition(camPosition);
        }
    }


    public boolean isInWall() {
        return inWall;
    }

    public void setInWall(boolean inWall) {
        this.inWall = inWall;
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
}
