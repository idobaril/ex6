package oop.ex6.validations;

import oop.ex6.main.Sjavac;

import java.util.*;

abstract public class Scope {

    /**Holds a list of Strings representing the current scope.*/
    protected List<String> scope;


    Scope(List<String> scope){
        this.scope = scope;
    }

}
