package goanvi.web.weblab3.utils;

public class HitMark {//TODO: Поменять область попадания для точки

    public static boolean hitMark (double x, double y, double r){
        return hitInCircle(x, y, r) || hitInRectangle(x, y, r) || hitInTriangle(x, y, r);
    }

    private static boolean hitInTriangle (double x, double y, double r){
        return x<=0 && y<=0 && -x/2-r/2<=y;
    }

    private static boolean hitInCircle (double x, double y, double r){
        return x>=0 && y<=0 && x*x+ y*y<=r*r;
    }

    private static boolean hitInRectangle (double x, double y, double r){
        return x <= 0 && x >= -r && y >= 0 && y <= r;
    }
}
