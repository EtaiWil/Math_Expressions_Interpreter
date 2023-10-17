import java.util.Map;
import java.util.TreeMap;
// 211601653 Etai Wilentzik
/**
 * expression test is the class I have the main.
 */
public class ExpressionsTest {
    /**
     * main of the program.
     * @param args
     */
    public static void main(String[] args) {
        Var x = new Var("x");
        Var y = new Var("y");
        Var z = new Var("z");
        Var t = new Var("t");
        Map<String, Boolean> map = new TreeMap<>();
        map.put("x", true);
        map.put("y", false);
        map.put("z", false);
        map.put("t", false);
        Expression expression = new Or(new And(x, y), new Xor(z, t));
        System.out.println(expression);
        try {
            System.out.println(expression.evaluate(map));
        } catch (Exception exception) {
            System.out.println("error in evaluate");
        }
        System.out.println(expression.nandify());
        System.out.println(expression.norify());
        System.out.println(expression.simplify());
    }
}
