package com.company;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLine {
    private String currentDir;
    private File file;

    CommandLine() {
        currentDir = System.getProperty("user.home");
    }

    public void changeDir(String path) {
        long count = path.chars().filter(ch -> ch == '.').count();

        switch (Math.toIntExact(count)) {
            case 0 -> {
                file = new File(currentDir + File.separator + path);
                if (file.exists()) {
                    currentDir = file.getAbsolutePath();
                } else {
                    System.out.println("Ошибка, каталог не найден.");
                }
            }
            case 2 -> {
                file = new File(currentDir);
                if (file.exists()) {
                    String dir = file.getParent();
                    currentDir = dir == null? currentDir : dir;
                } else {
                    System.out.println("Ошибка, каталог не найден.");
                }
            }
            default -> System.out.println("Ошибка, каталог не найден.");
        }

    }

    public void listCatalog(String path) {
        file = new File(currentDir+File.separator + path);
        if (file.exists()) {
            File[] filesList = file.listFiles();
            assert filesList != null;
            for (File fileName: filesList) {
                System.out.println(fileName.getName());
            }
        }
    }

    public Map<String,String> parseString(String str) {
        Map<String,String> dictionary = new HashMap<>();

        str = str.trim();
        String command = regex("^[a-zA-z]{0,4}\\S", str);
        String path = regex("\s([\\wа-яА-Я\\/\\.\\_ ]){0,}($|,\\W)", str);
        String data = regex(",[\\s\\S]*$", str);

        dictionary.put("command", command != null ? command : null);
        dictionary.put("path", path != null ? path.replaceAll(",", "").trim() : null);
        dictionary.put("data", data != null ? data.replaceFirst(",", "").trim() : null);

        return dictionary;
    }

    private String regex(String pattern, String string) {
        Pattern _pattern = Pattern.compile(pattern);
        Matcher matcher = _pattern.matcher(string);

        return matcher.find() == true ? string.substring(matcher.start(), matcher.end()) : null;
    }

    public void printCurrentDir() {
        System.out.print(currentDir+">");
    }

    public void openTextFile(String fileName) {
        file = new File(currentDir + File.separator + fileName);

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
               if (new File(currentDir + File.separator + path).createNewFile()) System.out.println("Файл успешно создан.");
            } catch (Exception e) {
                System.out.println("Не удалось создать файл.");
            }

        } else {
            System.out.println("Ошибка нет такого каталога");
        }
    }

    public void deleteFile(String path) {
        file = new File(currentDir + File.separator + path);
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
        file = new File(currentDir + File.separator + path);

        if(!file.exists()) createTextFile(path);
        file = new File(currentDir + File.separator + path);
        try {
            System.out.println(file.toString());
            Files.write(Paths.get(currentDir + File.separator + path), text.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            System.out.println("Не удалоь выполнить запись в файл");
        }

    }

}
