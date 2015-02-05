package tokens;

/**
 * Created by dan on 2/5/15.
 */
public class RightParenthesisToken implements LogToken
{

    public String getTokenType()
    {
        return "RightParenthesisToken";
    }

    public String toString()
    {
        return ")";
    }

}
