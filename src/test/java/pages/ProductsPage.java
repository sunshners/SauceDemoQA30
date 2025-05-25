package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class ProductsPage extends BasePage {

    private static final By TITLE = By.cssSelector("[data-test = title]");
    private static final By CART_BUTTON = By.cssSelector(".shopping_cart_link");
    private static final String ADD_TO_CART_PATTERN = "//*[text() = '%s']/ancestor::div[@class = 'inventory_item']//button";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return driver.findElement(TITLE).getText();

    }

    public void addProduct(String product) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
    }

    public void openCart() {
        driver.findElement(CART_BUTTON).click();
    }
}