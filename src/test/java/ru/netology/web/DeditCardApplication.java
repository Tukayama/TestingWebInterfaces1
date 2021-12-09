package ru.netology.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeditCardApplication {
    private WebDriver driver;

    @BeforeAll
    public static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "./driver/win/chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    public void personalDataPositiveTest() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type='text']")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[type='tel']")).sendKeys("+79678883455");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actualTest = driver.findElement(By.cssSelector(".Success_successBlock__2L3Cw")).getText().trim();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expected, actualTest);

    }

    @Test
    public void emptyNameField() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type='text']")).sendKeys("");
        driver.findElement(By.cssSelector("[type='tel']")).sendKeys("+79678883455");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actualTest = driver.findElement(By.cssSelector(".input__sub")).getText().trim();
        String expected = "Поле обязательно для заполнения";
        assertEquals(expected, actualTest);

    }

    @Test
    public void wrongPohneNumber() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type='text']")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[type='tel']")).sendKeys("788875");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actualTest = driver.findElement(By.cssSelector(" .input_invalid")).getText().trim();
        String expected = "Мобильный телефон\nТелефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        assertEquals(expected, actualTest);


    }

    @Test
    public void emptyPohneField() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type='text']")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[type='tel']")).sendKeys("");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actualTest = driver.findElement(By.cssSelector(" .input_invalid")).getText().trim();
        String expected = "Мобильный телефон\nПоле обязательно для заполнения";
        assertEquals(expected, actualTest);
    }

    @Test
    public void latinNameSurname() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type='text']")).sendKeys("Ivanov Ivan");
        driver.findElement(By.cssSelector("[type='tel']")).sendKeys("+79678883455");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actualTest = driver.findElement(By.cssSelector(".input__sub")).getText().trim();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        assertEquals(expected, actualTest);
    }

    @Test
    public void emptyForm() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type='text']")).sendKeys("");
        driver.findElement(By.cssSelector("[type='tel']")).sendKeys("");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actualTest = driver.findElement(By.cssSelector(".input__sub")).getText().trim();
        String expected = "Поле обязательно для заполнения";
        assertEquals(expected, actualTest);

    }
}

