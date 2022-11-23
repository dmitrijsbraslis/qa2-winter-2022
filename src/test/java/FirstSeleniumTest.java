import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FirstSeleniumTest {
    private final By ACCEPT_COOKIES_BTN = By.id("onetrust-accept-btn-handler");
    private final By TOP_LOCATION_LINK = By.xpath(".//div[@class = 'tl-box']/a");

    private final By AMAZON_ACCEPT_COOKIES_BTN = By.id("sp-cc-accept");
    private final By AMAZON_CONTINUE_BTN = By.xpath(".//input[@data-action-type = 'DISMISS']");
    private final By AMAZON_MAIN_MENU_ITEM = By.xpath(".//div[@id = 'nav-xshop']/a");

    private WebDriver browser;
    private WebDriverWait wait;

    @Test
    public void amazonTest() {
        //Test Data
        String menuItemToSelect = "Best Sellers";

        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.get("http://amazon.de");

        wait = new WebDriverWait(browser, Duration.ofSeconds(10));

        closeAllMessages();
        openMenuItem(menuItemToSelect);

        try {
            browser.findElement(ACCEPT_COOKIES_BTN).click();
        } catch (NoSuchElementException e) {

        }

        //while (true) {}
        //! == "не-"

//        while (!browser.findElements(NEXT_BTN).isEmpty()) {
//            //do something
//        }

//        !true -> false
//        !false -> true
    }

    @Test
    public void anotherAmazonTest() {
        //Test Data
        String menuItemToSelect = "New Releases";

        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.get("http://amazon.de");

        wait = new WebDriverWait(browser, Duration.ofSeconds(10));

        closeAllMessages();
        openMenuItem(menuItemToSelect);

        //while (true) {}
        //! == "не-"

//        while (!browser.findElements(NEXT_BTN).isEmpty()) {
//            //do something
//        }

//        !true -> false
//        !false -> true
    }

    private void openMenuItem(String itemName) {
        List<WebElement> menuItems = browser.findElements(AMAZON_MAIN_MENU_ITEM);
        for (WebElement we: menuItems) {
            if (we.getText().equals(itemName)) {
                we.click();
                break;
            }
        }
    }

    private void closeAllMessages() {
        wait.until(ExpectedConditions.elementToBeClickable(AMAZON_ACCEPT_COOKIES_BTN));
        browser.findElement(AMAZON_ACCEPT_COOKIES_BTN).click();

        wait.until(ExpectedConditions.elementToBeClickable(AMAZON_CONTINUE_BTN));
        browser.findElement(AMAZON_CONTINUE_BTN).click();
    }

    @Test
    public void openHomePageCheck() {
        String country = "Latvia";

        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.get("http://www.discovercars.com/");

        wait = new WebDriverWait(browser, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(ACCEPT_COOKIES_BTN));
        browser.findElement(ACCEPT_COOKIES_BTN).click();

        clickOnCountry(country);


        final By FILTER_OPTION = By.xpath(".//label[contains(@class, 'my-checkbox-label default filtering-event')]");



//        System.out.println("Total filters count: " + .......);


        for (WebElement we : browser.findElements(FILTER_OPTION)) {
            if (we.getAttribute("data-text").equals("Honda")) {
                we.click();
                break;
            }
        }


    }

    private void clickOnCountry(String country) {
        List<WebElement> links = browser.findElements(TOP_LOCATION_LINK);

        boolean isCountryFound = false;
        for (WebElement link : links) {
            if (link.getText().equals(country)) {
                isCountryFound = true;
                wait.until(ExpectedConditions.elementToBeClickable(link));
                link.click();
                break;
            }
        }

        Assertions.assertTrue(isCountryFound, "Country not found!");
    }

    @AfterEach
    public void closeBrowser() {
        browser.close();
    }
}
