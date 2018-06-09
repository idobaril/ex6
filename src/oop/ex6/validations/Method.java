package oop.ex6.validations;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Method extends Scope implements Validable{

    static private String methodSignatureRegex =
            "\\s*void\\s+[a-zA-Z]+\\w*\\((("+variableRegex+",)*"+variableRegex+")?\\)\\s*\\{\\s*";

    private static Pattern signaturePattern = Pattern.compile(methodSignatureRegex);

    Method(List<String> scope){
        super(scope);
    }

    private void checkMethodSignature(String firstLine) throws BadFormatException{
        Matcher m = signaturePattern.matcher(firstLine);
        if(!m.matches()){
            throw new BadMethodSignature(firstLine);
        }
    }

    public boolean isValid() throws BadFormatException{
        checkMethodSignature(scope.get(0));

        return true;
    }

}
