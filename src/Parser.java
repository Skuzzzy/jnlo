import java.util.ArrayList;

/**
 * Created by dan on 2/5/15.
 */
public class Parser
{
    public static ArrayList<LogToken> parseString(String expression)
    {
        int position = 0;
        int exprlen = expression.length();
        ArrayList<LogToken> tokenizedExpression = new ArrayList<LogToken>();

        while(position < exprlen)
        {
            char currentChar = expression.charAt(position);
            if(Parser.isOr(currentChar))
            {
                tokenizedExpression.add(new OrToken());
            }
            else if(Parser.isAnd(currentChar))
            {
                tokenizedExpression.add(new AndToken());
            }
            else if(Parser.isNot(currentChar))
            {
                tokenizedExpression.add(new NotToken());
            }
            else if(Parser.isLeftParenthesis(currentChar))
            {
                tokenizedExpression.add(new LeftParenthesisToken());
            }
            else if(Parser.isRightParenthesis(currentChar))
            {
                tokenizedExpression.add(new RightParenthesisToken());
            }
            else if(Character.isAlphabetic(currentChar))
            {
                tokenizedExpression.add(new VariableToken(currentChar));
            }
            else
            {
                System.out.println("Unknown character " + currentChar + ". Skipping it");
            }

            ++position;
        }

        return tokenizedExpression;
    }

    public static boolean isOr(char c)
    {
        return (c == '+');
    }
    public static boolean isAnd(char c)
    {
        return (c == '*');
    }
    public static boolean isNot(char c)
    {
        return (c == '!');
    }
    public static boolean isLeftParenthesis(char c)
    {
        return (c == '(');
    }
    public static boolean isRightParenthesis(char c)
    {
        return (c == ')');
    }
}
