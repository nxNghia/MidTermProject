import java.util.ArrayList;

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
        room.caculateCamera(3, false);
        System.out.println(rate);

//        Node from = new Node(400.0, 0.0);
//        Node from2 = new Node(0.0, 300.0);
//        ArrayList<Rectangle> rects = new ArrayList<>();
//
//        Node node1_1 = new Node(350.0, 300.0);
//        Node node1_2 = new Node(450.0, 300.0);
//        Node node1_3 = new Node(450.0, 100.0);
//        Node node1_4 = new Node(350.0, 100.0);
//        Rectangle rectangle1 = new Rectangle(node1_1, node1_2, node1_3, node1_4);
//
//        Node node2_1 = new Node(300, 300.0);
//        Node node2_2 = new Node(325.0, 300.0);
//        Node node2_3 = new Node(325.0, 100.0);
//        Node node2_4 = new Node(300.0, 100.0);
//        Rectangle rectangle2 = new Rectangle(node2_1, node2_2, node2_3, node2_4);
//
//        Node node3_1 = new Node(200.0, 200.0);
//        Node node3_2 = new Node(200.0, 400.0);
//        Node node3_3 = new Node(100.0, 400.0);
//        Node node3_4 = new Node(100.0, 200.0);
//        Rectangle rectangle3 = new Rectangle(node3_1, node3_2, node3_3, node3_4);
//
////        rects.add(rectangle2);
//        rects.add(rectangle3);
////        rects.add(rectangle1);
//
////        Line line = new Line(node1_3, node1_2);
//
//        TestShadow test = new TestShadow();
//        test.angle = Math.PI * 2 / 3;
//        ArrayList<Node> result = test.solve2(rects, from2);
//
//        System.out.println("Result");
//        for (Node node : result)
//            node.getNode();
    }
}
