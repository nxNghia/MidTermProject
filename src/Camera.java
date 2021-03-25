import java.util.ArrayList;

public class Camera {
    private static int CameraNumber;    //số lượng camera
    private int id;
    private int x;
    private int y;
    private float deepVision;   //tầm xa của camera
    private float widthVision;  //độ rộng của vision

    private float angle;
    public Camera(int x, int y, float deepVision, float widthVision, float angle) {
        ++CameraNumber;
        setX(x);
        setY(y);
        setDeepVision(deepVision);
        setID(CameraNumber);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getDeepVision() { return deepVision; }

    public int getID() {
        return id;
    }

    public float getWidthVision() { return widthVision; }

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

    public void setDeepVision(float deepVision) {
        this.deepVision = deepVision;
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
}
