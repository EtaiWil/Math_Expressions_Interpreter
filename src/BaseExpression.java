import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * abstract class that every expression extend, have the basic methods of every expression.
 */
public abstract class BaseExpression implements Expression {
    private List<Expression> list;


    /**
     * constracotr for unary expression.
     *
     * @param expression the expression for the unary.
     */
    public BaseExpression(Expression expression) {
        this.list = new ArrayList<>();
        list.add(expression);
    }

    /**
     * constructor for every binary expression.
     *
     * @param expression1 first expression (left).
     * @param expression2 second expression(right).
     */
    public BaseExpression(Expression expression1, Expression expression2) {
        this.list = new ArrayList<>();
        list.add(expression1);
        list.add(expression2);
    }

    @Override
    public Boolean evaluate() throws Exception {
        Map<String, Boolean> assignment = new TreeMap<>();
        return evaluate(assignment);
    }

    protected List<Expression> getList() {
        return this.list;
    }

    @Override
    public Boolean getVal() {
        return null;
    }
}

