package oop.ex6.validations;

import oop.ex6.main.Sjavac;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Method extends Scope implements Validable{

    /**The name of the method.*/
    private String name;

    /**The arguments of this method's input.*/
    private LinkedList<String> methodArguments = new LinkedList<>();

    /**Holds a list of global variables declared in main.*/
    private ArrayList<String> globalVarList = null;

    /**Holds a list of all variables known to the method (local and global).*/
    private ArrayList<String> allVarList = null;

    /**Holds a list of the methods declared in the code, including their signatures.*/
    static private ArrayList<String> methodList= null;

    /**Regex for the method name.*/
    static private String methodNameRegex = "\\s*void\\s+([a-zA-Z]+\\w*)";

    /**Regex for the signature of the method.*/
    static private String methodSignatureRegex =
            "\\(((?:"+variableInMethodSignatureRegex+",)*"+
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
     * Checks the signature of a method. If it is valid, saves all the input arguments in a linked list.
     * Also, saves the method name in a linked list.
     * @param firstLine The String representing the method signature.
     * @throws BadFormatException Exception.
     */
    private void checkMethodSignature(String firstLine) throws BadFormatException{
        Matcher m = signaturePattern.matcher(firstLine);
        if(!m.matches()){
            throw new BadMethodSignature(firstLine);
        }
        else{
            name = m.group(1);
            String[] methodSignature = m.group(2).split(",");
            for (String arg : methodSignature){
                Matcher matcher = Pattern.compile(variableTypeRegex).matcher(arg);
                matcher.find();
                String type = matcher.group(1);
                methodArguments.add(type);
            }
        }
//        CHECKS THAT ARGUMENTS ARE READ CORRECTLY
//        System.out.println("printing method arguments:");
//        for (String item : methodArguments){
//            System.out.println(item);
//        }
//        System.out.println("\n");
    }

    /**
     * Returns the name of the method.
     * @return The name of the method.
     */
    public String getName(){
        return name;
    }

    /**
     * Checks the method body.
     * @throws UnsupportedOperation exception.
     */
    private void checkMethodBody() throws BadFormatException{
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
                throw new UnsupportedOperation(curLine);
            }
        }
    }

    private int checkIfBlock(int j) {
        return -1;
    }

    private int checkWhileBlock(int j) {
        return -1;
    }

    private static void checkVarDeclaration(String curLine) {
//        new Variable(curLine).isValid();
    }

    private void checkVarAssignment(String curLine) {
//        new Variable(curLine).isValid();
    }

    /**
     * Checks if the method call syntax is correct
     * @param curLine The string representing the method call.
     * @throws BadFormatException Exception.
     */
    private void checkMethodCall(String curLine) throws BadFormatException{
        Matcher matcher = Pattern.compile(methodNameRegex).matcher(curLine);
        matcher.lookingAt();
        String methodName = matcher.group(1);
        methodExists(methodName, curLine);
        String[] arguments = matcher.group(2).split(",");
        isMethodCallValid(arguments, curLine);
    }

    /**
     * Checks if a method exists.
     * @param methodName The method name.
     * @param line The line in which the method is called.
     * @throws BadFormatException Exception.
     */
    private void methodExists(String methodName, String line) throws BadFormatException{
        if (!Sjavac.methods.contains(methodName)){
            throw new UnknownMethodException(line);
        }
    }

    public boolean isValid() throws BadFormatException{
        checkMethodSignature(scope.get(0));
        System.out.println("signature ok");
        checkMethodBody();
        return true;
    }

    /**
     * Checks if the methoc call is valid, by comparing amount and type of arguments.
     * @param methodCallArgs The arguments in the method call.
     * @param line The line in which the method is called.
     * @throws BadFormatException Exception.
     */
    private void isMethodCallValid(String[] methodCallArgs, String line) throws BadFormatException{
        if (methodCallArgs.length != methodArguments.size()){
            throw new WrongNumOfArgumentsException(line);
        }
        for (int j=0; j<methodCallArgs.length; j++){
            if (!methodCallArgs[j].equals(methodArguments.get(j))){
                throw new WrongVariableTypeException(line);
            }
        }
    }

}
