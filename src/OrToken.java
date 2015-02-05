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
}
