package ru.brominchik.lessons.school;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Main {

    static List<String> names;
    static List<String> addresses;
    static List<String> surnames;
    static Random random;
    protected static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        random = new Random();
        Scanner console = new Scanner(System.in);
        fillLists();
        log.info("Выберите режим 1-тихий, 2-полноэкранный");
        System.setProperty("webdriver.chrome.driver", "src/main/java/ru/brominchik/lessons/school/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        if (console.nextLine().equals("1")) {
            options.addArguments("headless");
        }
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
        var ctr = (driver.getPageSource());
        StringBuilder link = new StringBuilder();
        for (int i = 35; i < ctr.indexOf("personalization=false&amp;display=page") - ctr.indexOf("data-personalization=\"false\" href=\"") + 48; i++) {
            link.append(ctr.charAt(ctr.indexOf("data-personalization=\"false\" href=\"") + i));
        }
        link = new StringBuilder(link.toString().replace("amp;", ""));
        driver.get(link.toString());
        Thread.sleep(5000);
        button = driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div/form/div/input"));
        button.click();
        log.info("Введите номер в формате 9279370814");
        String number = console.nextLine();
        log.info("Введите почту");
        String mail = console.nextLine();
        button.sendKeys(number);
        Thread.sleep(7000);
        button = driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div/form/button"));
        button.click();
        Thread.sleep(7000);
        log.info("введи 5 цифр из sms");
        String sms = console.nextLine();
        ArrayList<WebElement> buttons = new ArrayList<>();
        buttons.add(driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div[2]/div[1]/form/div/div/input[1]")));
        buttons.add(driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div[2]/div[1]/form/div/div/input[2]")));
        buttons.add(driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div[2]/div[1]/form/div/div/input[3]")));
        buttons.add(driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div[2]/div[1]/form/div/div/input[4]")));
        buttons.add(driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div[2]/div[1]/form/div/div/input[5]")));
        for (int i = 0; i < 5; i++) {
            buttons.get(i).click();
            buttons.get(i).sendKeys(String.valueOf(sms.charAt(i)));
        }
        Thread.sleep(5000);
        fillName(driver, mail);
        heat(driver);
        driver.close();
        Thread.sleep(5000);
        downloadCookie(mail);
    }

    public static void fillName(WebDriver driver, String mail) throws InterruptedException {
        var button = driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div[2]/form/div/div[1]/input"));
        button.click();
        button.sendKeys(names.get(random.nextInt(0, names.size()) - 1));
        button = driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div[2]/form/div/div[2]/input"));
        button.click();
        button.sendKeys(surnames.get(random.nextInt(0, surnames.size() - 1)));
        button = driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div[2]/form/div/div[3]/input"));
        button.click();
        String birthday = (random.nextInt(10, 28) + "." + random.nextInt(0, 9) + "0." + random.nextInt(1980, 2002));
        button.sendKeys(birthday);
        String password = generatePassword();
        button = driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div[2]/form/div/div[4]/input"));
        button.click();
        button.sendKeys(password);
        button = driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div[2]/form/div/div[5]/input"));
        button.click();
        button.sendKeys(password);
        button = driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div[2]/form/div/div[6]/input"));
        button.click();
        button.sendKeys(mail);
        button = driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div[2]/form/div/div[7]/button[2]"));
        button.click();
        Thread.sleep(20000);
    }

    public static void heat(WebDriver driver) throws InterruptedException {
        Thread.sleep(3000);
        WebElement button;
        if (isElementPresent(driver, "//*[@id=\"cookies-notification\"]/button[1]/div")) {
            button = driver.findElement(By.xpath("//*[@id=\"cookies-notification\"]/button[1]/div"));
            button.click();
        }
        Thread.sleep(5000);
        button = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div[1]/div/div[3]/div[2]/div/div/div/div/div/div/div[1]/div/div/div/div[1]/div/div/div[3]/div[2]/div/button"));
        button.click();
        Thread.sleep(5000);
        button = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div/div/div/div[1]/div/div/div[2]/a[1]/span"));
        button.click();
        Thread.sleep(5000);
        button = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div/div/div/div/div[2]/div/div[2]/div/div[2]/div[2]/button/span[1]"));
        button.click();
        Thread.sleep(5000);
        button = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div/div/div/div/div[1]/div[2]/div/div/div/div/div[1]/div[1]/form/div/input"));
        button.click();
        button.sendKeys(addresses.get(random.nextInt(0, addresses.size() - 1) - 1));
        Thread.sleep(3000);
        button = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div/div/div/div/div[1]/div[5]/button"));
        button.click();
        Thread.sleep(3000);
        button = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div/div/div/div/div[1]/div[5]/button"));
        button.click();
        Thread.sleep(3000);
        if (isElementPresent(driver, "//*[@id=\"app\"]/main/div/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div[1]/div/label/span")) {
            button = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div[1]/div/label/span"));
            button.click();
        }
        Thread.sleep(3000);
        if (isElementPresent(driver, "//*[@id=\"app\"]/main/div/div/div/div/div/div[2]/div[5]/div[2]/button")) {
            button = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div/div/div/div/div[2]/div[5]/div[2]/button"));
            button.click();
        }
        if (isElementPresent(driver, "//*[@id=\"app\"]/main/div/div/div[2]/div/button/div")) {
            button = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div/div[2]/div/button/div"));
            button.click();
        }
    }

    public static void downloadCookie(String mail) throws IOException {
        String userHome = System.getProperty("user.home");
        Path fileToMovePath = Paths.get(userHome + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Network\\Cookies");
        File directory = new File(userHome + "\\Desktop", "kuki");
        if (!directory.exists() && directory.mkdir()) {
            log.info("Создана папка на рабочем столе");
            }
        Path targetPath = Paths.get(userHome + "\\Desktop\\kuki\\" + mail);
        Files.move(fileToMovePath, targetPath);
    }

    public static void fillLists() throws FileNotFoundException {
        File file = new File("src/main/java/ru/brominchik/lessons/properties/names.txt");
        try (Scanner scanner = new Scanner(file)) {
            names = new ArrayList<>();
            while (scanner.hasNext()) {
                names.add(scanner.nextLine());
            }
        }
        file = new File("src/main/java/ru/brominchik/lessons/properties/surnames.txt");
        try (Scanner scanner = new Scanner(file)) {
            surnames = new ArrayList<>();
            while (scanner.hasNext()) {
                surnames.add(scanner.nextLine());
            }
        }
        file = new File("src/main/java/ru/brominchik/lessons/properties/adresses.txt");
        try (Scanner scanner = new Scanner(file)) {
            addresses = new ArrayList<>();
            while (scanner.hasNext()) {
                addresses.add(scanner.nextLine());
            }
        }

    }

    public static String generatePassword() {
        String password = new PasswordGenerator().generatePassword(10, Arrays.asList(new CharacterRule(EnglishCharacterData.UpperCase, 2), new CharacterRule(EnglishCharacterData.LowerCase, 2), new CharacterRule(EnglishCharacterData.Digit, 2), new CharacterRule(EnglishCharacterData.UpperCase, 2)));
        password = password.replace(password.charAt(random.nextInt(0, 9)), '!');
        return password;
    }

    private static boolean isElementPresent(WebDriver driver, String path) {
        try {
            return !driver.findElements(By.xpath(path)).isEmpty();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
