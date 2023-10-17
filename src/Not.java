import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Not class have methods belong to not.
 */
public class Not extends UnaryExpression {
    /**
     * constructor.
     * @param expression the expression.
     */
    public Not(Expression expression) {
        super(expression);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !this.getExpression().evaluate(assignment);
    }


    @Override
    public List<String> getVariables() {
        List<String> list = new ArrayList<>();
        list.addAll(getList().get(0).getVariables());
        return list;
    }

    @Override
    public Expression assign(String var, Expression expression) {


        return new Not(this.getExpression().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        return new Nand(this.getExpression().nandify(), this.getExpression().nandify());

    }

    @Override
    public Expression norify() {
        return new Nor(this.getExpression().norify(), this.getExpression().norify());
    }

    @Override
    public Expression simplify() {
        Expression expression = this.getExpression().simplify();

        //if its only value without variables.
        if (expression.getVal() != null) {
            return new Val(!expression.getVal());
        }
        //return simplified expression
        return (new Not(expression));


    }

    @Override
    public String toString() {
        return "~" + "(" + this.getExpression() + ")";
    }

}
