package oop.ex6.main;

import oop.ex6.validations.BadFormatException;
import oop.ex6.validations.Scope;
import oop.ex6.validations.Variable;

import javax.xml.stream.events.Comment;
import java.util.Collection;

class CodeManager {
    private Collection<Scope> scopeObjects;

    private Collection<Comment> commentsObjects;

    private Collection<Variable> variableObjects;

    CodeManager (Collection scopes, Collection comments, Collection variables){
        scopeObjects = scopes;
        commentsObjects = comments;
        variableObjects = variables;
    }

    int runValidations(){
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

    }


}
