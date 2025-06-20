package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public abstract class ProductsPage extends BasePage {

    private static final By TITLE = By.cssSelector("[data-test='title']");
    private static final By CART_BUTTON = By.cssSelector(".shopping_cart_link");
    private static final String ADD_TO_CART_PATTERN =
            "//*[text()='%s']/ancestor::div[@class='inventory_item']//button";

    public ProductsPage(WebDriver driver) {
        super(driver);
        log.debug("Initialized ProductsPage");
    }


    public String getTitle() {
        log.info("Getting products page title");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
            String titleText = driver.findElement(TITLE).getText();
            log.debug("Products page title: '{}'", titleText);
            return titleText;
        } catch (Exception e) {
            log.error("Failed to get products page title: {}", e.getMessage());
            throw e;
        }
    }

    public void addProduct(String product) {
        log.info("Adding product '{}' to cart", product);
        try {
            By addToCartButton = By.xpath(String.format(ADD_TO_CART_PATTERN, product));
            driver.findElement(addToCartButton).click();
            log.debug("Successfully added product '{}' to cart", product);
        } catch (Exception e) {
            log.error("Failed to add product '{}' to cart: {}", product, e.getMessage());
            throw e;
        }
    }

    public void openCart() {
        log.info("Opening shopping cart");
        try {
            driver.findElement(CART_BUTTON).click();
            log.debug("Shopping cart opened successfully");
        } catch (Exception e) {
            log.error("Failed to open shopping cart: {}", e.getMessage());
            throw e;
        }
    }
}