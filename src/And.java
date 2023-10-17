
import java.util.Map;

/**
 * And class has methods on and operator.
 */
public class And extends BinaryExpression {
    /**
     * constructor.
     *
     * @param expression1 left expression.
     * @param expression2 right expression.
     */
    public And(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        Boolean value1 = this.getLeftExpression().evaluate(assignment);
        Boolean value2 = this.getRightExpression().evaluate(assignment);
        //if both are true.
        if ((value1 && value2)) {
            return true;
        }
        return false;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new And(this.getLeftExpression().assign(var, expression),
                this.getRightExpression().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        // a and b= (a nand b) nand (a nand b)
        return new Nand(new Nand(this.getLeftExpression().nandify(), this.getRightExpression().nandify()),
                new Nand(this.getLeftExpression().nandify(), this.getRightExpression().nandify()));
    }

    @Override
    public Expression norify() {
        // a and b= (a nor a) nor (b nor b)
        return new Nor(new Nor(this.getLeftExpression().norify(), this.getLeftExpression().norify()),
                new Nor(this.getRightExpression().norify(), this.getRightExpression().norify()));
    }

    @Override
    public Expression simplify() {
        Expression expression1 = this.getLeftExpression().simplify();
        Expression expression2 = this.getRightExpression().simplify();
        //if first expression is a value;
        if (expression1.getVal() != null) {
            //if expression1.getval is true.
            if (expression1.getVal()) {
                return expression2;
            }
            return expression1;
        }
        if (expression2.getVal() != null) {
            if (expression2.getVal()) {
                return expression1;
            }
            return expression2;
        }
        //if they are the same.
        if (expression1.toString().equals(expression2.toString())) {
            return expression1;
        }
        return (new And(expression1, expression2));
    }

    @Override
    public String toString() {
        return "(" + this.getLeftExpression() + " & " + this.getRightExpression() + ")";
    }
}
