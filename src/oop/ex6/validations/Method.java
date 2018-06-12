package oop.ex6.validations;

import oop.ex6.main.Sjavac;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Method extends Scope implements Validable{

    /**Holds a list of global variables declared in main.*/
    private ArrayList<String> globalVarList = null;

    /**Holds a list of all variables known to the method (local and global).*/
    private ArrayList<String> allVarList = null;

    /**Holds a list of the methods declared in the code, including their signatures.*/
    static private ArrayList<String> methodList= null;

    /**Regex for the signature of the method.*/
    static private String methodSignatureRegex =
            "\\s*void\\s+[a-zA-Z]+\\w*\\((("+variableInMethodSignatureRegex+",)*"+
                    variableInMethodSignatureRegex+")?\\)\\s*\\{\\s*";
    private static Pattern signaturePattern = Pattern.compile(methodSignatureRegex);

    /**Regex for an if block.*/
    private static Pattern ifBlockPattern = Pattern.compile("\\s*if\\s*\\(");

    /**Regex for a while block.*/
    private static Pattern whileBlockPattern = Pattern.compile("\\s*while\\s*\\(");

    /**Regex for variable declaration.*/
    private static Pattern varDeclarationPattern = Pattern.compile("\\s*(final\\s+)?"+Sjavac.VARIABLE_TYPES+"\\s+");

    /**Regex for return.*/
    private static Pattern returnPattern = Pattern.compile("\\s*return\\s*;\\s*");

    /**Regex for method call.*/
    private static Pattern methodCallPattern = Pattern.compile("\\s*"+methodList+"\\s*\\(");

    /**Regex for variable assignment.*/
    private Pattern varAssignmentPattern = Pattern.compile("\\s*"+allVarList+"\\s*=");

    enum LineType{VAR_DECLARATION, VAR_ASSIGNMENT, METHOD_CALL, IF_BLOCK, WHILE_BLOCK, RETURN}


    Method(List<String> scope){
        super(scope);
    }

    /**
     * Checks the signature of a method.
     * @param firstLine The String representing the method signature.
     * @throws BadFormatException Exception.
     */
    private void checkMethodSignature(String firstLine) throws BadFormatException{
        Matcher m = signaturePattern.matcher(firstLine);
        if(!m.matches()){
            throw new BadMethodSignature(firstLine);
        }
    }

    /**
     * Checks the method body.
     * @throws UnknownMethodLineException exception.
     */
    private void checkMethodBody() throws UnknownMethodLineException{
        int j = 1;
        String curLine;
        while (j < scope.size()-1){
            curLine = scope.get(j);
            System.out.println("curLine is " + curLine);
            if(ifBlockPattern.matcher(curLine).lookingAt()){
                j = checkIfBlock(j);
                j++;
            }
            else if(whileBlockPattern.matcher(curLine).lookingAt()){
                j = checkWhileBlock(j);
                j++;
            }
            else if(varDeclarationPattern.matcher(curLine).lookingAt()){
                System.out.println("HERE");
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
