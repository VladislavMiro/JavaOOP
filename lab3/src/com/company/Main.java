/*
Лабораторная работа 3
Выполнил: Мирошниченко В.А.
Группа: в3530904/80322
Дана строка. Разделить строку на фрагменты по три подряд идущих символа.
В каждом фрагменте средний символ заменить на случайный символ, не совпадающий ни с одним из символов этого фрагмента.
Вывести в консоль фрагменты, упорядоченные по алфавиту.
 */
package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        String str = inputString();
        var arr = changeSymbol(splitString(str));
        Collections.sort(arr);
        for (var elem: arr) {
            System.out.println(elem);
        }
    }

    private static String inputString() {
        System.out.println("Введите строку: ");
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        return str.trim();
    }

    private static ArrayList<String> splitString(String str) {
        ArrayList<String> array = new ArrayList<>();
        int index = 0;

        while (index < str.length()-3) {
            String tmp = str.substring(index, index+3);
            array.add(tmp);
            index = index + 3;
        }
        array.add(str.substring(index));

        return array;
    }

    private static ArrayList<String> changeSymbol(ArrayList<String> strArr) {

        for (int i = 0; i < strArr.size(); i++) {
            String st = strArr.get(i);

            if(st.length() == 3) {
                char ch;
                do {
                    ch = (char) ThreadLocalRandom.current().nextInt(32,127);
                } while (st.charAt(0) == ch && st.charAt(2) == ch);
                StringBuilder newStr = new StringBuilder(st);
                newStr.setCharAt(1,ch);
                strArr.set(i, newStr.toString());
            } else {
                strArr.set(i, st);
            }

        }

        return strArr;
    }

}
