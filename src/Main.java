import java.util.ArrayList;
import java.util.Stack;

public class Main
{

    public static void main(String[] args)
    {
        ArrayList<LogToken> chips = Parser.parseString("c*!(a+b)");
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
