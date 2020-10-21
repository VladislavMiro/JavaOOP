package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine();
        Scanner scanner = new Scanner(System.in);
        Commands command;
        String path;

        do {
            commandLine.printCurrentDir();

            String str = scanner.nextLine();
            String[] arrStr = commandLine.parseString(str);

            try {
                command = Commands.valueOf(arrStr[0].toUpperCase());
            } catch (Exception exe) {
                command = Commands.valueOf("ERROR");
            }

            switch (command) {
                case CD:
                    path = arrStr.length > 1 ? arrStr[1] : "";
                    commandLine.changeDir(path);
                    break;
                case LS:
                    path = arrStr.length > 1 ? arrStr[1] : "";
                    commandLine.listCatalog(path);
                    break;
                case CLEAR:
                    try {
                        Runtime.getRuntime().exec("cls");
                    } catch (Exception e) {}
                    break;
                case CAT:
                    if(arrStr.length > 1) {
                        commandLine.openTextFile(arrStr[1]);
                    } else { System.out.println("ошибка чтения файла");}
                    break;
                case TOUCH:
                    if(arrStr.length > 1) {
                        commandLine.createTextFile(arrStr[1]);
                    } else {
                        System.out.println("Ошибка в записи команды.");
                    }
                    break;
                case RM:
                    if(arrStr.length > 1) {
                        commandLine.deleteFile(arrStr[1]);
                    } else {
                        System.out.println("Ошибка в записи команды.");
                    }
                    break;
                case TEE:
                    if(arrStr.length == 3) {
                        commandLine.addDataInTextFile(arrStr[1], arrStr[2]);
                    } else {
                        System.out.println("Ошибка в записи команды.");
                    }
                    break;
                case ERROR:
                    System.out.println("Ошибка, неизветсная команда.");
                    break;
            }

        } while (command != Commands.EXIT);
    }
}
