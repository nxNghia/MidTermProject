import java.util.ArrayList;

public class Obstacle {
    private Coordinate bottom1;
    private Coordinate bottom2;
    private Coordinate bottom3;
    private Coordinate bottom4;

    private Coordinate top1;
    private Coordinate top2;
    private Coordinate top3;
    private Coordinate top4;

    private ArrayList<Integer> surface1;
    private ArrayList<Integer> surface2;
    private ArrayList<Integer> surface3;
    private ArrayList<Integer> surface4;
    private ArrayList<Integer> surface5;
    private String color;
    private int height;

    public Obstacle(int[] x, int[] y, int[] z, int height, String color)
    {
        //Tìm trả về Coordinate theo tọa độ (x[0], y[0], z[0]), ...
        //Set các bottom

        setHeight(height);
        setColor(color);

        //Tạo các mặt phẳng tọa độ

    }


    //Trả về mặt phẳng đi qua 4 tọa độ c1, c2, c3, c4
    //ax + by + cz = d
    //12 giá trị sau là biên của mặt phẳng
    private ArrayList<Integer> getSurface(Coordinate c1, Coordinate c2, Coordinate c3, Coordinate c4)
    {
        ArrayList<Integer> result = new ArrayList<>();
        if(c2.getZ() == c3.getZ())
        {
            result.add(0);  //a
            result.add(0);  //b
            result.add(1);  //c

            result.add(c1.getZ());  //d
        }else{
            int x = c1.getX() - c2.getX();
            int y = c1.getY() - c2.getY();

            result.add(y);  //a
            result.add(-x); //b
            result.add(0);  //c

            result.add(y * c1.getX() - x * c2.getY());  //d
        }

        result.add(c1.getX());
        result.add(c1.getY());
        result.add(c1.getZ());

        result.add(c2.getX());
        result.add(c2.getY());
        result.add(c2.getZ());

        result.add(c3.getX());
        result.add(c3.getY());
        result.add(c3.getZ());

        result.add(c4.getX());
        result.add(c4.getY());
        result.add(c4.getZ());

        return result;
    }

    public Coordinate getTop1() {
        return top1;
    }
    public Coordinate getTop2() {
        return top2;
    }

    public Coordinate getTop3() {
        return top3;
    }

    public Coordinate getTop4() {
        return top4;
    }

    public Coordinate getBottom1() {
        return bottom1;
    }

    public Coordinate getBottom2() {
        return bottom2;
    }

    public Coordinate getBottom3() {
        return bottom3;
    }

    public Coordinate getBottom4() {
        return bottom4;
    }

    public int getHeight() {
        return height;
    }

    public String getColor() {
        return color;
    }

    public void setBottom1(Coordinate bottom1) {
        this.bottom1 = bottom1;
    }

    public void setBottom2(Coordinate bottom2) {
        this.bottom2 = bottom2;
    }

    public void setBottom3(Coordinate bottom3) {
        this.bottom3 = bottom3;
    }

    public void setBottom4(Coordinate bottom4) {
        this.bottom4 = bottom4;
    }

    public void setTop1(Coordinate top1) {
        this.top1 = top1;
    }

    public void setTop2(Coordinate top2) {
        this.top2 = top2;
    }

    public void setTop3(Coordinate top3) {
        this.top3 = top3;
    }

    public void setTop4(Coordinate top4) {
        this.top4 = top4;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
