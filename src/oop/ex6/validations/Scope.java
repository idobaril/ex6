package oop.ex6.validations;

import oop.ex6.main.Sjavac;

import java.util.*;

abstract public class Scope {

    /**Holds a list of Strings representing the current scope.*/
    protected List<String> scope;

    /** Regex of the a variable type only (without name)*/
    protected static String variableTypeRegex =  "\\s*(final\\s+)?"+Sjavac.VARIABLE_TYPES+"\\s+";

    /**Regex for a single variable inside the method signature.*/
    protected static String variableInMethodSignatureRegex = variableTypeRegex +"((_\\w)|([a-zA-Z]+))\\s*";

    Scope(List<String> scope){
        this.scope = scope;
    }

}
