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


    @Test(testName = "Проверка входа с неверным паролем", priority = 3, groups = "Login Page")
    public void checkLoginWithWrongPassword(){
        loginPage.open();
        loginPage.login(user, "123123123123");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "SO BAAAAD");
    }

    @Test(dataProvider = "negativeLoginTests")
    public void login(String user, String password, String expectedErrorMessage) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(loginPage.getErrorMessage(),
                expectedErrorMessage,
                "Неверное сообщение об ошибке");
    }

    @DataProvider(name = "negativeLoginTests")
    public Object[][] negativeLoginData() {
        return new Object[][] {
                {user, "", "Epic sadface: Password is required"},
                {user, "wrong", "Epic sadface: Username and password do not match any user in this service"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."}
        };
    }
}
