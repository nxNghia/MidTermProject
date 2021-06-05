import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        Node from = new Node(400.0, 0.0);
        ArrayList<MyRectangle> rects = new ArrayList<>();
        Node node1_1 = new Node(350.0, 300.0);
        Node node1_2 = new Node(450.0, 300.0);
        Node node1_3 = new Node(450.0, 100.0);
        Node node1_4 = new Node(350.0, 100.0);
        MyRectangle rectangle1 = new MyRectangle(node1_1, node1_2, node1_3, node1_4);
        rects.add(rectangle1);
        TestShadow test = new TestShadow();
        test.angle = Math.PI / 2;
        ArrayList<Node> result = test.solve(rects, from);
        System.out.println("Result");
        for (Node node : result)
            node.getNode();
    }
}
