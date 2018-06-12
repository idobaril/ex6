package oop.ex6.validations;

import java.util.LinkedList;

public class Variable implements Validable{

    String type, name, value;
//    LinkedList<Variable> localVarList = new LinkedList<>();
    boolean isFinal;

    public Variable(String st){

    }


    Variable(boolean isFinal, String type, String name, String value){//, LinkedList<Variable> localVarList){
        this.isFinal = isFinal;
        this.type = type;
        this.name = name;
        this. value = value;
//        this.localVarList = localVarList;
    }

    Variable(boolean isFinal, String type, String name){//, LinkedList<Variable> localVarList){
        this.isFinal = isFinal;
        this.type = type;
        this.name = name;
        this. value = null;
//        this.localVarList = localVarList;
    }

//    Variable(boolean isFinal, String type, String name){
//        this.type = type;
//        this.name = name;
//        this. value = null;
////        this.localVarList = localVarList;
//        this.isFinal = isFinal;
//    }

    public boolean isValid(){return true;}

}
