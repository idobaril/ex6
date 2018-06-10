package oop.ex6.main;

import oop.ex6.validations.Scope;
import oop.ex6.validations.Variable;

import javax.xml.stream.events.Comment;
import java.util.Collection;

class ValidationsFactory {
    private static ValidationsFactory ourInstance = new ValidationsFactory();

    private Collection<Scope> scopeObjects;

    private Collection<Comment> commentsObjects;

    private Collection<Variable> variableObjects;

    private CodeManager codeManager = new CodeManager(scopeObjects, commentsObjects, variableObjects);


    static ValidationsFactory getInstance() {
        return ourInstance;
    }


    private ValidationsFactory() {
    }


    private void createValidationsObjects(Collection<String> fileLines){


    }




    CodeManager getCodeManager(Collection<String> fileLines){
        createValidationsObjects(fileLines);
        return codeManager;
    }
}
