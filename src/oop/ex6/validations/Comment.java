package oop.ex6.validations;

public class Comment implements Validable{

    private String comment;

    public Comment(String commentString){
        comment = commentString;
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
