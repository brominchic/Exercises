package ru.brominchik.lessons.school;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CookiesDownloader {
    public static void main(String[] args ) throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/java/ru/brominchik/lessons/school/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://megamarket.ru/");

        var cookies= driver.manage().getCookies();
        File file = new File("mail"+".json");
        file.createNewFile();
       FileWriter writer = new FileWriter("mail"+".json", false);
        writer.write(cookies.toString());

        writer.close();
    }
}
