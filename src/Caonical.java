import tokens.LogToken;

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



}
