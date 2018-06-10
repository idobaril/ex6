package oop.ex6.main;

import java.util.Collection;

public class Sjavac {


    static Collection<String> parseFile(String path) {
        // try catch
        // open file
        // skip empty lines
        // return lines in collection
        return null;
    }



    public static void main(String[] args) {
        Collection<String> fileLines = parseFile(args[0]);
        ValidationsFactory factory = ValidationsFactory.getInstance();
        CodeManager codeManager = factory.getCodeManager(fileLines);
        int codeVerifyValue = codeManager.runValidations();

        System.out.println(codeVerifyValue);
    }
}