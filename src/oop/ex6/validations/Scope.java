package oop.ex6.validations;

import oop.ex6.main.Sjavac;

import java.util.*;

abstract public class Scope {

    protected List<String> scope;

    protected static String variableRegex = "\\s*"+Sjavac.VARIABLE_TYPES+"\\s+((_\\w)|([a-zA-Z]+))\\s*";

    Scope(List<String> scope){
        this.scope = scope;
    }

}
