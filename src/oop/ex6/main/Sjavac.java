package oop.ex6.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;

public class Sjavac {



    static private Collection<String> parseFile (String path){
        Collection<String> codeLines = new ArrayList<>();
        try {
            BufferedReader newBuff = new BufferedReader(new FileReader(path));
            String line = newBuff.readLine();
            while (line != null) {
                codeLines.add(line);
                line = newBuff.readLine();
            }
            newBuff.close();
        } catch (Exception IOException) {
            System.out.println("IO Exception");
        }
        return codeLines;
    }


    public static void main(String[] args) {
        Collection<String> fileLines = parseFile(args[0]);
        for (String line : fileLines){
            System.out.println(line);
        }
//        ValidationsFactory factory = ValidationsFactory.getInstance();
//        CodeManager codeManager = factory.getCodeManager();
//        codeManager.runValidations();
    }
}
