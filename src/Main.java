import tokens.LogToken;

import java.util.ArrayList;

public class Main
{

    public static void main(String[] args)
    {
        String expr = "(a)+(b*!c)+d";
        System.out.println("Expression: " + expr);
        System.out.println("Sum of Products Form: " + Caonical.getSumOfProductsString(expr,true));
        System.out.println("Product of Sums Form: " + Caonical.getProductOfSumsString(expr, true));
        System.out.println("Minimized Sum of Products Form: " + Caonical.getReducedSumOfProductsString(expr, true));
        System.out.println("Minimized Product of Sums Form: " + Caonical.getReducedProductOfSumsString(expr,true));


        /*
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

        System.out.println("\n\n\n");

        ArrayList<boolean[]> test = Evaluator.generateTruthTable(postfixExpression);
        for(boolean[] b : test)
        {
            for(boolean a : b)
            {
                System.out.print(a+"\t");
            }
            System.out.println();
        }
        */
    }
}
