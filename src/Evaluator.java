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

        //Set initial inputs to all false
        for(int i=0;i<inputs.length;i++)
        {
            inputs[i] = false;
        }

        /*
        Magic Function
        vertPos%((numVars+1)/(2*(horzPos+1))) == 0 ?
        T -> F
        F -> T
        */

        for(int vertPos=0;vertPos<Math.pow(2,numVars);vertPos++)
        {
            for(int horzPos=0;horzPos<numVars;horzPos++)
            {
                if(vertPos%(Math.pow(2,numVars)/(2*(horzPos+1))) == 0) // This works I swear
                {
                    inputs[horzPos] = !inputs[horzPos];
                }
            }

            //Display Row Stuff
            for(int i=0;i<inputs.length;i++)
            {
                System.out.print(variables.get(i)+":"+inputs[i]+ "\t");
            }
            System.out.println("result:" + evaluatePostfixExpression(expression,inputs));
        }

    }
}
