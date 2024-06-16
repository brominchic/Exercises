package ru.brominchik.lessons.school;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Main {

    static List<String> names;
    static List<String> surnames;
    static List<String> mails;


    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/main/java/ru/brominchik/lessons/school/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://megamarket.ru/");
        Thread.sleep(5000);
        var button = driver.findElement(By.xpath("//*[@id=\"cookies-notification\"]/button[1]/div"));
        button.click();
        Thread.sleep(5000);
        button = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div/div/div/div/div/div/div[3]/button[1]"));
        button.click();
        button = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div[1]/div[1]/div/div/div/div/div[1]/div[1]/div"));
        button.click();
        button = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div[2]/nav/div/div[1]/div[2]/div[1]/div/button"));
        button.click();
        Thread.sleep(2000);
        var ctr=(driver.getPageSource());

        String link="";
        for (int i = 35; i <ctr.indexOf("personalization=false&amp;display=page")-ctr.indexOf("data-personalization=\"false\" href=\"")+48 ; i++) {
            link +=ctr.charAt(ctr.indexOf("data-personalization=\"false\" href=\"")+i);
            System.out.print(ctr.charAt(ctr.indexOf("data-personalization=\"false\" href=\"")+i));
        }
         link=link.replace("amp;","");
        System.out.println(link);
        driver.get(link);
        Thread.sleep(5000);
        button = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[1]/div/form/div/input"));
        button.click();
        System.out.println("Введи номер без 7");
        Scanner console = new Scanner(System.in);
        String number = console.nextLine();
        button.sendKeys(number);
        button = driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div/form/button"));
        Thread.sleep(10000);
        button.click();
        System.out.println("введи sms");
        String sms = console.nextLine();
        ArrayList<WebElement> buttons= new ArrayList<>();
        buttons.add(driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div[2]/div[1]/form/div/div/input[1]")));
        buttons.add(driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div[2]/div[1]/form/div/div/input[2]")));
        buttons.add(driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div[2]/div[1]/form/div/div/input[3]")));
        buttons.add(driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div[2]/div[1]/form/div/div/input[4]")));
        buttons.add(driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div[2]/div[1]/form/div/div/input[5]")));
        for (int i = 0; i < 5; i++) {
            buttons.get(i).click();
            buttons.get(i).sendKeys(String.valueOf(sms.charAt(i)));
        }

    }

    public static void fillName (WebDriver driver){
        Random random=new Random();
        var button = driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div[2]/form/div/div[1]/input"));
        button.click();
        button.sendKeys(names.get(random.nextInt(0,99)));
            button = driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div[2]/form/div/div[2]/input"));
        button.click();
        button.sendKeys(names.get(random.nextInt(0,99)));
        button = driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div[2]/form/div/div[3]/input"));
        button.click();
        button.sendKeys(random.nextInt(1,28)+"."+random.nextInt(0,12)+"."+random.nextInt(1980,2002));

    }

    public static void fillLists(){
        Scanner scanner = new Scanner("src/main/java/ru/brominchik/lessons/properties/names.txt");
        names=new ArrayList<>();
        while (scanner.hasNext()){
            names.add(scanner.nextLine());
        }
        scanner = new Scanner("src/main/java/ru/brominchik/lessons/properties/surnames.txt");
        surnames=new ArrayList<>();
        while (scanner.hasNext()){
            surnames.add(scanner.nextLine());
        }
        scanner = new Scanner("src/main/java/ru/brominchik/lessons/properties/mails.txt");
        mails=new ArrayList<>();
        while (scanner.hasNext()){
            mails.add(scanner.nextLine());
        }
    }
}