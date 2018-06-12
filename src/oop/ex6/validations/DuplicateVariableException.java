package oop.ex6.validations;

public class DuplicateVariableException extends BadFormatException{
    public DuplicateVariableException(String firstLine) {
        super(firstLine);
    }
}
