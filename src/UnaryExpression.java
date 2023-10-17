/**
 * abstract class for unary expression.
 */
public abstract class UnaryExpression extends BaseExpression {
    /**
     * constructor.
     * @param expression the expression.
     */
    public UnaryExpression(Expression expression) {
        super(expression);
    }

    /**
     * give the expression I want to do on him unary method.
     * @return the expression
     */
    protected Expression getExpression() {
        return this.getList().get(0);
    }


}
