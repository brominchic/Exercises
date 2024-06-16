package ru.brominchik.lessons.school;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.io.*;
import java.util.*;


public class Main {

    static List<String> names;
    static List<String> surnames;


    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);
        fillLists();
        System.out.println("Выберите режим 1-тихий, 2-полноэкранный");

        System.setProperty("webdriver.chrome.driver", "src/main/java/ru/brominchik/lessons/school/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        if (console.nextInt() == 1) {
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

        String link = "";
        for (int i = 35; i < ctr.indexOf("personalization=false&amp;display=page") - ctr.indexOf("data-personalization=\"false\" href=\"") + 48; i++) {
            link += ctr.charAt(ctr.indexOf("data-personalization=\"false\" href=\"") + i);
            System.out.print(ctr.charAt(ctr.indexOf("data-personalization=\"false\" href=\"") + i));
        }
        link = link.replace("amp;", "");
        System.out.println(link);
        driver.get(link);
        Thread.sleep(5000);
        button = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[1]/div/form/div/input"));
        button.click();
        System.out.println("Введите номер в формате 9279370814");
        String number = console.nextLine();
        button.sendKeys(number);
        button = driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div/form/button"));
        Thread.sleep(10000);
        button.click();
        System.out.println("введи 5 цифр из sms");
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
        fillName(driver);


    }

    public static void fillName(WebDriver driver) throws IOException, InterruptedException {
        Random random = new Random();
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
        File file = new File("src/main/java/ru/brominchik/lessons/properties/mails.txt");
        Scanner scanner = new Scanner(file);
        String mail = scanner.nextLine();
        delete(mail);
        button.sendKeys(mail);
        button = driver.findElement(By.xpath("//*[@id=\"desktopContainer\"]/div[1]/div[2]/form/div/div[7]/button[2]"));
        button.click();
        FileWriter writer = new FileWriter("src/main/java/ru/brominchik/lessons/properties/passwords.txt", false);
        writer.write(mail + " " + password);
        writer.write("\n");
        writer.close();
        Thread.sleep(15000);
        var cookies = driver.manage().getCookies();
        file = new File(mail + ".json");
        file.createNewFile();
        writer = new FileWriter(mail + ".json", false);
        writer.write(cookies.toString());
        writer.close();
    }

    public static void fillLists() throws FileNotFoundException {
        File file = new File("src/main/java/ru/brominchik/lessons/properties/names.txt");
        Scanner scanner = new Scanner(file);
        names = new ArrayList<>();
        while (scanner.hasNext()) {
            names.add(scanner.nextLine());
        }
        file = new File("src/main/java/ru/brominchik/lessons/properties/surnames.txt");
        scanner = new Scanner(file);
        surnames = new ArrayList<>();
        while (scanner.hasNext()) {
            surnames.add(scanner.nextLine());
        }
        scanner.close();
    }

    public static String generatePassword() {
        Random random = new Random();
        CharacterRule digits = new CharacterRule(EnglishCharacterData.Digit);
        String password = new PasswordGenerator().generatePassword(10,
                Arrays.asList(
                        new CharacterRule(EnglishCharacterData.UpperCase, 2),
                        new CharacterRule(EnglishCharacterData.LowerCase, 2),
                        new CharacterRule(EnglishCharacterData.Digit, 2),
                        new CharacterRule(EnglishCharacterData.UpperCase, 2)));

        password = password.replace(password.charAt(random.nextInt(0, 9)), '!');
        return password;
    }

    public static void delete(String mail) {
        // имя файла
        String fileName = "src/main/java/ru/brominchik/lessons/properties/mails.txt";
        // строка, которую нужно удалить
        try {
            // создаем временный файл
            File tempFile = new File("temp.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            // читаем исходный файл
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {

                if (!currentLine.equals(mail)) {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }

            // закрываем ридер и писатель
            reader.close();
            writer.close();

            // удаляем исходный файл
            File oldFile = new File(fileName);
            oldFile.delete();

            // переименовываем временный файл в исходное имя файла
            tempFile.renameTo(oldFile);

            System.out.println("Строка " + mail + " удалена из файла " + fileName);

        } catch (IOException ex) {
            System.out.println("Ошибка при удалении строки из файла " + fileName);
            ex.printStackTrace();
        }
    }
}
