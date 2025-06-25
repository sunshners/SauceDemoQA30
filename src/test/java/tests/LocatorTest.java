package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LocatorTest extends BaseTest {

    @Test
    public void checkLocator(){
        driver.get("https://www.saucedemo.com/");
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        driver.findElement(By.name("user-name"));
        driver.findElement(By.className("error-message-container"));
        driver.findElement(By.tagName("div"));

        driver.findElement(By.id("user-name"));
        driver.findElement(By.id("password"));
        driver.findElement(By.id("login-button"));
        driver.findElement(By.name("password"));
        driver.findElement(By.className("btn_action"));
        driver.findElement(By.tagName("button"));
        driver.findElement(By.linkText("Sauce Labs Backpack"));
        driver.findElement(By.partialLinkText("Backpack"));
        driver.findElement(By.xpath("//input[@id='user-name']"));
        driver.findElement(By.xpath("//input[@name='password']"));
        driver.findElement(By.xpath("//input[@class='form_input']"));
        driver.findElement(By.xpath("//div[contains(text(), 'Sauce Labs')]"));
        driver.findElement(By.xpath("//input[starts-with(@id, 'user')]"));
        driver.findElement(By.xpath("//input[contains(@class, 'form')]"));
        driver.findElement(By.xpath("//div[contains(@id, 'inventory')]"));
        driver.findElement(By.xpath("//input[@id='user-name']/following::input[1]"));
        driver.findElement(By.xpath("//input[@id='user-name']/parent::div"));
        driver.findElement(By.xpath("//input[@id='login-button']/preceding::input[1]"));
        driver.findElement(By.xpath("(//div[@class='inventory_item_name'])[1]"));
        driver.findElement(By.xpath("(//button[contains(@class, 'btn_inventory')])[1]"));
        driver.findElement(By.className("shopping_cart_link"));
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        driver.findElement(By.className("checkout_button"));
        driver.findElement(By.linkText("CHECKOUT"));
        driver.findElement(By.xpath("//a[text()='CHECKOUT']"));
        driver.findElement(By.id("first-name"));
        driver.findElement(By.xpath("//input[@data-test='firstName']"));
    }
}
