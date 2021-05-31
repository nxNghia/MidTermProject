public class Rectangle {
    public Line line1;
    public Line line2;

    public Line line3;
    public Line line4;

    public Rectangle(Node node1, Node node2, Node node3, Node node4)
    {
        line1 = new Line(node1, node2);
        line2 = new Line(node4, node3);

        line3 = new Line(node4, node1);
        line4 = new Line(node3, node2);
    }
}
