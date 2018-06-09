package oop.ex6.validations;

class BadMethodSignature extends BadFormatException{

    /**The problematic line.*/
    private String line;

    /**
     * Constructor of BadMethodSignature.
     * @param line The problematic line.
     */
    BadMethodSignature(String line){
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
