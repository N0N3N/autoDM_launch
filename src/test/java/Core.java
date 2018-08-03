import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class Core {
    private WebDriver driver;
    private static final String CHROME_DRIVER_LOCATION = "C://driver/chromedriver.exe";
    private static final Logger LOGGER = LogManager.getLogger(Core.class);

    int i = 0;
    //private List<WebElement> outputList;
    //List<WebElement> outputList;

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

    /*public List<WebElement> selectFromListOfElements (List<WebElement> inputList, String criteria){
        //int i = 0;

        for (WebElement ele : inputList){
            if (ele.getText().contains(criteria)){
                List<WebElement> outputList[i] = ele;
                i = i + 1;
            }
        }
    }*/

    public void printTablename(By path){
        //WebDriverWait wait = new WebDriverWait(driver, 5);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(path));
        System.out.println(driver.findElement(path).getText());
    }

    public void waitPageLoaded(){
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
}
