package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class LoginPage extends BasePage {

    private static final By USER_NAME_FIELD = By.id("user-name"),
            PASSWORD_FIELD = By.id("password"),
            LOGIN_BUTTON = By.id("login-button"),
            ERROR_MESSAGE = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
        log.debug("Initializing LoginPage");
    }

    @Override
    public BasePage isPageOpened() {
        return null;
    }


    public LoginPage open() {
        log.info("Opening login page at URL: {}", BASE_URL);
        try {
            driver.get(BASE_URL);
            wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
            log.debug("Login page loaded successfully");
        } catch (Exception e) {
            log.error("Failed to open login page: {}", e.getMessage());
            throw e;
        }
        return null;
    }


    public void login(String user, String password) {
        log.info("Attempting to login with username: {}", user);
        try {
            log.debug("Entering username");
            driver.findElement(USER_NAME_FIELD).sendKeys(user);

            log.debug("Entering password");
            driver.findElement(PASSWORD_FIELD).sendKeys(password);

            log.debug("Clicking login button");
            driver.findElement(LOGIN_BUTTON).click();

            log.info("Login attempt completed");
        } catch (Exception e) {
            log.error("Login failed: {}", e.getMessage());
            throw e;
        }
    }

    public String getErrorMessage() {
        try {
            log.info("Retrieving error message");
            wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE));
            String errorText = driver.findElement(ERROR_MESSAGE).getText();
            log.debug("Error message text: {}", errorText);
            return errorText;
        } catch (Exception e) {
            log.error("Failed to get error message: {}", e.getMessage());
            throw e;
        }
    }

    public BasePage successfulLogin(String user, String password) {
        login(user, password);
        log.info("Login successful for user: {}", user);
        return new BasePage(driver) {

            @Override
            public LoginPage open() {
                return null;
            }

            @Override
            public BasePage isPageOpened() {
                return null;
            }
        };
    }
}
