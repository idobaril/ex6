package oop.ex6.main;


import oop.ex6.validations.BadFormatException;
import oop.ex6.validations.Method;
import oop.ex6.validations.Variable;

import javax.xml.stream.events.Comment;
import java.util.Collection;

public class CodeManager {
    static private Collection<Method> MethodObjects;

    static private Collection<Comment> commentsObjects;

    static private Collection<Variable> variableObjects;

    public static Collection<Method> getMethodObjects() {
        return MethodObjects;
    }

    public static void setMethodObjects(Collection<Method> methodObjects) {
        MethodObjects = methodObjects;
    }

    public static Collection<Comment> getCommentsObjects() {
        return commentsObjects;
    }

    public static void setCommentsObjects(Collection<Comment> commentsObjects) {
        CodeManager.commentsObjects = commentsObjects;
    }

    public static Collection<Variable> getVariableObjects() {
        return variableObjects;
    }

    public static void setVariableObjects(Collection<Variable> variableObjects) {
        CodeManager.variableObjects = variableObjects;
    }

    CodeManager (Collection scopes, Collection comments, Collection variables){
        MethodObjects = scopes;
        commentsObjects = comments;
        variableObjects = variables;
    }

    int runValidations(){
        /*
        for (Variable var : variableObjects){
            try {
                var.isValid();
            }
            catch (BadFormatException e){
                System.err.println(e.getMessage());
                return 1;
            }
        }

        for (Scope scope : scopeObjects){
            try {

            }
            catch ()
        }
*/
        return 0;
    }


}