import java.util.ArrayList;

public class Obstacle {
    private ArrayList<Double> bottom1;
    private ArrayList<Double> bottom2;
    private ArrayList<Double> bottom3;
    private ArrayList<Double> bottom4;

    private ArrayList<Double> top2;
    private ArrayList<Double> top1;
    private ArrayList<Double> top3;
    private ArrayList<Double> top4;

    private ArrayList<Double> surface1;

    private ArrayList<Double> surface2;

    private ArrayList<Double> surface3;

    private ArrayList<Double> surface4;
    private ArrayList<Double> surface5;
    private String color;
    private int height;
    public Obstacle(double[] x, double[] y, double[] z, int height, String color)
    {
        ArrayList<Double> bot_1 = new ArrayList<>();
        ArrayList<Double> bot_2 = new ArrayList<>();
        ArrayList<Double> bot_3 = new ArrayList<>();
        ArrayList<Double> bot_4 = new ArrayList<>();
        ArrayList<Double> top_1 = new ArrayList<>();
        ArrayList<Double> top_2 = new ArrayList<>();
        ArrayList<Double> top_3 = new ArrayList<>();
        ArrayList<Double> top_4 = new ArrayList<>();
        bot_1.add(x[0]);bot_1.add(y[0]);bot_1.add(z[0]);
        bot_2.add(x[1]);bot_2.add(y[1]);bot_2.add(z[1]);
        bot_3.add(x[2]);bot_3.add(y[2]);bot_3.add(z[2]);
        bot_4.add(x[3]);bot_4.add(y[3]);bot_4.add(z[3]);
        top_1.add(x[4]);top_1.add(y[4]);top_1.add(z[4]);
        top_2.add(x[5]);bot_1.add(y[5]);bot_1.add(z[5]);
        top_3.add(x[6]);top_3.add(y[6]);top_3.add(z[6]);
        top_4.add(x[7]);top_4.add(y[7]);top_4.add(z[7]);
        setBottom1(bot_1);
        setBottom2(bot_2);
        setBottom3(bot_3);
        setBottom4(bot_4);
        setTop1(top_1);
        setTop2(top_2);
        setTop3(top_3);
        setTop4(top_4);

        setHeight(height);
        setColor(color);

        //Tạo các mặt phẳng tọa độ

    }

    //Trả về mặt phẳng đi qua 4 tọa độ c1, c2, c3, c4
    //ax + by + cz = d
    //12 giá trị sau là biên của mặt phẳng

    private ArrayList<Double> getSurface(double xA, double yA,double zA,double xB,double yB,double zB,double xC,
                                          double yC,double zC,double xD,double yD,double zD)
    {
        ArrayList<Double> result = new ArrayList<>();
        if(zB == zC)
        {
            result.add(0.0);  //a
            result.add(0.0);  //b
            result.add(1.0);  //c

            result.add(zA);  //d
        }else{
            double x =xA - xB;
            double y = yA - yB;

            result.add(y);  //a
            result.add(-x); //b
            result.add(0.0);  //c

            result.add(y * xA - x * yB);  //d
        }

        result.add(xA);
        result.add(yA);
        result.add(zA);

        result.add(xB);
        result.add(yB);
        result.add(zB);

        result.add(xC);
        result.add(yC);
        result.add(zC);

        result.add(xD);
        result.add(yD);
        result.add(zD);

        return result;
    }

    public ArrayList<Double> getSurface1() {
        return surface1;
    }
    public ArrayList<Double> getSurface2() {
        return surface2;
    }

    public ArrayList<Double> getSurface3() {
        return surface3;
    }

    public ArrayList<Double> getSurface4() {
        return surface4;
    }

    public ArrayList<Double> getSurface5() {
        return surface5;
    }

    public ArrayList<Double> getTop1() {
        return top1;
    }

    public ArrayList<Double> getTop2() {
        return top2;
    }
    public ArrayList<Double> getTop3() {
        return top3;
    }

    public ArrayList<Double> getTop4() {
        return top4;
    }

    public ArrayList<Double> getBottom1() {
        return bottom1;
    }

    public ArrayList<Double> getBottom2() {
        return bottom2;
    }

    public ArrayList<Double> getBottom3() {
        return bottom3;
    }

    public ArrayList<Double> getBottom4() {
        return bottom4;
    }

