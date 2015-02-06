import tokens.LogToken;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by dan on 2/6/15.
 */
public class Caonical
{
    public static String getSumOfProductsString(String expression)
    {
        return getSumOfProductsString(expression,true);
    }
    public static String getSumOfProductsString(String expression, boolean explicitAnd)
    {
        ArrayList<LogToken> tokenExpression = Parser.parseString(expression);
        ArrayList<LogToken> postfixExpression = Parser.convertToPostfix(tokenExpression);
        ArrayList<boolean[]> truthTable = Evaluator.generateTruthTable(postfixExpression);
        ArrayList<String> variables = Evaluator.getLexographicalOrderOfVariables(postfixExpression);

        String SoP = "";

        for(boolean[] line : truthTable)
        {
            if(line[line.length-1])
            {
                if(SoP.length() > 0)
                {
                    SoP += "+";
                }

                if(line[0] == true)
                {
                    SoP += "(" + variables.get(0);
                }
                else
                {
                    SoP += "(!" + variables.get(0);
                }

                for(int i=1;i<line.length-1;i++)
                {
                    if(explicitAnd)
                    {
                        SoP += "*";
                    }
                    if(line[i] == true)
                    {
                        SoP += "" + variables.get(i);
                    }
                    else
                    {
                        SoP +=  "!" + variables.get(i);
                    }
                }

                SoP += ")";
            }

        }
        return SoP;
    }

    public static String getProductOfSumsString(String expression)
    {
        return getSumOfProductsString(expression,true);
    }
    public static String getProductOfSumsString(String expression, boolean explicitAnd)
    {
        ArrayList<LogToken> tokenExpression = Parser.parseString(expression);
        ArrayList<LogToken> postfixExpression = Parser.convertToPostfix(tokenExpression);
        ArrayList<boolean[]> truthTable = Evaluator.generateTruthTable(postfixExpression);
        ArrayList<String> variables = Evaluator.getLexographicalOrderOfVariables(postfixExpression);

        String PoS = "";

        for(boolean[] line : truthTable)
        {
            if(!line[line.length-1])
            {
                if(PoS.length() > 0 && explicitAnd)
                {
                    PoS += "*";
                }

                if(line[0] == false)
                {
                    PoS += "(" + variables.get(0);
                }
                else
                {
                    PoS += "(!" + variables.get(0);
                }

                for(int i=1;i<line.length-1;i++)
                {

                    PoS += "+";

                    if(line[i] == false)
                    {
                        PoS += "" + variables.get(i);
                    }
                    else
                    {
                        PoS +=  "!" + variables.get(i);
                    }
                }
                PoS += ")";
            }

        }
        return PoS;
    }

    public static String getReducedSumOfProductsString(String expression)
    {
        return getReducedSumOfProductsString(expression, true);
    }
    public static String getReducedSumOfProductsString(String expression, boolean explicitAnd) {
        String reducedSoP = "";

        ArrayList<LogToken> tokenExpression = Parser.parseString(expression);
        ArrayList<LogToken> postfixExpression = Parser.convertToPostfix(tokenExpression);
        ArrayList<boolean[]> truthTable = Evaluator.generateTruthTable(postfixExpression);
        ArrayList<String> variables = Evaluator.getLexographicalOrderOfVariables(postfixExpression);

        ArrayList<boolean[]> trueLines = new ArrayList<boolean[]>();
        for (boolean[] line : truthTable) {
            if (line[line.length - 1]) {
                trueLines.add(line);
            }
        }

        ArrayList<String[]> modifiedInputs = new ArrayList<String[]>();
        for (boolean[] line : trueLines) {
            String[] modified = new String[line.length];
            for (int i = 0; i < modified.length; i++) {
                String convert = line[i] ? "true" : "false";
                modified[i] = convert;
            }
            modifiedInputs.add(modified);
        }


        boolean hasBeenChanges = true;
        while (hasBeenChanges == true)
        {

            hasBeenChanges = false;
            for (int i = 0; i < modifiedInputs.size(); i++) {
                for (int j = i + 1; j < modifiedInputs.size(); j++) {
                    int lineDiff = lineDifference(modifiedInputs.get(i), modifiedInputs.get(j));
                    if (lineDiff == 1) {
                        hasBeenChanges = true;

                        String[] simplified = simplifyLines(modifiedInputs.get(i), modifiedInputs.get(j));
                        modifiedInputs.set(i, simplified);
                        modifiedInputs.remove(j);
                    }
                }
            }
        }

        for(int i = 0; i < modifiedInputs.size(); i++)
        {
            reducedSoP += "(";
            boolean firstTerm = true;

            for (int j = 0; j < modifiedInputs.get(i).length-1; j++)
            {
                if(modifiedInputs.get(i)[j].equals("x"))
                {
                    // Do nothing
                }
                else
                {
                    if(firstTerm)
                    {
                        firstTerm = false;
                    }
                    else if(explicitAnd)
                    {
                        reducedSoP += "*";
                    }

                    if(modifiedInputs.get(i)[j].equals("true"))
                    {
                        reducedSoP += "" + variables.get(j);
                    }
                    else
                    {
                        reducedSoP += "!" + variables.get(j);
                    }
                }
            }
            reducedSoP += ")";
        }

        return reducedSoP;
    }
    private static int lineDifference(String[] line1,String[] line2)
    {
        int lineDifference = 0;
        for(int i=0;i<line1.length-1;i++)
        {
            if(line1[i].equals(line2[i])) // Weird edge cases will mess up here TODO think about this more
            {
                // Do nothing
            }
            else
            {
                lineDifference++;
            }
        }
        return lineDifference;
    }
    private static String[] simplifyLines(String[] line1,String[] line2)
    {
        String[] result = new String[line1.length];
        for(int i=0;i<result.length;i++)
        {
            if(line1[i].equals(line2[i]))
            {
                result[i] = line1[i];
            }
            else
            {
                result[i] = "x";
            }
        }
        return result;
    }



}
