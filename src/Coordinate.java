import java.util.ArrayList;

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
    public double getDistances(Coordinate destination)
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

    public double angleXOY(double xX, double yX, double zX, double xY, double yY, double zY, double xO, double yO, double zO)
    {
        double xVectorOX = xX-xO;
        double yVectorOX = yX-yO;
        double zVectorOX = zX-zO;
        double xVectorOY = xY-xO;
        double yVectorOY = yY-yO;
        double zVectorOY = zY-zO;
        double cosXOY = (xVectorOX*xVectorOY+yVectorOX*yVectorOY+zVectorOX*zVectorOY)
                /(Math.sqrt(xVectorOX*xVectorOX+yVectorOX*yVectorOX+zVectorOX*zVectorOX)
                *Math.sqrt(xVectorOY*xVectorOY+yVectorOY*yVectorOY+zVectorOY*zVectorOY));
        double angle = Math.acos(cosXOY);
        return angle;
    }
    public int throughFace(ArrayList<Integer> line, ArrayList<Integer> surface)
    {
        double t = (double) (surface.get(3)-surface.get(0)*line.get(0)-surface.get(1)*line.get(1)
                -surface.get(2)*line.get(2)/(surface.get(0)*line.get(3)+surface.get(1)*line.get(4)
                +surface.get(2)*line.get(5)));
        double xO = t*line.get(3)+line.get(0);
        double yO = t*line.get(4)+line.get(1);
        double zO = t*line.get(5)+line.get(2);
        double AOB = angleXOY(surface.get(4),surface.get(5),surface.get(6),surface.get(7),surface.get(8),surface.get(9)
                ,xO,yO,zO);
        double BOC = angleXOY(surface.get(7),surface.get(8),surface.get(9),surface.get(10),surface.get(11),surface.get(12)
                ,xO,yO,zO);
        double COD = angleXOY(surface.get(10),surface.get(11),surface.get(12),surface.get(13),surface.get(14),surface.get(15)
                ,xO,yO,zO);
        double DOA = angleXOY(surface.get(13),surface.get(14),surface.get(15),surface.get(4),surface.get(5),surface.get(6)
                ,xO,yO,zO);
        double totalAngle = Math.toDegrees(AOB + BOC + COD + DOA);
        if(totalAngle >=359.99999 && totalAngle <=360.00001)
            return 1;
        return 0;
    }
