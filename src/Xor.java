import java.util.Map;

/**
 * Xor opertaor have Xor methods.
 */
public class Xor extends BinaryExpression {
    /**
     * constructor.
     *
     * @param expression1 left expression.
     * @param expression2 right expression.
     */
    public Xor(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        Boolean value1 = this.getLeftExpression().evaluate(assignment);
        Boolean value2 = this.getRightExpression().evaluate(assignment);
        //if it's true.
        if ((value1 ^ value2)) {
            return true;
        }
        return false;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Xor(this.getLeftExpression().assign(var, expression),
                this.getRightExpression().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        // a xor b= { a nand (a nand b)} nand { b nand (a nand b)}

        return new Nand(new Nand(this.getLeftExpression().nandify(), new Nand(this.getLeftExpression().nandify(),
                this.getRightExpression().nandify())), new Nand(this.getRightExpression().nandify(),
                new Nand(this.getLeftExpression().nandify(), this.getRightExpression().nandify())));
    }

    @Override
    public Expression norify() {
        // a xor b={ (a nor a) nor (b nor b)} nor(a nor b)

        return new Nor(new Nor(new Nor(this.getLeftExpression().norify(), this.getLeftExpression().norify()),
                new Nor(this.getRightExpression().norify(), this.getRightExpression().norify())),
                new Nor(this.getLeftExpression().norify(), this.getRightExpression().norify()));

    }

    @Override
    public Expression simplify() {
        Expression expression1 = this.getLeftExpression().simplify();
        Expression expression2 = this.getRightExpression().simplify();
        if (expression1.getVal() != null) {
            if (expression1.getVal()) {
                return new Not(expression2).simplify();
            }
            return expression2;
        }
        if (expression2.getVal() != null) {
            if (expression2.getVal()) {
                return new Not(expression1).simplify();
            }
            return expression1;
        }
        // x xor x is false.
        if (expression1.toString().equals(expression2.toString())) {
            return new Val(false);
        }
        return new Xor(expression1, expression2);
    }

    @Override
    public String toString() {
        return "(" + this.getLeftExpression() + " ^ " + this.getRightExpression() + ")";
    }

}
