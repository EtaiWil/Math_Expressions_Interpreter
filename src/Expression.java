import java.util.List;
import java.util.Map;

/**
 * Expression interface that had methods to do on expressions.
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided in the assignment.
     *
     * @param assignment
     * @return the result of the expression.
     * @throws Exception If the expression contains a variable which is not in the assignment, an exception is thrown.
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;


    /**
     * convenience method. Like the `evaluate(assignment)` method above,but uses an empty assignment.
     *
     * @return the evaluated of the expression
     * @throws Exception if evaluate failed.
     */
    Boolean evaluate() throws Exception;


    /**
     * get the variables in the expression.
     *
     * @return list of variables in the expression.
     */
    List<String> getVariables();

    /**
     * Returns a nice string representation of the expression.
     *
     * @return new string of the expression.
     */
    String toString();


    /**
     * @param var        the variable to give the value.
     * @param expression string that represent the value.
     * @return a new expression in which all occurrences of the variable var are replaced with the provided expression
     */
    Expression assign(String var, Expression expression);

    /**
     * convert the expression to nand form.
     *
     * @return the expression tree resulting from converting all the operations to the logical Nand operation.
     */
    Expression nandify();

    /**
     * convert the expression to nor form.
     *
     * @return the expression tree resulting from converting all the operations to the logical nor operation.
     */
    Expression norify();


    /**
     * get a expression and return simplified expression.
     *
     * @return a simplified version of the current expression.
     */
    Expression simplify();

    /**
     * get expression and return true if the expression is val or null if not.
     *
     * @return value of the expression if val, otherwise return null.
     */
    Boolean getVal();
}
