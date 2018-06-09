package oop.ex6.validations;

/**
 * This is an abstract exception class used by all inheriting classes.
 */
public abstract class BadFormatException extends Exception {

    /**Default message that is thrown with the exception.*/
    protected static final String ERROR_MSG = "ERROR: in line: ";
    private static final long serialVersionUID = 1L;
}
