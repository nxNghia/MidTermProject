import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
//        Room room = new Room(20,10,10,2, 1);
//        double[] x = {5.0,15.0,15.0,5.0,5.0,15.0,15.0,5.0};
//        double[] y = {2.5,2.5,7.5,7.5,2.5,2.5,7.5,7.5};
//        double[] z = {0.0,0.0,0.0,0.0,10.0,10.0,10.0,10.0};
//        Obstacle obstacle = new Obstacle(x,y,z,1,"a");
//        Camera camera1 = new Camera(0,5.0,5.0,90,90,10,2,room);
//        Camera camera2 = new Camera(10,10,5,90,90,10,2,room);
//        room.addCamera(camera1);
//        room.addCamera(camera2);
//        room.addObstacle(obstacle);
//        double rate = room.RateOfSighting();
//        System.out.println(rate);

        Node from = new Node(4.0, 8.0, false);
        ArrayList<Rectangle> rects = new ArrayList<>();

        Node node1_1 = new Node(1.0, 2.0, true);
        Node node1_2 = new Node(3.0, 2.0, false);
        Node node1_3 = new Node(3.0, 1.0, false);
        Node node1_4 = new Node(1.0, 1.0, true);
        Rectangle rectangle1 = new Rectangle(node1_1, node1_2, node1_3, node1_4);

        Node node2_1 = new Node(3.0, 4.0, true);
        Node node2_2 = new Node(5.0, 4.0, false);
        Node node2_3 = new Node(5.0, 3.0, false);
        Node node2_4 = new Node(3.0, 3.0, true);
        Rectangle rectangle2 = new Rectangle(node2_1, node2_2, node2_3, node2_4);

        Node node3_1 = new Node(3.0, 7.5, true);
        Node node3_2 = new Node(3.5, 7.5, false);
        Node node3_3 = new Node(3.5, 6.0, false);
        Node node3_4 = new Node(3.0, 6.0, true);
        Rectangle rectangle3 = new Rectangle(node3_1, node3_2, node3_3, node3_4);

        rects.add(rectangle2);
        rects.add(rectangle3);
        rects.add(rectangle1);

        TestShadow test = new TestShadow();
        ArrayList<Node> result = test.solve(rects, from);

//        Line line = new Line(node2_1, node2_2);
//        node1_1.scaleUp(from, 0.0).getNode();

//        for (Node node : result)
//        {
//            System.out.println(node.x + " " + node.y + " " + node.coefficient);
//        }
        System.out.println("Result");
        for (Node node : result)
            node.getNode();

//        Line line = new Line(node3_3, node3_2);
//        Line line2 = new Line(node2_4, node2_1);
//        Line line3 = new Line(node3_1, node3_2);
        Line line = new Line(node2_3, node2_2);
//        System.out.println(line.cover(from, node3_3));
    }
}
