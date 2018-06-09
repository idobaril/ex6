package oop.ex6.validations;

/**
 * This is an abstract exception class used by all inheriting classes.
 */
public abstract class BadFormatException extends Exception {

    /**The problematic line.*/
    protected String line;

    /**Default message that is thrown with the exception.*/
    protected static final String ERROR_MSG = "ERROR: in line: ";
    private static final long serialVersionUID = 1L;

    /**
     * Constructor of BadFormatException.
     * @param line The problematic line.
     */
    BadFormatException(String line){
        this.line = line;
    }

    /**
     * Returns an informative message with the specific line.
     * @return an informative message with the specific line.
     */
    public String getMessage(){
        return ERROR_MSG + line;
    }

}
