public class test {
    public static void main(String[] args) {
        Room room = new Room(2,1,1,2, 1);
        double[] x = {0.5,1.5,1.5,0.5,0.5,1.5,1.5,0.5};
        double[] y = {0.25,0.25,0.75,0.75,0.25,0.25,0.75,0.75};
        double[] z = {0.0,0.0,0.0,0.0,1.0,1.0,1.0,1.0};
        Obstacle obstacle = new Obstacle(x,y,z,1,"a");
        Camera camera1 = new Camera(0,0.5,0.5,90,90,10,2,room);
        Camera camera2 = new Camera(1,1,0.5,90,90,10,2,room);
        room.addCamera(camera1);
        room.addCamera(camera2);
        room.addObstacle(obstacle);
        double rate = room.RateOfSighting();
        System.out.println(rate);

    }
}
