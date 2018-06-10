package oop.ex6.validations;

abstract public class Scope implements Validable {

    public abstract boolean isValid() throws BadFormatException;
}
