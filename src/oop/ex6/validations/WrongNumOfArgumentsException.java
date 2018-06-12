package oop.ex6.validations;

public class WrongNumOfArgumentsException extends BadFormatException {

    WrongNumOfArgumentsException(String line){
        super(line);
    }

}