    public int getHeight() {
        return height;
    }

    public String getColor() {
        return color;
    }

    public void setBottom1(ArrayList<Double> bottom1) {
        this.bottom1 = bottom1;
    }

    public void setBottom2(ArrayList<Double> bottom2) {
        this.bottom2 = bottom2;
    }

    public void setBottom3(ArrayList<Double> bottom3) {
        this.bottom3 = bottom3;
    }

    public void setBottom4(ArrayList<Double> bottom4) {
        this.bottom4 = bottom4;
    }

    public void setTop1(ArrayList<Double> top1) {
        this.top1 = top1;
    }

    public void setTop2(ArrayList<Double> top2) {
        this.top2 = top2;
    }

    public void setTop3(ArrayList<Double> top3) {
        this.top3 = top3;
    }

    public void setTop4(ArrayList<Double> top4) {
        this.top4 = top4;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSurface1(ArrayList<Double> surface1) {
//        ArrayList<Double> result = new ArrayList<>();
//        result.add(0.0);
//        result.add(1.0);
//        result.add(0.0);
//        result.add(0.0-bottom1.get(1));
//        result.add(bottom1.get(0));result.add(bottom1.get(1));result.add(bottom1.get(2));
//        result.add(bottom2.get(0));result.add(bottom2.get(1));result.add(bottom2.get(2));
//        result.add(top2.get(0));result.add(top2.get(1));result.add(top2.get(2));
//        result.add(top1.get(0));result.add(top1.get(1));result.add(top1.get(2));
        this.surface1 = surface1;
    }

    public void setSurface2(ArrayList<Double> surface2) {
//        ArrayList<Double> result = new ArrayList<>();
//        result.add(1.0);
//        result.add(0.0);
//        result.add(0.0);
//        result.add(0.0-bottom2.get(0));
//        result.add(bottom3.get(0));result.add(bottom3.get(1));result.add(bottom3.get(2));
//        result.add(bottom2.get(0));result.add(bottom2.get(1));result.add(bottom2.get(2));
//        result.add(top2.get(0));result.add(top2.get(1));result.add(top2.get(2));
//        result.add(top3.get(0));result.add(top3.get(1));result.add(top3.get(2));
        this.surface2 = surface2;
    }

    public void setSurface3(ArrayList<Double> surface3) {
//        ArrayList<Double> result = new ArrayList<>();
//        result.add(0.0);
//        result.add(1.0);
//        result.add(0.0);
//        result.add(0.0-bottom3.get(1));
//        result.add(bottom3.get(0));result.add(bottom3.get(1));result.add(bottom3.get(2));
//        result.add(bottom4.get(0));result.add(bottom4.get(1));result.add(bottom4.get(2));
//        result.add(top4.get(0));result.add(top4.get(1));result.add(top4.get(2));
//        result.add(top3.get(0));result.add(top3.get(1));result.add(top3.get(2));
        this.surface3 = surface3;
    }

    public void setSurface4(ArrayList<Double> surface4) {
//        ArrayList<Double> result = new ArrayList<>();
//        result.add(1.0);
//        result.add(0.0);
//        result.add(0.0);
//        result.add(0.0-bottom1.get(0));
//        result.add(bottom1.get(0));result.add(bottom1.get(1));result.add(bottom1.get(2));
//        result.add(bottom4.get(0));result.add(bottom4.get(1));result.add(bottom4.get(2));
//        result.add(top4.get(0));result.add(top4.get(1));result.add(top4.get(2));
//        result.add(top1.get(0));result.add(top1.get(1));result.add(top1.get(2));
        this.surface4 = surface4;
    }

    public void setSurface5(ArrayList<Double> surface5) {
//        ArrayList<Double> result = new ArrayList<>();
//        result.add(0.0);
//        result.add(0.0);
//        result.add(1.0);
//        result.add(0.0-top1.get(2));
//        result.add(top4.get(0));result.add(top4.get(1));result.add(top4.get(2));
//        result.add(top3.get(0));result.add(top3.get(1));result.add(top3.get(2));
//        result.add(top2.get(0));result.add(top2.get(1));result.add(top2.get(2));
//        result.add(top1.get(0));result.add(top1.get(1));result.add(top1.get(2));
        this.surface5 = surface5;
    }
}
