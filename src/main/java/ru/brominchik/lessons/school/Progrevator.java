package ru.brominchik.lessons.school;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.util.Date;
import java.util.StringTokenizer;

public class Progrevator {
    public void delete(String mail) throws IOException {
        String fileName = "src/main/java/ru/brominchik/lessons/properties/";
        // создаем временный файл
        File tempFile = new File("temp.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        // читаем исходный файл
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            if (!currentLine.equals(mail)) {
                writer.write(currentLine + System.lineSeparator());
            }
        }
        // закрываем ридер и писатель
        reader.close();
        writer.close();
        // удаляем исходный файл
        File oldFile = new File(fileName);
        if (oldFile.delete()){
            // переименовываем временный файл в исходное имя файла
            tempFile.renameTo(oldFile);}
        System.out.println("Строка " + mail + " удалена из файла " + fileName);

    }
}