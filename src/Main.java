import tokens.LogToken;

import java.util.ArrayList;

public class Main
{

    public static void main(String[] args)
    {
        String expr = "c*!(a+b)";
        System.out.println(expr);
        ArrayList<LogToken> tokenExpression = Parser.parseString(expr);
        ArrayList<LogToken> postfixExpression = Parser.convertToPostfix(tokenExpression);
        Evaluator.displayTruthTable(postfixExpression);
    }

}
