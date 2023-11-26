package ru.brominchik.lessons.files;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ModsSwitcher {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        discs();
        System.out.println("Выберите диск");
        String disk = in.nextLine();
        File folder = new File(disk + ":");
        returnPathTo(folder);

    }

    public static void discs() {
        File[] paths;
        FileSystemView fsv = FileSystemView.getFileSystemView();
        paths = File.listRoots();
        for (File path : paths) {
            System.out.println("Drive Name: " + path);
            System.out.println("Description: " + fsv.getSystemTypeDescription(path));
        }
    }

    public static void returnPathTo(File folder) throws IOException {
        Map<Integer, File> values = new HashMap<>();
        Scanner in = new Scanner(System.in);
        int i = 0;
        try {
            for (File file : folder.listFiles()) {
                values.put(i, file);
                i++;
            }
        } catch (NullPointerException e) {
            System.out.println("Такого диска не существует");
            String[] string = new String[0];
            main(string);
        }

        for (Map.Entry<Integer, File> entry : values.entrySet()) {
            Integer key = entry.getKey();

            String value = entry.getValue().getName();
            System.out.println(key + " - " + value);
        }
        System.out.println(values.size() + " - " + "Назад");
        System.out.println("Выберите папку");
        int num = in.nextInt();
        try {
            if (num == values.size()) {
                String[] string = new String[0];
                main(string);
            } else {
                if ((values.get(num).isDirectory())) {
                    if ((values.get(num).listFiles().length > 2)) {
                        if ((values.get(num).listFiles()[0].getName().equals("asiloader.log")) & (values.get(num).listFiles()[1].getName().equals("bink2w64.dll"))) {
                            enableDisable(values.get(num));
                        } else {
                            returnPathTo(values.get(num));
                        }
                    } else {
                        returnPathTo(values.get(num));
                    }
                } else {
                    System.out.println("Это не папка");
                    returnPathTo(folder);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Такой папки нет");
            returnPathTo(folder);

        }
    }

    public static void enableDisable(File folder) {
        System.out.println("Включить/Отключить");
        String nextLine = in.nextLine();

        if (nextLine.equals("Включить")) {
            for (File file : folder.listFiles()) {
                if (file.getName().equals("mods")) {
                    System.out.println("Моды уже включены");
                    return;
                }
                if (file.getName().equals("modsgg")) {
                    String modsPath = folder.getAbsolutePath() + "\\modsgg";
                    File modsFile = new File(modsPath);
                    modsFile.renameTo(new File(folder.getAbsolutePath() + "\\mods"));
                    modsFile.delete();
                    System.out.println("Моды Включены");
                    return;
                }
            }
        }
        if (nextLine.equals("Отключить")) {
            for (File file : folder.listFiles()) {
                if (file.getName().equals("modsgg")) {
                    System.out.println("Моды уже выключены");
                    return;
                }
                if (file.getName().equals("mods")) {
                    String modsPath = folder.getAbsolutePath() + "\\mods";
                    File modsFile = new File(modsPath);
                    modsFile.renameTo(new File(folder.getAbsolutePath() + "\\modsgg"));
                    modsFile.delete();
                    System.out.println("Моды Выключены");
                    return;
                }
            }
        } else {
            System.out.println("Команда не распознана");
            enableDisable(folder);
        }
    }
}
