import tokens.LogToken;
import tokens.Operator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by dan on 2/5/15.
 */
public class Evaluator
{
    public static boolean evaluatePostfixExpression(ArrayList<LogToken> expression, final boolean[] inputs)
    {
        // Note, if inputs and orderedVariables have different lengths we are going to have a problem
        final ArrayList<String> orderedVariables = getLexographicalOrderOfVariables(expression);

        Stack<Boolean> evalStack = new Stack<Boolean>();

        for(LogToken token : expression)
        {
            if(token.getTokenType().equals("VariableToken"))
            {
                int index = orderedVariables.indexOf(token.toString());
                evalStack.add(inputs[index]);
            }
            else
            {
                int stackConsumption = ((Operator)token).expectedParameters();
                boolean[] fromStack = new boolean[stackConsumption];
                while(stackConsumption>0)
                {
                    fromStack[stackConsumption-1] = evalStack.pop();
                    --stackConsumption;
                }
                evalStack.add(((Operator)token).evaluate(fromStack));
            }
        }

        //The last thing on the stack will be the solution
        return evalStack.pop();
    }

    public static ArrayList<String> getLexographicalOrderOfVariables(ArrayList<LogToken> expression)
    {
        ArrayList<String> orderedVariableNames = new ArrayList<String>();
        for(LogToken token : expression)
        {
            if(token.getTokenType().equals("VariableToken"))
            {
                if(!orderedVariableNames.contains(token.toString()))
                {
                    orderedVariableNames.add(token.toString());
                }
            }
        }

        Collections.sort(orderedVariableNames);

        return orderedVariableNames;
    }

    public static void displayTruthTable(ArrayList<LogToken> expression)
    {
        ArrayList<String> variables =  getLexographicalOrderOfVariables(expression);
        int numVars = variables.size();
        boolean inputs[] = new boolean[numVars];

    }
}
