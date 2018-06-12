package oop.ex6.validations;

import oop.ex6.main.Sjavac;

import java.util.regex.Pattern;

/**
 * An abstract static class containing all the regexes used in the code.
 */
abstract class Regex {

    //VARIABLE-RELATED REGEX

    /** Regex of the a variable type only (without name)*/
    static Pattern variableType =  Pattern.compile("\\s*(final\\s+)?"+Sjavac.VARIABLE_TYPES+"\\s+");

    /**Regex for a single variable inside the method signature.*/
    static Pattern variableInMethodSignature = Pattern.compile(variableType +"((_\\w+)|([a-zA-Z]+))\\s*");

    /**Regex for variable declaration. THIS STILL NEEDS TO BE WORKED ON*/
    static Pattern varDeclaration = null;

    /**Regex for variable assignment. THIS STILL NEEDS TO BE WORKED ON*/
    static Pattern varAssignment = null;



    /**Regex for the method name.*/
    static Pattern methodName = Pattern.compile("\\s*void\\s+([a-zA-Z]+\\w*)\\s*");


//    static Pattern methodCall = Pattern.compile(methodName)

    /**Regex for the signature of the method.*/
    static Pattern signaturePattern = Pattern.compile(methodName
            + "\\(((?:"+variableInMethodSignature+",)*"+
            variableInMethodSignature+")?\\)\\s*\\{\\s*");

    /**Regex for an if block.*/
    static Pattern ifBlock = Pattern.compile("\\s*if\\s*\\(");

    /**Regex for a while block.*/
    static Pattern whileBlock = Pattern.compile("\\s*while\\s*\\(");


    /**Regex for return.*/
    static Pattern _return= Pattern.compile("\\s*return\\s*;\\s*");

    /**Regex for method call.*/
    static Pattern methodCall = Pattern.compile("\\s*"+Sjavac.methodList+"\\s*\\(");

}
