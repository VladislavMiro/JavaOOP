/*
Лабораторная работа 6
Мирошниченко В.А.
группа: в3530904/80322
Разработать консольное приложение, позволяющее просматривать файлы и каталоги файловой системы,
а также создавать и удалять текстовые файлы. Для работы с текстовыми файлами необходимо реализовать
функциональность записи (дозаписи) в файл.
*/

package com.company;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine();
        Scanner scanner = new Scanner(System.in);
        Commands command;
        String path;
        Map<String,String> dictionary = new HashMap<>();

        do {
            commandLine.printCurrentDir();

            String str = scanner.nextLine();

            dictionary = commandLine.parseString(str);


            try {
                command = Commands.valueOf(dictionary.get("command").toUpperCase());
            } catch (Exception exe) {
                command = Commands.valueOf("ERROR");
            }

            switch (command) {
                case CD:
                    path = dictionary.get("path");
                    if(path != null) {
                        commandLine.changeDir(path);
                    } else {
                        System.out.println("Ошибка в записи команды.");
                    }
                    break;
                case LS:
                    path = dictionary.get("path") != null ? dictionary.get("path") : "";
                    commandLine.listCatalog(path);
                    break;
                case CLEAR:
                    try {
                        Runtime.getRuntime().exec("cls");
                    } catch (Exception e) {}
                    break;
                case CAT:
                    path = dictionary.get("path");
                    if(path != null) {
                        commandLine.openTextFile(path);
                    } else { System.out.println("ошибка чтения файла"); }
                    break;
                case TOUCH:
                    path = dictionary.get("path");
                    if(path != null) {
                        commandLine.createTextFile(path);
                    } else {
                        System.out.println("Ошибка в записи команды.");
                    }
                    break;
                case RM:
                    path = dictionary.get("path");
                    if(path != null) {
                        commandLine.deleteFile(path);
                    } else {
                        System.out.println("Ошибка в записи команды.");
                    }
                    break;
                case TEE:
                    path = dictionary.get("path");
                    if(path != null) {
                        String data = dictionary.get("data") == null ? "" : dictionary.get("data");
                        commandLine.addDataInTextFile(path, data);
                    } else {
                        System.out.println("Ошибка в записи команды.");
                    }
                    break;
                case ERROR:
                    System.out.println("Ошибка, неизветсная команда, или ошибка в записи команды.");
                    break;
            }

        } while (command != Commands.EXIT);
    }
}