//    public int cutFace(ArrayList<Integer> line, ArrayList<Integer> surface)
//    {
//        double k = (double) (surface.get(3)-surface.get(0)*line.get(0)-surface.get(1)*line.get(1)
//                -surface.get(2)*line.get(2)/(surface.get(0)*line.get(3)+surface.get(1)*line.get(4)
//                +surface.get(2)*line.get(5)));
//        double x = k*line.get(3)+line.get(0);
//        double y = k*line.get(4)+line.get(1);
//        double z = k*line.get(5)+line.get(2);
//        double d1,d2;
//        if(surface.get(6)==surface.get(9))
//        {
//            d1=(surface.get(7)-surface.get(4))*(y-surface.get(5))-(x-surface.get(4))*(surface.get(8)-surface.get(5));
//            d2=(surface.get(10)-surface.get(13))*(y-surface.get(14))-(x-surface.get(13))*(surface.get(11)-surface.get(14));
//            if(d1*d2<0)
//            {
//                d1=(surface.get(10)-surface.get(7))*(y-surface.get(8))-(x-surface.get(7))*(surface.get(11)-surface.get(8));
//                d2=(surface.get(13)-surface.get(4))*(y-surface.get(5))-(x-surface.get(4))*(surface.get(14)-surface.get(5));
//                if(d1*d2<0)
//                    return 1;
//                else return 0;
//            }
//            else
//                return 0;
//        }
//        else
//            if(surface.get(5)==surface.get(8))
//            {
//                d1=(surface.get(7)-surface.get(4))*(z-surface.get(6))-(x-surface.get(4))*(surface.get(9)-surface.get(6));
//                d2=(surface.get(10)-surface.get(13))*(z-surface.get(15))-(x-surface.get(13))*(surface.get(12)-surface.get(15));
//                if(d1*d2<0)
//                {
//                    d1=(surface.get(10)-surface.get(7))*(z-surface.get(9))-(x-surface.get(7))*(surface.get(12)-surface.get(9));
//                    d2=(surface.get(13)-surface.get(4))*(z-surface.get(6))-(x-surface.get(4))*(surface.get(15)-surface.get(6));
//                    if(d1*d2<0)
//                        return 1;
//                    else return 0;
//                }
//                else
//                    return 0;
//            }
//            else
//            {
//                d1=(surface.get(8)-surface.get(5))*(z-surface.get(6))-(y-surface.get(5))*(surface.get(9)-surface.get(6));
//                d2=(surface.get(11)-surface.get(14))*(z-surface.get(15))-(y-surface.get(14))*(surface.get(12)-surface.get(15));
//                if(d1*d2<0)
//                {
//                    d1=(surface.get(11)-surface.get(8))*(z-surface.get(9))-(y-surface.get(8))*(surface.get(12)-surface.get(9));
//                    d2=(surface.get(14)-surface.get(5))*(z-surface.get(6))-(y-surface.get(5))*(surface.get(15)-surface.get(6));
//                    if(d1*d2<0)
//                        return 1;
//                    else return 0;
//                }
//                else
//                    return 0;
//            }
//    }
    public boolean inVision(Camera camera){
        double deltaR;
        double deltaH;
        double maxR;
        if(camera.isInWall())
        {
            deltaR=Math.abs(camera.getPosition().getZ()-this.z);
            deltaH=Math.sqrt((camera.getPosition().getX()-this.x)*(camera.getPosition().getX()-this.x)*
                    +(camera.getPosition().getY()-this.y)*(camera.getPosition().getY()-this.y));

        }
        else
        {
            deltaR=Math.sqrt((camera.getPosition().getX()-this.x)*(camera.getPosition().getX()-this.x)*
                    +(camera.getPosition().getY()-this.y)*(camera.getPosition().getY()-this.y));
            deltaH=Math.abs(camera.getPosition().getZ()-this.z);
        }
        maxR=deltaH*Math.tan(camera.getWidthVision());
        if(maxR>=deltaR)
        {
            return true;
        }
        return false;
    }


    //sau hàm này xác định được chuỗi seenByCamera của từng coordinate
    public void beSeen(ArrayList<Camera> cameras, ArrayList<Obstacle> obstacles)
    {
        for (Camera camera : cameras)
        {
            int id = camera.getID();
            if(inVision(camera)) //xét xem có trong góc nhìn camera không
            {
                if(getDistances(camera.getPosition())<=(camera.getDeepVision()*camera.getDeepVision())) //xét khoảng cách
                {
                    int check =1;
                    for(Obstacle obstacle : obstacles )
                    {
                        if(obstacle.getTop1().getZ() > getZ())
                        {
                            ArrayList<Integer> line = getVector(camera.getPosition());
                            ArrayList<Integer> face1 = obstacle.getSurface1();
                            ArrayList<Integer> face2 = obstacle.getSurface2();
                            ArrayList<Integer> face3 = obstacle.getSurface3();
                            ArrayList<Integer> face4 = obstacle.getSurface4();
                            ArrayList<Integer> face5 = obstacle.getSurface5();
                            if(throughFace(line,face1)+throughFace(line,face2)+throughFace(line,face3)+throughFace(line,face4)
                                    +throughFace(line,face5)!=0)
                            {
                                check=0;
                                break;
                            }
                        }
                    }
                    if(check==1)
                    {
                        seenByCameras.setCharAt(id-1, '1');
                    }
                }
            }
        }
    }

    public static Coordinate getCoordinate(int x, int y, int z, Coordinate[] point)
    {
        for(Coordinate p : point)
        {
            if(p.getX()==x && p.getY()==y && p.getZ() == z)
                return p;
        }
        return null;
    }
}
