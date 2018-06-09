package oop.ex6.main;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class SjavacTest {

    @Test
    public void variableTypeTest(){
        String line;
        Matcher m;
        Pattern p = Pattern.compile(Sjavac.VARIABLE_TYPES);

        line = "int __";
        m = p.matcher(line);
        assertFalse(m.matches());

        line = "int";
        m = p.matcher(line);
        assertTrue(m.matches());

        line = "double";
        m = p.matcher(line);
        assertTrue(m.matches());

        line = "char";
        m = p.matcher(line);
        assertTrue(m.matches());

        line = "boolean";
        m = p.matcher(line);
        assertTrue(m.matches());

        line = "String";
        m = p.matcher(line);
        assertTrue(m.matches());

        line = " double";
        m = p.matcher(line);
        assertFalse(m.matches());

        line = "string";
        m = p.matcher(line);
        assertFalse(m.matches());

        line = "int __";
        m = p.matcher(line);
        assertFalse(m.matches());

        line = "intdouble";
        m = p.matcher(line);
        assertFalse(m.matches());
    }


    @Test
    public void regexVariableTest(){
        String line;
        Matcher m;
        Pattern p = Pattern.compile("\\s*"+ Sjavac.VARIABLE_TYPES+"\\s+((_\\w)|([a-zA-Z]+))");
        line = "int __";
        m = p.matcher(line);
        assertTrue(m.matches());

        line = " int _a";
        m = p.matcher(line);
        assertTrue(m.matches());

        line = "    int _Z";
        m = p.matcher(line);
        assertTrue(m.matches());

        line = "String _";
        m = p.matcher(line);
        assertFalse(m.matches());

        line = "double _1";
        m = p.matcher(line);
        assertTrue(m.matches());

        line = "char name";
        m = p.matcher(line);
        assertTrue(m.matches());

        line = "boolean i";
        m = p.matcher(line);
        assertTrue(m.matches());


    }

    @Test
    public void methodSignatureTest(){
        String variableRegex = "\\s*"+Sjavac.VARIABLE_TYPES+"\\s+((_\\w)|([a-zA-Z]+))\\s*";
        String lineSignatureRegex = "\\s*void\\s+[a-zA-Z]+\\w*\\((("+variableRegex+",)*"+variableRegex+")?\\)\\s*\\{\\s*";
        String line;
        Matcher m;
        Pattern p = Pattern.compile(lineSignatureRegex);

        line = "void foo(){";
        m = p.matcher(line);
        assertTrue(m.matches());

        line = "void a(int c, double bl,boolean cd){";
        m = p.matcher(line);
        assertTrue(m.matches());

        line = "void foo()";
        m = p.matcher(line);
        assertFalse(m.matches());

        line = "   void   foo   (   char _a,    String   h1     , )    {      ";
        m = p.matcher(line);
        assertFalse(m.matches());

        line = "void f o o(){";
        m = p.matcher(line);
        assertFalse(m.matches());

        line = "void a(int c, doublebl, boolean cd){";
        m = p.matcher(line);
        assertFalse(m.matches());

 }
}