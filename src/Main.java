import tokens.LogToken;

import java.util.ArrayList;

public class Main
{

    public static void main(String[] args)
    {
        String expr = "(!a*a)+(b*!c)+d";
        System.out.println(expr);
        System.out.println(Caonical.getSumOfProductsString(expr,true));
        System.out.println(Caonical.getProductOfSumsString(expr,true));
        System.out.println(Caonical.getReducedSumOfProductsString(expr,true));
        System.out.println(Caonical.getReducedProductOfSumsString(expr,true));


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
