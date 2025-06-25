package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SortTest extends BaseTest {

    @DataProvider(name = "Проверка сортировки")
    public Object[][] sortData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", "Name (A to Z)"},
                {"standard_user", "secret_sauce", "Name (Z to A)"},
                {"standard_user", "secret_sauce", "Price (low to high)"},
                {"standard_user", "secret_sauce", "Price (high to low)"},
        };
    }

    @Test(testName = "Проверка сортировки", dataProvider = "Проверка сортировки", groups = {"regression"})
    public void checkSort(String user, String password, String typeOfSort) {
        productsPage = loginPage.open()
                .login(user, password);
        productsPage.sort(typeOfSort);
        assertTrue(productsPage.isSort(typeOfSort), "Сортировка не работает");
    }
}