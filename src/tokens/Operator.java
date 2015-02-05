package tokens;

/**
 * Created by dan on 2/5/15.
 */
public interface Operator
{
    public int getOperatorPrecedence();
    public boolean evaluate(boolean[] inputs);
    public int expectedParameters();

}
