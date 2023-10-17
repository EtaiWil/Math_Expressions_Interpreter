import java.util.Map;

/**
 * Xnor operator have methods belong to Xnor.
 */
public class Xnor extends BinaryExpression {
    /**
     * constructor.
     * @param expression1 left expression.
     * @param expression2 right expression.
     */
    public Xnor(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }


    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        Boolean value1 = this.getLeftExpression().evaluate(assignment);
        Boolean value2 = this.getRightExpression().evaluate(assignment);
        if (value1 == value2) {
            return true;
        }
        return false;
    }


    @Override
    public Expression assign(String var, Expression expression) {
        return new Xnor(this.getLeftExpression().assign(var, expression),
                this.getRightExpression().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        // a xnor b= {(a nand a) nand (b nand b)} nand (a nand b)
        return new Nand(new Nand(new Nand(this.getLeftExpression().nandify(), this.getLeftExpression().nandify()),
                new Nand(this.getRightExpression().nandify(), this.getRightExpression().nandify())),
                new Nand(this.getLeftExpression().nandify(), this.getRightExpression().nandify()));

    }

    @Override
    public Expression norify() {
        // a xnor b= { a nor (a nor b)} nor { b nor (a nor b)}
        return new Nor(new Nor(this.getLeftExpression().norify(), new Nor(this.getLeftExpression().norify(),
                this.getRightExpression().norify())), new Nor(this.getRightExpression().norify(),
                new Nor(this.getLeftExpression().norify(), this.getRightExpression().norify())));
    }

    @Override
    public Expression simplify() {

        Expression expression1 = this.getLeftExpression().simplify();
        Expression expression2 = this.getRightExpression().simplify();
        // true with true is true. false with false is true. anything else is false.
        if (expression1.getVal() != null && expression2.getVal() != null) {
            if (expression1.getVal() == expression2.getVal()) {
                return new Val(true);
            }
            return new Val(false);
        }
        // if its x #x return true;
        if (expression1.toString().equals(expression2.toString())) {
            return new Val(true);
        }
        return new Xnor(expression1, expression2);
    }

    @Override
    public String toString() {
        return "(" + this.getLeftExpression() + " # " + this.getRightExpression() + ")";
    }
}
