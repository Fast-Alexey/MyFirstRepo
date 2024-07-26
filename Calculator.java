import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.in;


public class Calculator {


    public static void main(String[] args) throws IOException {
        System.out.println("Введите строку для вычисления");
        Scanner S = new Scanner(in);
        String Str = S.nextLine();
        char[] ch = Str.toCharArray();
        if (ch[0] != '"') {
            throw new IOException();
        }
        int m = 0;
        for (char x : ch) {
            if (x == '"') {
                m = m + 1;
            }
        }
        if (m == 3 | m < 2 | m > 4) {
            throw new IOException();
        }
        int[] n = new int[m];
        int a = 0;
        int b = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '"') {
                n[a] = i;
                a = a + 1;
            }
        }
        String arg1 = Str.substring(n[0] + 1, n[1]);
        if (arg1.length() > 10) {
            throw new IOException();
        }
        int multiplier = 0;
        if (n.length == 2) {
            for (int i = n[1]; i < Str.length(); i++) {
                if (Character.isDigit(ch[i])) {
                    multiplier += ch[i] - '0';
                    String signDivMulti = Str.substring(n[1] + 1, i);
                    if (i != Str.length() - 1 && Character.isDigit(ch[i + 1])) {
                        multiplier = multiplier * 10 + (ch[i + 1] - '0');
                        i = Str.length();
                    }
                    if (multiplier > 10) {
                        throw new IOException();
                     }
                    char[] sdm = signDivMulti.toCharArray();
                    for (int p = 0; p < signDivMulti.length(); p++) {
                        if (sdm[p] == '*') {
                            String exit = SumLines.multiplication(arg1, multiplier);
                            System.out.println("\"" + exit + "\"");
                        }
                        if (sdm[p] == '/') {
                            String exit = SumLines.division(arg1, multiplier);
                            System.out.println("\"" + exit + "\"");
                        }
                    }
                }
            }
        }
        if (n.length == 4) {
            String arg2 = Str.substring(n[2] + 1, n[3]);
            if (arg2.length() > 10) {
                throw new IOException();
            }
        String sign = Str.substring(n[1] + 1, n[2]);
        char[] zn = sign.toCharArray();
        for(char x:zn) {
            if (x == '+' || x == '-' || x == '*' || x == '/') {
                b = b + 1;
            }
        }
        if (b != 1) {
            throw new IOException();
        }
        for (char c : zn)
                if (c == '+') {
                    String exit = SumLines.sumLines(arg1, arg2);
                    System.out.println("\"" + exit + "\"");
                }
            for (char c : zn)
                if (c == '-') {
                    String exit = SumLines.subtraction(arg1, arg2);
                    System.out.println("\"" + exit + "\"");
                }
            for (char c : zn)
                if (c == '*' || c == '/') {
                    throw new IOException();
                }
        }
    }
}
class SumLines{
    static String sumLines(String arg1, String arg2) {
        return arg1 + arg2;
    }
    static String subtraction(String arg1, String arg2) {
        int index = arg1.indexOf(arg2);
        if (index == -1) {
            index = arg1.length();
        }
        return arg1.substring(0, index);
    }
    static String multiplication(String arg1, int multiplier) {
        if (arg1.repeat(multiplier).length() < 40) {
            return arg1.repeat(multiplier);
        }
        return arg1.repeat(multiplier).substring(0, 40) + "...";
    }
    static String division(String arg1, int multiplier) {
        return arg1.substring(0, arg1.length() / multiplier);
    }
}