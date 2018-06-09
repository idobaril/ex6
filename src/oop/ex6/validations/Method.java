package oop.ex6.validations;

import oop.ex6.main.Sjavac;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Method extends Scope implements Validable{

    private ArrayList<String> varList = null;

    private ArrayList<String> methodVarList = null;

    static private ArrayList<String> methodList= null;

    static private String methodSignatureRegex =
            "\\s*void\\s+[a-zA-Z]+\\w*\\((("+variableRegex+",)*"+variableRegex+")?\\)\\s*\\{\\s*";

    private static Pattern signaturePattern = Pattern.compile(methodSignatureRegex);

    private static Pattern ifBlockPattern = Pattern.compile("\\s*if\\s*\\(");
    private static Pattern whileBlockPattern = Pattern.compile("\\s*while\\s*\\(");
    private static Pattern varDeclarationPattern = Pattern.compile("\\s*(final\\s*)?"+Sjavac.VARIABLE_TYPES+"\\s+");
    private static Pattern returnPattern = Pattern.compile("\\s*return\\s*;\\s*");
    private static Pattern methodCallPattern = Pattern.compile("\\s*"+methodList+"\\s*\\(");
    private Pattern varAssignmentPattern = Pattern.compile("\\s*"+methodVarList+"\\s*=");

    enum LineType{VAR_DECLARATION, VAR_ASSIGNMENT, METHOD_CALL, IF_BLOCK, WHILE_BLOCK, RETURN}


    Method(List<String> scope){
        super(scope);
    }

    private void checkMethodSignature(String firstLine) throws BadFormatException{
        Matcher m = signaturePattern.matcher(firstLine);
        if(!m.matches()){
            throw new BadMethodSignature(firstLine);
        }
    }

    private void checkMethodBody() throws UnknownMethodLineException{
        int j = 1;
        String curLine;
        while (j < scope.size()){
            curLine = scope.get(j);
            if(ifBlockPattern.matcher(curLine).lookingAt()){
                j = checkIfBlock(j);
                j++;
            }
            else if(whileBlockPattern.matcher(curLine).lookingAt()){
                j = checkWhileBlock(j);
                j++;
            }
            else if(varDeclarationPattern.matcher(curLine).lookingAt()){
                checkVarDeclaration(curLine);
                j++;
            }
            else if(varAssignmentPattern.matcher(curLine).lookingAt()){
                checkVarAssignment(curLine);
                j++;
            }
            else if(methodCallPattern.matcher(curLine).lookingAt()){
                checkMethodCall(curLine);
                j++;
            }
            else if(returnPattern.matcher(curLine).lookingAt()){
                j++;
            }

            else{
                throw new UnknownMethodLineException(curLine);
            }
        }
    }

    private int checkIfBlock(int j) {
        return -1;
    }

    private int checkWhileBlock(int j) {
        return -1;
    }

    private void checkVarDeclaration(String curLine) {
    }

    private void checkVarAssignment(String curLine) {
    }

    private void checkMethodCall(String curLine) {
    }

    public boolean isValid() throws BadFormatException{
        checkMethodSignature(scope.get(0));
        checkMethodBody();
        return true;
    }

}
