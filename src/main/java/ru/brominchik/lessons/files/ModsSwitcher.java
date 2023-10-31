package ru.brominchik.lessons.files;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ModsSwitcher {

    public static void main(String arg[]) {
        Scanner in = new Scanner(System.in);
        discs();
        System.out.println("Выберите диск");
        String disk = in.nextLine();
        File folder = new File(disk + ":");
        pathTo(folder);
        System.out.println("Включить/Отключить");
        String nextLine = in.nextLine();

        if (nextLine.equals("Включить")) {
            for (File file : folder.listFiles()) {
                if (file.getName().equals("mods")) {
                    System.out.println("Моды уже включены");
                    return;
                }
                if (file.getName().equals("modsgg")) {
                    String path = "F:\\Grand Theft Auto V\\modsgg";
                    File modsFile = new File(path);
                    modsFile.renameTo(new File("F:\\Grand Theft Auto V\\mods"));
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
                    String path = "F:\\Grand Theft Auto V\\mods";
                    File modsFile = new File(path);
                    modsFile.renameTo(new File("F:\\Grand Theft Auto V\\modsgg"));
                    modsFile.delete();
                    System.out.println("Моды Выключены");
                    return;
                }
            }
        }
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

    public static String pathTo(File folder) {
        Map<Integer, String> values = new HashMap<>();
        Scanner in = new Scanner(System.in);
        int i = 0;
        for (File file : folder.listFiles()) {
            values.put(i, file.getName());
            i++;
            System.out.println(file.getName());
        }
        for (Map.Entry<Integer, String> entry : values.entrySet()) {
            Integer key = entry.getKey();

            String value = entry.getValue();
            System.out.println(key + " - " + value);
        }
        System.out.println("Выберите папку");
        int num = in.nextInt();
        if (values.get(num).equals(null)) {
            System.out.println("Такой папки нет");
        } else {
            
        }
        return "1";
    }
}
