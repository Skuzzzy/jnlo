/**
 * Created by dan on 2/5/15.
 */
public class AndToken implements LogToken, Operator
{

    public String getTokenType()
    {
        return "AndToken";
    }

    public int getOperatorPrecedence()
    {
        return 2;
    }

    public String toString()
    {
        return "*";
    }
}
