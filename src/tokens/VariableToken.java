package tokens;

/**
 * Created by dan on 2/5/15.
 */
public class VariableToken implements LogToken
{
    char c;

    public VariableToken(char c)
    {
        this.c = c;
    }

    public String getTokenType()
    {
        return "VariableToken";
    }

    public String toString()
    {
        return c + "";
    }
}
