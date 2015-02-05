import tokens.LogToken;

import java.util.ArrayList;

public class Main
{

    public static void main(String[] args)
    {
        String expr = "c*!(a+b)";
        System.out.println("Expression: "+expr);
        ArrayList<LogToken> tokenExpression = Parser.parseString(expr);
        ArrayList<LogToken> postfixExpression = Parser.convertToPostfix(tokenExpression);


        System.out.print("Postfix Reprepsentation: ");
        for(LogToken token : postfixExpression)
        {
            System.out.print(token.toString()+" ");
        }
        System.out.print("\n");

        Evaluator.displayTruthTable(postfixExpression);
    }

}
