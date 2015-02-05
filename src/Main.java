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

        for(LogToken chip : dips)
        {
            System.out.print(chip.toString()+" ");
        }
    }
}
