package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.BasePage;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

import java.time.Duration;
import java.util.HashMap;

import static tests.AllureUtils.takeScreenshot;

@Listeners(TestListener.class)
public class BaseTest {
    WebDriver driver;
    SoftAssert softAssert;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    String user = System.getProperty("standart_user");
    String password = System.getProperty("secret_sauce");

    @Parameters({"browser"})
    @BeforeMethod (alwaysRun = true)
    public void setup(@Optional ("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")){
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        driver = new ChromeDriver(options);
    } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver) {
            @Override
            public LoginPage open() {
                return null;
            }

            @Override
            public BasePage isPageOpened() {
                return null;
            }
        };
        cartPage = new CartPage(driver) {
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

        @AfterMethod(alwaysRun = true, description = "Закрытие браузера")
        public void tearDown(ITestResult result) {
            if (ITestResult.FAILURE == result.getStatus()) {
                takeScreenshot(driver);
            }
            if (driver != null) {
                driver.quit();
            }
        }
}
