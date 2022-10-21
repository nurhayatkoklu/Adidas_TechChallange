import Utils.BaseStaticDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Steps extends BaseStaticDriver {

    public static void goToWebsite() {
        driver.get("https://www.demoblaze.com/index.html");
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public static void login() {
        WebElement login= driver.findElement(By.id("login2"));
        WebElement username=driver.findElement(By.id("loginusername"));
        WebElement password=driver.findElement(By.id("loginpassword"));
        WebElement loginButton=driver.findElement(By.cssSelector("[onclick='logIn()']"));

        Click(login);
        sendKeys(username, "nkoklu");
        sendKeys(password, "1234*4321");
        Click(loginButton);
        Wait(1);
    }

    public static void navigateThroughProducts() {
        JavascriptExecutor js=(JavascriptExecutor) driver;

        WebElement phones= driver.findElement(By.xpath("//*[text()='Phones']"));
        WebElement laptops=driver.findElement(By.xpath("//*[text()='Laptops']"));
        WebElement monitors=driver.findElement(By.xpath("//*[text()='Monitors']"));

        Click(phones);
        js.executeScript("window.scrollBy(0,300)");
        Wait(1);
        js.executeScript("window.scrollBy(0,300)");
        Wait(1);
        js.executeScript("window.scrollBy(0,300)");
        Wait(1);
        js.executeScript("window.scrollBy(0,300)");
        Wait(1);
        js.executeScript("window.scrollBy(0,300)");
        Wait(1);
        js.executeScript("window.scrollBy(0,-1500)");
        Wait(1);

        Click(laptops);
        js.executeScript("window.scrollBy(0,300)");
        Wait(1);
        js.executeScript("window.scrollBy(0,300)");
        Wait(1);
        js.executeScript("window.scrollBy(0,300)");
        Wait(1);
        js.executeScript("window.scrollBy(0,-900)");
        Wait(1);

        Click(monitors);
        js.executeScript("window.scrollBy(0,300)");
        Wait(1);
        js.executeScript("window.scrollBy(0,300)");
        Wait(1);

        js.executeScript("window.scrollBy(0,-600)");
        Wait(1);

    }

    public static void navigateToLaptop() {
        WebElement laptops=driver.findElement(By.xpath("//*[text()='Laptops']"));
        Click(laptops);
    }

    public static void navigateToSony() {
        WebElement sony=driver.findElement(By.linkText("Sony vaio i5"));
        Click(sony);

    }
    public static void addSonyToCart() throws AWTException {
        WebElement addToCart=driver.findElement(By.xpath("//*[text()='Add to cart']"));
        Click(addToCart);
        Wait(1);
        pressEnter();
        Wait(1);
    }

    public static void navigateToDell() throws AWTException {
        WebElement dell=driver.findElement(By.linkText("Dell i7 8gb"));
        Click(dell);

    }

    public static void addDellToCart() throws AWTException {
        WebElement addToCart=driver.findElement(By.xpath("//*[text()='Add to cart']"));
        Click(addToCart);
        Wait(1);
        pressEnter();
        Wait(1);
    }


    public static void goToCart() {
        WebElement cart = driver.findElement(By.linkText("Cart"));
        Click(cart);
    }

    public static void deleteDellFromCart() {
        WebElement deleteDell = driver.findElement(By.xpath("(//*[text()='Delete'])[2]"));
        Click(deleteDell);
        Wait(2);
    }

    public static void placeOrder() {
        WebElement placeOrder = driver.findElement(By.xpath("//button[text()='Place Order']"));
        Click(placeOrder);
    }

    public static void fillTheForm() throws AWTException {
        WebElement name = driver.findElement(By.id("name"));
        WebElement country = driver.findElement(By.id("country"));
        WebElement city = driver.findElement(By.id("city"));
        WebElement creditCard = driver.findElement(By.id("card"));
        WebElement month = driver.findElement(By.id("month"));
        WebElement year = driver.findElement(By.id("year"));


        sendKeys(name, "Jack Stone");
        sendKeys(country, "US");
        sendKeys(city, "Florida");
        sendKeys(creditCard, "15488");
        sendKeys(month, "04");
        sendKeys(year,"2025");

    }

    public static void clickPurchase() {
        WebElement purchase = driver.findElement(By.xpath("//*[text()='Purchase']"));
        Click(purchase);
    }

    public static void takeScreenShot() throws IOException {
        Wait(2);
        DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime now=LocalDateTime.now();
        String purchaseSS= "logPurchaseIDandAmount" + dtf.format(now) + ".png";
        Wait(1);
        File purchaseScreenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(purchaseScreenshotFile, new File("PurchaseSS" + purchaseSS));

    }

    public static void assertAmount() {
        WebElement assertAmount=driver.findElement(By.xpath("//*[text()='Amount: 790 USD']"));
        Assert.assertTrue(assertAmount.getText().contains("790"));
    }

    public static void clickOK() {
        WebElement okButton=driver.findElement(By.xpath("//button[text()='OK']"));
        okButton.click();

    }




    public static void pressEnter() throws AWTException {

      Robot rbt=new Robot();
      rbt.keyPress(KeyEvent.VK_ENTER);
      rbt.keyRelease(KeyEvent.VK_ENTER);
    }

    public static void clickHome() {
        WebElement home=driver.findElement(By.xpath("//*[text()='Home ']"));
        Click(home);
    }

    public static void Click(WebElement element) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public static void sendKeys(WebElement element, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        element.clear();
        element.sendKeys(value);
    }
}
