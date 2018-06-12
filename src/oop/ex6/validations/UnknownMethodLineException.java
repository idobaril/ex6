package oop.ex6.validations;

/**
 * This exception is thrown when there's an unknown line inside the scope of a method.
 */
class UnknownMethodLineException extends BadFormatException{

    UnknownMethodLineException(String line){
        super(line);
    }
}
