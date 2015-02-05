package tokens;

/**
 * Created by dan on 2/5/15.
 */
public class OrToken implements LogToken, Operator
{

    public String getTokenType()
    {
        return "OrToken";
    }

    public int getOperatorPrecedence()
    {
        return 3;
    }

    public String toString()
    {
        return "+";
    }

    public boolean evaluate(boolean[] inputs) {
        return (inputs[0] || inputs[1]);
    }

    public int expectedParameters() {
        return 2;
    }
}
