package oop.ex6.validations;

public class WrongVariableTypeException extends BadFormatException{
    public WrongVariableTypeException(String line) {
        super(line);
    }
}
