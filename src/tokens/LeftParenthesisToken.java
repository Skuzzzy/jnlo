package tokens;

import tokens.LogToken;

/**
 * Created by dan on 2/5/15.
 */
public class LeftParenthesisToken implements LogToken
{

    public String getTokenType()
    {
        return "LeftParenthesisToken";
    }

    public String toString()
    {
        return "(";
    }
}
