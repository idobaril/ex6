package oop.ex6.validations;

class UnknownMethodLineException extends BadFormatException{

    UnknownMethodLineException(String line){
        super(line);
    }
}
