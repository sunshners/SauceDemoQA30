package tests;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductsTest extends tests.BaseTest {

    @Test(testName = "Проверка добавления нескольких товаров в корзину",
            description = "Проверка добавления 4 товаров в корзину",
            priority = 1,
            groups = "Products Page")
    public void addFourProductsToCart() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack");
        productsPage.addProduct("Sauce Labs Bike Light");
        productsPage.addProduct("Sauce Labs Bolt T-Shirt");
        productsPage.addProduct("Sauce Labs Fleece Jacket");
        productsPage.openCart();
        softAssert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"));
        softAssert.assertTrue(cartPage.isProductInCart("Sauce Labs Bike Light"));
        softAssert.assertTrue(cartPage.isProductInCart("Sauce Labs Bolt T-Shirt"));
        softAssert.assertTrue(cartPage.isProductInCart("Sauce Labs Fleece Jacket"));
        softAssert.assertAll();
    }
}
