import java.util.Map;

/**
 * Nor class has methods relative to Nor.
 */
public class Nor extends BinaryExpression {
    /**
     * constracotr.
     * @param expression1 left expression.
     * @param expression2 right expression.
     */
    public Nor(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }


    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        Boolean value1 = this.getLeftExpression().evaluate(assignment);
        Boolean value2 = this.getRightExpression().evaluate(assignment);
        if (!(value1 || value2)) {
            return true;
        }
        return false;
    }


    @Override
    public Expression assign(String var, Expression expression) {
        return new Nor(this.getLeftExpression().assign(var, expression),
                this.getRightExpression().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        // a nor b= {(a nand a) nand (b nand b)} nand{(a nand a) nand (b nand b)}
        return new Nand(new Nand(new Nand(this.getLeftExpression().nandify(), this.getLeftExpression().nandify()),
                new Nand(this.getRightExpression().nandify(), this.getRightExpression()).nandify()),
                new Nand(new Nand(this.getLeftExpression().nandify(), this.getLeftExpression().nandify()),
                        new Nand(this.getRightExpression().nandify(), this.getRightExpression().nandify())));
    }

    @Override
    public Expression norify() {
        //return just it.
        return new Nor(this.getLeftExpression().norify(), this.getRightExpression().norify());
    }

    @Override
    public Expression simplify() {
        Expression expression1 = this.getLeftExpression().simplify();
        Expression expression2 = this.getRightExpression().simplify();
        //check if the expression are the same.
        if (expression1.toString().equals(expression2.toString())) {
            return (new Not(expression1).simplify());
        }
        // if the expression is a val and then check if his value is ture or false. x nor true=false,
        // and x nro false=not x;
        if (expression1.getVal() != null) {
            //if expression1.getval==true
            if (expression1.getVal()) {
                return new Val(false);
            }
            return (new Not(expression2).simplify());
        }
        if (expression2.getVal() != null) {
            if (expression2.getVal()) {
                return new Val(false);
            }
            return (new Not(expression1).simplify());
        }

        return new Nor(expression1, expression2);

    }

    @Override
    public String toString() {
        return "(" + this.getLeftExpression() + " V " + this.getRightExpression() + ")";
    }
}
