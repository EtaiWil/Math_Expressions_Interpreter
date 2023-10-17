import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * var class create and have methods relative to variables.
 */
public class Var implements Expression {
    private String var;

    /**
     * constructor.
     *
     * @param var
     */
    public Var(String var) {
        this.var = var;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {

        for (String name : assignment.keySet()) {
            if (this.var == name) {
                if (assignment.get(name)) {
                    return true;
                }

                return false;
            }
        }

        throw new Exception("no value for" + this.var);
    }

    @Override
    public Boolean evaluate() throws Exception {
        throw new Exception("evaluate is failed");
    }

    @Override
    public String toString() {
        return this.var;
    }

    @Override
    public List<String> getVariables() {
        List<String> list = new ArrayList<>();

        list.add(var);
        return list;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.var.equals(var)) {
            return expression;
        }
        return this;


    }

    @Override
    public Expression nandify() {
        return this;
    }

    @Override
    public Expression norify() {
        return this;
    }

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public Boolean getVal() {
        return null;
    }

}

