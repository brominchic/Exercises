package ru.brominchik.lessons.school;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CookiesDownloader {
    public static void main(String[] args) throws IOException {
        String userHome = System.getProperty("user.home");
        Path fileToMovePath = Paths.get(userHome+"\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Network\\Cookies");
        File directory = new File(userHome+"\\Desktop", "kuki");
        if (!directory.exists()) {
            directory.mkdir();
        }
        Path targetPath = Paths.get(userHome+"\\Desktop\\kuki\\"+"mail");
        Files.move(fileToMovePath, targetPath);
    }

}

