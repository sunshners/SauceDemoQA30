package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Retry;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(retryAnalyzer = Retry.class)
    public void checkSuccessLogin(){
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(productsPage.getTitle(),
                "Products",
                "Логин не выполнен");
    }


    @Test(testName = "Проверка логина с пустым паролем", priority = 2, groups = "Login Page")
    public void checkLoginWithEmptyPassword(){
        loginPage.open();
        loginPage.login(user, "");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "SO BAAAAD");
    }

    @Test(testName = "Проверка логина с пустым логином", priority = 2, groups = "Login Page")
    public void checkLoginWithEmptyUsername(){
        loginPage.open();
        loginPage.login("", password);
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username is required",
                "SO BAAAAD");
    }


    @Test(testName = "Проверка входа с неверным паролем", priority = 3, groups = "Login Page")
    public void checkLoginWithWrongPassword(){
        loginPage.open();
        loginPage.login(user, "123123123123");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "SO BAAAAD");
    }
}
