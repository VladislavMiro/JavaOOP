package com.company;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class CommandLine {
    private String currentDir;
    private File file;

    CommandLine() {
        /*file = new File("C:/Users");
        currentDir = file.getAbsolutePath();
         */
        currentDir = System.getProperty("user.home");
    }

    public void changeDir(String path) {
        long count = path.chars().filter(ch -> ch == '.').count();

        switch (Math.toIntExact(count)) {
            case 0 -> {
                file = new File(currentDir + "/" + path);
                if (file.exists()) {
                    currentDir = file.getAbsolutePath();
                } else {
                    System.out.println("Ошибка, каталог не найден.");
                }
            }
            case 2 -> {
                file = new File(currentDir);
                if (file.exists()) {
                    currentDir = file.getParent();
                } else {
                    System.out.println("Ошибка, каталог не найден.");
                }
            }
            default -> System.out.println("Ошибка, каталог не найден.");
        }

    }

    public void listCatalog(String path) {
        file = new File(currentDir+path);
        if (file.exists()) {
            File[] filesList = file.listFiles();
            assert filesList != null;
            for (File fileName: filesList) {
                System.out.println(fileName.getName());
            }
        }
    }

    public String[] parseString(String str) {
        String[] array;

        array = str.split(" ");

        return array;
    }

    public void printCurrentDir() {
        System.out.print(currentDir+">");
    }

    public void openTextFile(String fileName) {
        file = new File(currentDir + "/" + fileName);

        if (file.exists() && file.isFile()) {
            try {
               Scanner scan = new Scanner(file);
               while (scan.hasNextLine()) {
                   String data = scan.nextLine();
                   System.out.println(data);
               }
               scan.close();
            } catch (Exception ignored) {}
        } else {
            System.out.println("Ошибка, файл не найден.");
        }

    }

    public void createTextFile(String path) {
        file = new File(currentDir);
        if(file.exists() && file.isDirectory()) {
            try {
               if (new File(currentDir + "/" + path).createNewFile()) System.out.println("Файл успешно создан.");
            } catch (Exception e) {
                System.out.println("Не удалось создать файл.");
            }

        } else {
            System.out.println("Ошибка нет такого каталога");
        }
    }

    public void deleteFile(String path) {
        file = new File(currentDir + "/" + path);
        if(file.exists() && file.isFile()) {
            try {
                if (file.delete()) System.out.println("Файл был успешно удален.");
            } catch (Exception e) {
                System.out.println("Не удалось удалть файл.");
            }

        } else {
            System.out.println("Ошибка нет такого файла");
        }
    }

    public void addDataInTextFile(String path, String text) {
        file = new File(currentDir + "/" + path);

        if(!file.exists()) createTextFile(path);
        file = new File(currentDir + "/" + path);
        try {
            System.out.println(file.toString());
            Files.write(Paths.get(currentDir + "/" + path), text.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            System.out.println("Не удалоь выполнить запись в файл");
        }

    }

}
