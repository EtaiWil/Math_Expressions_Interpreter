import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * val class have methods relative to values.
 */
public class Val implements Expression {
    private Boolean val;

    /**
     * constructor.
     * @param val the value.
     */
    public Val(boolean val) {
        this.val = val;
    }

    /**
     * get this val.
     * @return return the value.
     */
    public Boolean getVal() {
        return this.val;
    }

  @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.val;
    }
@Override
    public Boolean evaluate() throws Exception {
        return this.val;
    }
@Override
    public List<String> getVariables() {
        //return empty list
        return Collections.emptyList();
    }

@Override
    public Expression assign(String var, Expression expression) {

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
    public String toString() {
        if (this.val) {
            return "T";
        }
        return "F";
    }

}


