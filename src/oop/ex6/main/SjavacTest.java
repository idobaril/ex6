package oop.ex6.main;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class SjavacTest {


    @Test
    public void regexTest(){
        String line = "a";
        Pattern p = Pattern.compile("\\s*");
        Matcher m = p.matcher(line);
        System.out.println("trying to match line " + line);
        if (m.matches()){
            System.out.println(line + " an empty line! ");
        }

    }
}