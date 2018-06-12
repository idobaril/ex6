package oop.ex6.main;

import oop.ex6.validations.*;

import java.util.LinkedList;
import java.util.regex.Pattern;

class ValidationsFactory {
    private static ValidationsFactory ourInstance = new ValidationsFactory();

    private LinkedList<Method> methodObjects;

    private LinkedList<Comment> commentsObjects;

    private LinkedList<Variable> variableObjects;

    private static Pattern endsWithOpenBracket = Pattern.compile(".*\\{\\s*$");

    private static Pattern endsWithCloseBracket = Pattern.compile(".*\\}\\s*$");

    private static Pattern commentLine = Pattern.compile("^\\\\.*");

    static ValidationsFactory getInstance() {
        return ourInstance;
    }

    private ValidationsFactory() {
        methodObjects = new LinkedList<>();
        commentsObjects = new LinkedList<>();
        variableObjects = new LinkedList<>();
    }

    private int getEndOfScope (LinkedList<String> fileLines, int startIndex)throws MethodBracketsException{
        int  bracketCounter = 1;
        for (int index = startIndex + 1; index < fileLines.size() ; index++){
            if (endsWithCloseBracket.matcher(fileLines.get(index)).lookingAt()){
                bracketCounter --;
            }
            else if (endsWithOpenBracket.matcher(fileLines.get(index)).lookingAt()){
                bracketCounter ++;
            }
            if (bracketCounter == 0){
                return index;
            }
        }
        // If we get here, it means the method was never closed.
        throw new MethodBracketsException(fileLines.get(startIndex));
    }


    private void createValidationsObjects(LinkedList<String> fileLines) throws MethodBracketsException{
        for (int index = 0; index < fileLines.size(); index ++){
            if (endsWithOpenBracket.matcher(fileLines.get(index)).lookingAt()) {
                try {
                    int endOfScope = getEndOfScope(fileLines, index)+1;
                    Method newMethod = new Method(fileLines.subList(index, endOfScope));
                    try {
                        newMethod.isValid();
                    }
                    catch (Exception e){
                        System.out.println("CAUGHTTTTTTTTTTTTTTTTTTT");
                    }
                    methodObjects.add(newMethod);
                    index = endOfScope;
                } catch (MethodBracketsException e) {
                    throw e;
                }
            }
            else if (commentLine.matcher(fileLines.get(index)).lookingAt()){
                Comment newComment = new Comment(fileLines.get(index));
                commentsObjects.add(newComment);
            }
            else{
                Variable newVariable = new Variable(fileLines.get(index));
                variableObjects.add(newVariable);
            }
        }
    }


    CodeManager getCodeManager(LinkedList<String> fileLines) throws MethodBracketsException{
        try {
            createValidationsObjects(fileLines);
        }
        catch (MethodBracketsException e){
            throw e;
        }
        return  new CodeManager(methodObjects, commentsObjects, variableObjects);
    }
}
