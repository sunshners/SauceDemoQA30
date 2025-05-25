package tests;

import org.testng.annotations.Test;
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
                "Epic sadface: Password is requred",
                "SO BAAAAD");
    }


    @Test(testName = "Проверка входа с неверным паролем", priority = 3, groups = "Login Page")
    public void checkLoginWithWrongPassword(){
        loginPage.open();
        loginPage.login(user, "123123123123");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is requred",
                "SO BAAAAD");
    }

    @Test(dataProvider = "Hегативные тесты для логина")
    public void login() {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(loginPage.getErrorMessage(),
                "SO BAAAAD");
    }
}
