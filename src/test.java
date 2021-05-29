public class test {
    public static void main(String[] args) {
        Room room = new Room(20,10,10,2, 1);
        double[] x = {5.0,15.0,15.0,5.0,5.0,15.0,15.0,5.0};
        double[] y = {2.5,2.5,7.5,7.5,2.5,2.5,7.5,7.5};
        double[] z = {0.0,0.0,0.0,0.0,10.0,10.0,10.0,10.0};
        Obstacle obstacle = new Obstacle(x,y,z,1,"a");
        Camera camera1 = new Camera(0,5.0,5.0,90,90,10,2,room);
        Camera camera2 = new Camera(10,10,5,90,90,10,2,room);
        room.addCamera(camera1);
        room.addCamera(camera2);
        room.addObstacle(obstacle);
        double rate = room.RateOfSighting();
        System.out.println(rate);
    }
}
