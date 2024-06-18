package ru.brominchik.lessons.school;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CookiesDownloader {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/java/ru/brominchik/lessons/school/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://megamarket.ru/");
        Thread.sleep(40000);
        WebElement button;
        if (isElementPresent(driver,"//*[@id=\"cookies-notification\"]/button[1]/div")){
        button = driver.findElement(By.xpath("//*[@id=\"cookies-notification\"]/button[1]/div"));
        button.click();}
        Thread.sleep(7000);
        button = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div[1]/div/div[3]/div[2]/div/div/div/div/div/div/div[1]/div/div/div/div[1]/div/div/div[3]/div[2]/div/button"));
        button.click();
        Thread.sleep(7000);
        button = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div/div/div/div[1]/div/div/div[2]/a[1]/span"));
        button.click();
        Thread.sleep(7000);
        button = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div/div/div/div/div[2]/div/div[2]/div/div[2]/div[2]/button/span[1]"));
        button.click();
        Thread.sleep(7000);
        button = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div/div/div/div/div[1]/div[2]/div/div/div/div/div[1]/div[1]/form/div/input"));
        button.click();
        button.sendKeys("Уфа Авроры 5\n");
        Thread.sleep(3000);
        button = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div/div/div/div/div[1]/div[5]/button"));
        button.click();
        Thread.sleep(3000);
        button = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div/div/div/div/div[1]/div[5]/button"));
        button.click();
        Thread.sleep(3000);
        if (isElementPresent(driver,"//*[@id=\"app\"]/main/div/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div[1]/div/label/span")){
            button = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div[1]/div/label/span"));
            button.click();}
        Thread.sleep(3000);
        if (isElementPresent(driver,"//*[@id=\"app\"]/main/div/div/div/div/div/div[2]/div[5]/div[2]/button")){
            button = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div/div/div/div/div[2]/div[5]/div[2]/button"));
            button.click();}
        if (isElementPresent(driver,"//*[@id=\"app\"]/main/div/div/div[2]/div/button/div")){
            button = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div/div[2]/div/button/div"));
            button.click();}
    }

    private static boolean isElementPresent(WebDriver driver, String path) {
        try {
            return !driver.findElements(By.xpath(path)).isEmpty();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}

