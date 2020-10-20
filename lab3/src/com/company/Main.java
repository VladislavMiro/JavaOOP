/*
Мирошниченко В.А.
группа: в3530904/80322
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
        if (str != null) {
            var arr = changeSymbol(splitString(str));
            Collections.sort(arr);
            System.out.println(arr);
        }
    }

    private static String inputString() {
        System.out.println("Введите строку колличество елементов кратное трем: ");
        Scanner scan = new Scanner(System.in);
        String str = scan.next();
        if (str.length() % 3 != 0) {
            System.out.println("Ошибка, необходимо ввести строку коллчество елементов которой кратно 3!");
            return null;
        } else {
            return str;
        }
    }

    private static ArrayList<String> splitString(String str) {
        ArrayList<String> array = new ArrayList<>();

        for (int i = 0; i < str.length(); i = i+3)
            array.add(str.substring(i, i+3));

        return array;
    }

    private static ArrayList<String> changeSymbol(ArrayList<String> strArr) {
        ArrayList<String> newArray = new ArrayList<>();

        for (int i = 0; i < strArr.size(); i++) {
            String st = strArr.get(i);
            char ch;
            do {
                ch = (char) ThreadLocalRandom.current().nextInt(32,127);
            } while (st.indexOf(ch) >= 0);
            strArr.set(i, new String(new char[] {st.charAt(0),ch,st.charAt(2)}));
        }

        return strArr;
    }

}
