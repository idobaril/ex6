package oop.ex6.validations;

public class CodeLine implements Validable{

    private String codeLine;

    public CodeLine(String stringCodeLine){
        codeLine = stringCodeLine;
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
