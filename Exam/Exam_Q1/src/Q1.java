import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
class Vector3D{
    double x;
    double y;
    double z;
    String xstr;
    String ystr;
    String zstr;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(String xstr, String ystr, String zstr) {
        this.xstr = xstr;
        this.ystr = ystr;
        this.zstr = zstr;
    }

    String getLength(){
        double result = Math.sqrt(x*x+y*y+z*z);
        return getRoundedNum(result);
    }
    String getRoundedNum(double number){
        double resultNum = number;
        double roundedResultNum = BigDecimal.valueOf(resultNum).setScale(2, RoundingMode.HALF_UP).doubleValue();
        String doubleStr = Double.toString(roundedResultNum);
        if (doubleStr.indexOf(".") - doubleStr.length() != -3)
            doubleStr +='0';
        if (doubleStr.equals("-0.00"))
            doubleStr = "0.00";
        return doubleStr;
    }
    Vector3D crossProduct(Vector3D V2){
        double x;
        double y;
        double z;
        x = this.y*V2.z - this.z*V2.y;
        y = this.z * V2.x - this.x * V2.z;
        z = this.x * V2.y - this.y * V2.x;
        Vector3D Result = new Vector3D(Double.parseDouble(getRoundedNum(x)),Double.parseDouble(getRoundedNum(y))
                ,Double.parseDouble(getRoundedNum(z)));
        return Result;
    }
    String dotProduct(Vector3D V2){
        double result;
        result = this.x*V2.x + this.y * V2.y + this.z * V2.z;
        return getRoundedNum(result);
    }
    @Override
    public String toString(){
        if (this.xstr != null)
            return "NaN,NaN,NaN";
        String result = new String();
        result = getRoundedNum(this.x)+","+getRoundedNum(this.y)+","+getRoundedNum(this.z);
        return result;
    }
    Vector3D getUnitVector(){
        double x;
        double y;
        double z;
        if(this.getLength().equals("0.00")) {
            String xst = "NaN";
            String yst = "NaN";
            String zst = "NaN";
            Vector3D result = new Vector3D(xst , yst , zstr);
            return result;
        }

        x = this.x/Double.parseDouble(this.getLength());
        y = this.y/Double.parseDouble(this.getLength());
        z = this.z/Double.parseDouble(this.getLength());
        Vector3D Result = new Vector3D(Double.parseDouble(getRoundedNum(x)) , Double.parseDouble(getRoundedNum(y))
                , Double.parseDouble(getRoundedNum(z)));
        return Result;
    }
}
public class Q1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Vector3D V1,V2,V3;
        double X,Y,Z;
        X = scanner.nextDouble();
        Y = scanner.nextDouble();
        Z = scanner.nextDouble();
        V1 = new Vector3D(X,Y,Z);
        V2 = new Vector3D(Y,Z,X);
        System.out.println("L= " + V2.getLength());
        V3 = V1.crossProduct(V2);
        System.out.println("CP= " + V3.toString());
        System.out.println("DP= " + V1.dotProduct(V2));
        System.out.println("UV= " + V3.getUnitVector().toString());
    }
}
