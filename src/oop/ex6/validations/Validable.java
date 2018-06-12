package oop.ex6.validations;

/**
 * This interface is implemented by all oop.ex6.validations classes. It has one method, isValid, which
 * returns a boolean answer of whether the syntax of the code is valid.
 */
public interface Validable {

    /**
     * Checks whether the syntax is valid.
     * @return True if valid.
     * @throws BadFormatException Exception.
     */
    boolean isValid() throws BadFormatException;


}
