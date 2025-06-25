package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
public class ProductsPage extends BasePage {

    private static final By TITLE = By.cssSelector("[data-test='title']");
    private static final By UNIVERSAL_PRODUCT_NAME = By.xpath("//*[@data-test='inventory-item-name']");
    private static final By UNIVERSAL_PRODUCT_PRICE = By.xpath("//*[@data-test='inventory-item-price']");
    private static final By PRODUCT_SORT = By.xpath("//*[@data-test='product-sort-container']");
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

    public List getAllProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement el : driver.findElements(UNIVERSAL_PRODUCT_NAME)) {
            names.add(el.getText());
        }
        return names;
    }

    public List getAllProductPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement el : driver.findElements(UNIVERSAL_PRODUCT_PRICE)) {
            prices.add(Double.parseDouble(el.getText().replaceAll("\\$", "")));
        }
        return prices;
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

    @Step("Сортировка {typeOfSort}")
    public ProductsPage sort(String typeOfSort) {
        log.info("Sort by {}", typeOfSort);
        Select select = new Select(driver.findElement(PRODUCT_SORT));
        select.selectByVisibleText(typeOfSort);
        return this;
    }

    public boolean isSort(String typeOfSort) {
        List<String> actualNames = getAllProductNames();
        List<Double> actualPrices = getAllProductPrices();
        List<String> expectedSortedNames = new ArrayList<>(actualNames);
        List<Double> expectedSortedPrices = new ArrayList<>(actualPrices);

        switch (typeOfSort) {
            case "Name (A to Z)":
                Collections.sort(expectedSortedNames);
                return actualNames.equals(expectedSortedNames);
            case "Name (Z to A)":
                expectedSortedNames.sort(Collections.reverseOrder());
                return actualNames.equals(expectedSortedNames);
            case "Price (low to high)":
                Collections.sort(expectedSortedPrices);
                return actualPrices.equals(expectedSortedPrices);
            case "Price (high to low)":
                expectedSortedPrices.sort(Collections.reverseOrder());
                return actualPrices.equals(expectedSortedPrices);
            default:
                return false;
        }
    }
}