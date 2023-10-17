import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * abstract class for all binary expressions.
 */
public abstract class BinaryExpression extends BaseExpression {
    /**
     * constracotr.
     *
     * @param expression1 first expression
     * @param expression2 second expression
     */
    public BinaryExpression(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    /**
     * give the left expression of the binary expression.
     * @return left expression.
     */
    protected Expression getLeftExpression() {
        return this.getList().get(0);
    }

    /**
     * give the right expression of the binary expression.
     * @return right expression.
     */
    protected Expression getRightExpression() {
        return this.getList().get(1);
    }
@Override
/**
 * return list of variables in the expression.
 */
    public List<String> getVariables() {
        Set<String> set = new HashSet<>();
        //add the left and right expression variables to the list.
        set.addAll(getList().get(0).getVariables());
        set.addAll(getList().get(1).getVariables());
        //return new array list that gets the set
        return new ArrayList<>(set);
    }


}
