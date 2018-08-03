import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Core {
    private WebDriver driver;
    private static final String CHROME_DRIVER_LOCATION = "C://driver/chromedriver.exe";
    private static final Logger LOGGER = LogManager.getLogger(Core.class);


    public Core() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_LOCATION);
        this.driver = new ChromeDriver();
    }

    public void openWebPage(String url) {
        if (!url.contains("http://") && !url.contains("https://")) { url = "http://" + url; }
        LOGGER.info("Opening web page: " + url);
        driver.get(url);
    }
    public void closeDriver(){
        driver.close();
    }

    public List<WebElement> getListOfElements (By path){
        List<WebElement> elements = driver.findElements(path);
        return elements;
    }

    public void printTablename(By path){
        //WebDriverWait wait = new WebDriverWait(driver, 5);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(path));
        System.out.println(driver.findElement(path).getText());
    }

    public void waitPageLoaded(){
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
}
