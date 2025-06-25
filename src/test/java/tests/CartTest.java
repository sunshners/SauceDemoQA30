package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {

    @Test
    public void checkCart() {
            loginPage.open();
            loginPage.login(user, password);
            productsPage.addProduct("Sauce Labs Backpack");
            productsPage.addProduct("Sauce Labs Bike Light");
            productsPage.openCart();
            assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"),
                    "Товар не добавлен в корзину");
    }

    @Test(testName = "Добавление товара в корзину", priority = 2, groups = "Cart Page")
    public void addToCart() {
        loginPage.open();
        loginPage.login(user, password);
        productsPage.addProduct("Sauce Labs Backpack");
        productsPage.openCart();
        assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"),
                "Товар не добавлен в корзину");
        cartPage.removeProduct("Sauce Labs Backpack");
        assertTrue(cartPage.checkEmptyState(), "Корзина не пуста");
    }

    @Test(testName = "Проверка стоимости товара", priority = 3, groups = "Cart Page")
    public void checkProductsPrice() {
        loginPage.open();
        loginPage.login(user, password);
        productsPage.addProduct("Sauce Labs Backpack");
        productsPage.openCart();
        Assert.assertEquals(cartPage.getProductPrice(0), 29.99);
    }
}
