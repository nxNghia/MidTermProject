import java.util.ArrayList;
import java.util.Collections;

public class TestShadow {
    public ArrayList<Rectangle> rects;
    public ArrayList<Line> lines_horizon;
    public ArrayList<Line> lines_vertical;
    public ArrayList<Node> tmp;
    public ArrayList<Node> result;
    public final double P_INFINITE = 1000000.0;
    public final double N_INFINITE = -1000000.0;

    public ArrayList<Node> solve(ArrayList<Rectangle> r, Node from)
    {
        this.rects = r;
        lines_horizon = new ArrayList<>();
        lines_vertical = new ArrayList<>();
        tmp = new ArrayList<>();
        result = new ArrayList<>();

        Node base_line_left = new Node(N_INFINITE, 0.0, true);
        Node base_line_right = new Node(P_INFINITE, 0.0, true);
        Line base_line = new Line(base_line_left, base_line_right);

        for (Rectangle rect : rects)
        {
            rect.line1.left.setK(from);
            rect.line1.right.setK(from);
            rect.line2.left.setK(from);
            rect.line2.right.setK(from);

            lines_horizon.add(rect.line1);
            lines_horizon.add(rect.line2);

            lines_vertical.add(rect.line3);
            lines_vertical.add(rect.line4);
        }

        for (Line line : lines_horizon)
        {
            boolean beCovered = false;
//            System.out.print("left node: ");
//            line.left.getNode();
            for (Line line_ : lines_horizon)
            {
                beCovered = line_.cover(from, line.left);
                if(beCovered) {
                    beCovered = line_.left.y > line.left.y;
                }

                if(beCovered && line_ != line) {
//                    System.out.println("52get cut at: ");
//                    line_.left.getNode();
//                    line_.right.getNode();
//                    System.out.println();
                    break;
                }
            }

//            System.out.println(beCovered);
//            System.out.println();

            if(!beCovered)
            {
                for (Line line_ : lines_vertical)
                {
                    beCovered = line_.cover(from, line.left);
                    if(beCovered)
                        beCovered = line_.left.x > line.left.x;

                    if(beCovered && line_ != line)
                    {
//                        System.out.println("73get cut at: ");
//                        line_.left.getNode();
//                        line_.right.getNode();
//                        System.out.println();
                        break;
                    }
                }

                if(!beCovered)
                    tmp.add(line.left);
            }

//            System.out.print("right node: ");
//            line.right.getNode();
            for (Line line_ : lines_horizon)
            {
                beCovered = line_.cover(from, line.right);
//                System.out.println(beCovered + "56");
                if(beCovered) {
                    beCovered = line_.left.y > line.right.y;
                }
//                System.out.println(beCovered + "64");

                if(beCovered && line_ != line) {
//                    System.out.println("97get cut at: ");
//                    line_.left.getNode();
//                    line_.right.getNode();
//                    System.out.println();
                    break;
                }
            }

//            System.out.println(beCovered);
//            System.out.println();

            if(!beCovered)
            {
                for (Line line_ : lines_vertical)
                {
//                    line.right.getNode();
//                    line_.left.getNode();
//                    line_.right.getNode();
                    beCovered = line_.cover(from, line.right);
//                    System.out.println(beCovered);
                    if(beCovered)
                        beCovered = line_.left.x > line.right.x;
//                    System.out.println(beCovered);
                    if(beCovered && line_ != line)
                    {
//                        System.out.println("122get cut at: ");
//                        line_.left.getNode();
//                        line_.right.getNode();
//                        System.out.println();
                        break;
                    }
                }

                if(!beCovered)
                    tmp.add(line.right);
            }
        }

        Collections.sort(tmp);

        for (Node node : tmp)
        {
            node.getNode();
        }

        for (Node node : tmp)
        {
//            node.getNode();
            Node possible_node = node.scaleUp(from, base_line);
            double possible_Y = 0.0;

            for (Line line : lines_horizon)
            {
                if(line.left != node && line.right != node)
                {
                    Node imagine_node = node.scaleUp(from, line);
                    if((imagine_node.x >= line.left.x) && (imagine_node.x <= line.right.x))
                    {
                        if(imagine_node.y > possible_Y)
                        {
                            possible_Y = imagine_node.y;
                            possible_node = imagine_node;
                        }
                    }
                }
            }

            if(node.isLeft)
            {
                result.add(possible_node);
//                System.out.println(possible_node.x + " " + possible_node.y);
                result.add(node);
            }else{
                result.add(node);
                result.add(possible_node);
//                System.out.println(possible_node.x + " " + possible_node.y);
            }
        }

        return result;
    }
}
