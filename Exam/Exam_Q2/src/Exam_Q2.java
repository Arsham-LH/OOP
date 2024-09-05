import java.util.InputMismatchException;
import java.util.Scanner;
class Point{
    String name;
    double x;
    double y;
    double z;

    public Point(String name, double x, double y, double z) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
class Triangle{
    Point point1;
    Point point2;
    Point point3;

    double edgeLength(Point point1 , Point point2){
        double x2 = (point1.x-point2.x) * (point1.x - point2.x);
        double y2 = (point1.y-point2.y) * (point1.y - point2.y);
        double z2 = (point1.z-point2.z) * (point1.z - point2.z);
        return Math.sqrt(x2+y2+z2);
    }

    public Triangle(Point point1, Point point2, Point point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }
    public static Triangle create(Scanner scanner) {
        try {
            String name;
            double x;
            double y;
            double z;
            name = scanner.next();
            x = scanner.nextDouble();
            y = scanner.nextDouble();
            z = scanner.nextDouble();
            Point point1 = new Point(name , x , y , z);

            name = scanner.next();
            x = scanner.nextDouble();
            y = scanner.nextDouble();
            z = scanner.nextDouble();
            Point point2 = new Point(name , x , y , z);

            name = scanner.next();
            x = scanner.nextDouble();
            y = scanner.nextDouble();
            z = scanner.nextDouble();
            Point point3 = new Point(name , x , y , z);
            Triangle result = new Triangle(point1 , point2 , point3);
            return result;
        } catch (InputMismatchException e){
            Triangle nulltri = new Triangle(null , null , null);
            return nulltri;
        } catch (NullPointerException e){
            return null;
        }

    }

    void println(){
        try {
            if (this != null) {
                if ((edgeLength(point1, point2) >= edgeLength(point1, point3) + edgeLength(point2, point3)) ||
                        (edgeLength(point2, point3) >= edgeLength(point1, point3) + edgeLength(point1, point2)) ||
                        (edgeLength(point1, point3) >= edgeLength(point1, point2) + edgeLength(point2, point3))) {
                    System.out.println("The points do not form a triangle!");
                } else {
                    System.out.println(point1.name + "(" + point1.x + ", " + point1.y + ", " + point1.z + ") , " +
                            point2.name + "(" + point2.x + ", " + point2.y + ", " + point2.z + ") , " +
                            point3.name + "(" + point3.x + ", " + point3.y + ", " + point3.z + ")");
                }
            }
        }
        catch (NullPointerException e){
            System.out.println("INVALID INPUT!");
        }
    }
}

public class Exam_Q2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Triangle triangle = Triangle.create(scanner);
            triangle.println();
    }
}
