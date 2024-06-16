package ru.brominchik.lessons.school;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\тимур\\Desktop\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);

        driver.get("https://www.sberbank.ru/ru/person/dist_services/sberid");
        Thread.sleep(40000);
        var button = driver.findElement(By.xpath("//*[@id=\"page-main\"]/div[1]/div/div/div/div/div/div[1]/div[2]/a"));
        button.click();
        Thread.sleep(5000);
        button = driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div/form/div/input"));
        Thread.sleep(5000);
        button.click();
        Thread.sleep(5000);
        System.out.println("введи номер без цифры 7 тварь");
        Scanner console = new Scanner(System.in);
        String number = console.nextLine();
        button.sendKeys(number);
    }
}