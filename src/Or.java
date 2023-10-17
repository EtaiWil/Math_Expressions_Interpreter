
import java.util.Map;

/**
 * Or class have methods relative to or .
 */
public class Or extends BinaryExpression {
    /**
     * constructor.
     * @param expression1 left expression.
     * @param expression2 right expression.
     */
    public Or(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        Boolean value1 = this.getLeftExpression().evaluate(assignment);
        Boolean value2 = this.getRightExpression().evaluate(assignment);
        //if both true.
        if ((value1 || value2)) {
            return true;
        }
        return false;
    }


    @Override
    public Expression assign(String var, Expression expression) {
        return new Or(this.getLeftExpression().assign(var, expression),
                this.getRightExpression().assign(var, expression));

    }

    @Override
    public Expression nandify() {
        // a or b=(a nand a) nand (b nand b)
        return new Nand(new Nand(this.getLeftExpression().nandify(), this.getLeftExpression().nandify()),
                new Nand(this.getRightExpression().nandify(), this.getRightExpression().nandify()));
    }

    @Override
    public Expression norify() {
        // a or b= (a nor b) nor (a nor b)
        return new Nor(new Nor(this.getLeftExpression().norify(), this.getRightExpression().norify()),
                new Nor(this.getLeftExpression().norify(), this.getRightExpression().norify()));
    }

    @Override
    public Expression simplify() {
        Expression expression1 = this.getLeftExpression().simplify();
        Expression expression2 = this.getRightExpression().simplify();
        // x or true= ture; x or false= false;
        if (expression1.getVal() != null) {
            if (expression1.getVal()) {
                return expression1;
            }
            return expression2;
        }
        if (expression2.getVal() != null) {
            if (expression2.getVal()) {
                return expression2;
            }
            return expression1;
        }
        if (expression1.toString().equals(expression2.toString())) {
            return expression1;
        }
        return (new Or(expression1, expression2));

    }

    @Override
    public String toString() {
        return "(" + this.getLeftExpression() + " | " + this.getRightExpression() + ")";

    }

}
