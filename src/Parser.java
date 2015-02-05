import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Stack;

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

    public static ArrayList<LogToken> convertToPostfix(ArrayList<LogToken> infixExpression)
    {
        ArrayList<LogToken> postfixExpression = new ArrayList<LogToken>();
        Stack<LogToken> operatorStack = new Stack<LogToken>();

        for(LogToken currentToken : infixExpression)
        {
            String currentTokenType = currentToken.getTokenType();
            if(currentTokenType.equals("OrToken") || currentTokenType.equals("AndToken") || currentTokenType.equals("NotToken"))
            {
                //While stack is not empty
                //While the thing on top of the stack is not a LeftParenthesisToken
                //While the operator on the top of the stack has a higher or equal precedence
                while((operatorStack.size() > 0) && !operatorStack.peek().getTokenType().equals("LeftParenthesisToken") && ((Operator)operatorStack.peek()).getOperatorPrecedence() <= ((Operator)currentToken).getOperatorPrecedence())
                {
                    postfixExpression.add(operatorStack.pop());
                }
                operatorStack.add(currentToken);

            }
            else if(currentTokenType.equals("LeftParenthesisToken"))
            {
                operatorStack.add(currentToken);
            }
            else if(currentTokenType.equals("RightParenthesisToken"))
            {
                while(operatorStack.size() > 0 && !(operatorStack.peek()).getTokenType().equals("LeftParenthesisToken"))
                {
                    postfixExpression.add(operatorStack.pop());
                }
                operatorStack.pop();
            }
            else if(currentTokenType.equals("VariableToken"))
            {
                postfixExpression.add(currentToken);
            }
            else
            {
                System.out.println("Unknown token type " + currentTokenType + ". Skipping it");
            }
        }

        while(operatorStack.size() > 0)
        {
            postfixExpression.add((LogToken)operatorStack.pop());
        }

        return postfixExpression;
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
