import tokens.LogToken;

import java.util.ArrayList;

public class Main
{

    public static void main(String[] args)
    {
        String expr = "c*!(a+b)";
        ArrayList<LogToken> chips = Parser.parseString(expr);

        System.out.println(expr);

        for(LogToken chip : chips)
        {
            //System.out.println(chip.getTokenType());
        }

        ArrayList<LogToken> dips = Parser.convertToPostfix(chips);

        /*
        for(LogToken chip : dips)
        {
            System.out.print(chip.toString()+" ");
        }
        System.out.println("");
        */

        ;
        ArrayList<String> asd = Evaluator.getLexographicalOrderOfVariables(chips);
        /*
        for(String s : asd )
        {
            System.out.println(s);
        }
        */

        boolean[] inputs = {true,true,true};
        //System.out.println("a = true    b = true  c = true");
        //System.out.println(Evaluator.evaluatePostfixExpression(dips,inputs));
        Evaluator.displayTruthTable(dips);
    }

}
