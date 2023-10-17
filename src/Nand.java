import java.util.Map;

/**
 * nand class have methods on nand operator.
 */
public class Nand extends BinaryExpression {
    /**
     * constracotr for new nand.
     * @param expression1 left expression.
     * @param expression2 right expression.
     */
    public Nand(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }


    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        Boolean value1 = this.getLeftExpression().evaluate(assignment);
        Boolean value2 = this.getRightExpression().evaluate(assignment);
        if (!(value1 && value2)) {
            return true;
        }
        return false;
    }


    @Override
    public Expression assign(String var, Expression expression) {
        return new Nand(this.getLeftExpression().assign(var, expression),
                this.getRightExpression().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        //return just as is;
        return new Nand(this.getLeftExpression().nandify(), this.getRightExpression().nandify());
    }

    @Override
    public Expression norify() {
        // a nand b= { (a nor a) nor (b nor b)} nor (a nor a) nor (b nor b)
        return new Nor(new Nor(new Nor(this.getLeftExpression().norify(), this.getLeftExpression().norify()),
                new Nor(this.getRightExpression().norify(), this.getRightExpression().norify())),
                new Nor(new Nor(this.getLeftExpression().norify(), this.getLeftExpression().norify()),
                        new Nor(this.getRightExpression().norify(), this.getRightExpression().norify())));

    }
    @Override
    public Expression simplify() {
        Expression expression1 = this.getLeftExpression().simplify();
        Expression expression2 = this.getRightExpression().simplify();
//if its x nand x = not x;

//if the first is val simplified as wanted;
        if (expression1.getVal() != null) {
            //if expression1.get val is true
            if (expression1.getVal()) {
                return (new Not(expression2).simplify());
            }
            return (new Val(true));
        }
        if (expression2.getVal() != null) {
            if (expression2.getVal()) {
                return (new Not(expression1).simplify());
            }
            return new Val(true);
        }
//check if the expression are the same.
        if (expression1.toString().equals(expression2.toString())) {
            return (new Not(expression1).simplify());
        }


        return (new Nand(expression1, expression2));
    }

    @Override
    public String toString() {
        return "(" + this.getLeftExpression() + " A " + this.getRightExpression() + ")";
    }
}
