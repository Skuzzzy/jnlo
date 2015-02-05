/**
 * Created by dan on 2/5/15.
 */
public class NotToken implements LogToken, Operator
{

    public String getTokenType()
    {
        return "NotToken";
    }

    public int getOperatorPrecedence()
    {
        return 1;
    }

    public String toString()
    {
        return "!";
    }
}
