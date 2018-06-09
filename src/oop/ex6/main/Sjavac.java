package oop.ex6.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Sjavac {

    private static final int PATH_ARG = 0;
    private static final int LEGAL_CODE = 0;
    private static final int ILLEGAL_CODE = 1;
    private static final int EXCEPTION_THROWN = 2;
    private static final String BAD_FILE = "ERROR: ILLEGAL FILE";
    public static final String VARIABLE_TYPES ="(int|double|boolean|char|String)";


    /**
     * Checks if a line is an empty line using regex.
     * @param line The line to be checked.
     * @param p The regex pattern.
     * @return True if line is empty, false otherwise.
     */
    static private boolean isLineEmpty(String line, Pattern p){
        Matcher m = p.matcher(line);
        return m.matches();
    }


    /**
     * Parses a file from the path given by a user. Returns an ArrayList of strings of the code lines,
     * without empty lines.
     * @param path The path given by the user.
     * @return An array list of strings representing the code.
     */
    static private Collection<String> parseFile (String path){
        Pattern p = Pattern.compile("\\s*"); //Empty line regex
        Collection<String> codeLines = new ArrayList<>();
        try {
            BufferedReader newBuff = new BufferedReader(new FileReader(path));
            String line = newBuff.readLine();
            while (line != null) {
                if (!isLineEmpty(line, p)){
                    codeLines.add(line);
                }
                line = newBuff.readLine();
            }
            newBuff.close();
        } catch (Exception IOException) {
            System.out.println(EXCEPTION_THROWN);
            System.err.println(BAD_FILE);
        }
        return codeLines;
    }


    public static void main(String[] args) {
        Collection<String> fileLines = parseFile(args[PATH_ARG]);
//        ValidationsFactory factory = ValidationsFactory.getInstance();
//        CodeManager codeManager = factory.getCodeManager();
//        codeManager.runValidations();
    }
}
