public class Line {
    public Node left;
    public Node right;

    public Line(Node left, Node right)
    {
        this.left = left;
        this.right = right;
    }

    public boolean cover(Node to, Node from)
    {
        Node imagine_node = to.scaleUp(from, this);
//        from.getNode();
//        System.out.print("unreal node: ");
//        imagine_node.getNode();
        if(this.left.x == this.right.x)
        {
            return imagine_node.y >= this.left.y && imagine_node.y <= this.right.y;
        }else{
            return imagine_node.x >= this.left.x && imagine_node.x <= this.right.x;
        }
    }
}
