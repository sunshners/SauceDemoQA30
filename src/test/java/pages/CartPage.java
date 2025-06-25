package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

@Log4j2
public class CartPage extends BasePage {
    public final String REMOVE_BUTTON = "//*[contains(text(),'%s')]/ancestor::div[@class='cart_item']//button";
    private static final By PRODUCTS_PRICE = By.xpath(
            "//div[@class= 'cart_item']/descendant::div[@class = 'inventory_item_price']");

    public CartPage(WebDriver driver) {
        super(driver);
        log.debug("Initializing CartPage");
    }


    public boolean isProductInCart(String product) {
        log.info("Checking if product '{}' is in cart", product);
        boolean isPresent = driver.findElement(By.xpath(String.format(
                        "//*[@class = 'cart_item']//*[text() = '%s']", product)))
                .isDisplayed();
        log.debug("Product '{}' presence: {}", product, isPresent);
        return isPresent;
    }

    public Double getProductPrice(int index) {
        log.info("Getting price for product at index {}", index);
        List<WebElement> productsName = driver.findElements(PRODUCTS_PRICE);

        if (productsName.isEmpty()) {
            log.warn("No products found in cart when trying to get price for index {}", index);
            return 0.0;
        }

        try {
            String priceText = productsName.get(index).getText();
            Double price = Double.parseDouble(priceText.substring(priceText.indexOf('$') + 1));
            log.debug("Product at index {} has price: {}", index, price);
            return price;
        } catch (Exception e) {
            log.error("Error getting price for product at index {}: {}", index, e.getMessage());
            throw e;
        }
    }

    public void removeProduct(String product) {
        log.info("Remove product {} from cart", product);
        driver.findElement(By.xpath(String.format(REMOVE_BUTTON, product))).click();
    }

    public boolean checkEmptyState() {
        log.info("Checking if cart is empty");
        boolean isEmpty = driver.findElements(By.xpath("//div[@class='cart_item']")).isEmpty();
        log.debug("Cart empty state: {}", isEmpty);
        return isEmpty;
    }
}