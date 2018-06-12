package oop.ex6.validations;

import oop.ex6.main.CodeManager;
import oop.ex6.main.Sjavac;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Method extends Scope implements Validable{

    /**The name of the method.*/
    private String name;

    /**The arguments of this method's input.*/
    private LinkedList<Variable> methodArguments = new LinkedList<>();

    /**Holds a list of all variables known to the method (local and global).*/
    private ArrayList<String> allVarList = Sjavac.globalVarList;

    /**List of variables that are local with respect to the method's scope.*/
    private LinkedList<Variable> localVarList = new LinkedList<>();

    public Method(List<String> scope){
        super(scope);
    }


    public boolean isValid() throws BadFormatException{
        for (String line : scope){
            System.out.println(line);
        }
        System.out.println("\n");
        checkMethodSignature(scope.get(0));
        System.out.println("signature okay");
        checkMethodBody();
        return true;
    }

    /**
     * Overriding object's equals to compare method names.
     * @param obj The object to compare to.
     * @return true if name is the same, false if name is different or if obj isn't a Method object..
     */
    public boolean equals(Object obj){
        boolean answer;
        try{
            answer = this.name.equals(obj);
        }
        catch (Exception e){
            return false;
        }
        return answer;
    }



    private void parseMethodArgs(String[] args, String line) throws BadFormatException{
        for (String arg : args){
            Matcher matcher = Regex.variableInMethodSignature.matcher(arg);
            matcher.find();
            boolean isFinal = false;
            String finalArg = matcher.group(1);
            if (finalArg !=null){
                isFinal = true;
            }
            String type = matcher.group(2);
            String name = matcher.group(3);
            Variable localVar = new Variable(isFinal, type, name);
            methodArguments.add(localVar);
            if (localVarList.contains(localVar)){
                throw new DuplicateVariableException(line);
            }
            localVarList.add(localVar);
        }
    }

    /**
     * Checks the signature of a method. If it is valid, saves all the input arguments in a linked list.
     * Also, saves the method name in a linked list.
     * @param firstLine The String representing the method signature.
     * @throws BadFormatException Exception.
     */
    private void checkMethodSignature(String firstLine) throws BadFormatException{
        Matcher m = Regex.signaturePattern.matcher(firstLine);
        if(!m.matches()){
            throw new BadMethodSignature(firstLine);
        }
        else{
            name = m.group(1);
            String[] methodArguments= m.group(2).split(",");
            parseMethodArgs(methodArguments, firstLine);
        }
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
            if(Regex.ifBlock.matcher(curLine).lookingAt()){
//                j = checkIfBlock(j);
                j++;
            }
            else if(Regex.whileBlock.matcher(curLine).lookingAt()){
//                j = checkWhileBlock(j);
                j++;
            }
            else if(Regex.varDeclaration.matcher(curLine).lookingAt()){
                System.out.println("HERE");
                checkVarDeclaration(curLine);
                j++;
            }
            else if(Regex.varAssignment.matcher(curLine).lookingAt()){
                checkVarAssignment(curLine);
                j++;
            }
            else if(Regex.methodCall.matcher(curLine).lookingAt()){
                System.out.println("METHOD CALL");
                checkMethodCall(curLine);
                j++;
            }
            else if(Regex._return.matcher(curLine).lookingAt()){
                j++;
            }

            else{
//                System.out.println("ARE YOU HERE");
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

    private void checkVarDeclaration(String curLine) {
//        Variable variable = new Variable(curLine);
//        variable.isValid();
//        allVarList.add(variable);
    }

    private void checkVarAssignment(String curLine) {
//        String varName = FIND VARIABLE NAME
//        variableExists(varName, curLine);
//        newVal = FIND NEW ASSIGNMENT ARGUMENT
//        variable.setVal(newVal);
//        variable.isValid();
    }

    /**
     * Checks if the method call syntax is correct
     * @param curLine The string representing the method call.
     * @throws BadFormatException Exception.
     */
    private void checkMethodCall(String curLine) throws BadFormatException{
        Matcher matcher = Regex.methodName.matcher(curLine);
        matcher.lookingAt();
        String methodName = matcher.group(1);
        methodExists(methodName, curLine);
        //THIS IS FALSE - REGEX FOR METHOD CALL SHOULD BE DIFFERENT
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
        if (!CodeManager.getMethodObjects().contains(methodName)){
            throw new UnknownMethodException(line);
        }
    }

    /**
     * Checks if the method call is valid, by comparing amount and type of arguments.
     * @param methodCallArgs The arguments in the method call.
     * @param line The line in which the method is called.
     * @throws BadFormatException Exception.
     */
    private void isMethodCallValid(String[] methodCallArgs, String line) throws BadFormatException{
        if (methodCallArgs.length != methodArguments.size()){
            throw new WrongNumOfArgumentsException(line);
        }
        for (int j=0; j<methodCallArgs.length; j++){
            if (!methodCallArgs[j].equals(methodArguments.get(j).type)){
                throw new WrongVariableTypeException(line);
            }
        }
    }

}
