import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Launcher {
    private Core core = new Core();
    private static final String WEB_PAGE = "http://autodm:8080/assets/index.html#/dealers";

    private static final Logger LOGGER = LogManager.getLogger(Launcher.class);
    private List<WebElement> tableNames;
    private List<WebElement> tables;
    int i = 0;


    public static final By TABLE_NAMES =  By.xpath("//td[4][@class='ng-binding']");
    //public static final By TABLE_NAMES =  By.cssSelector("td[class='ng-binding'][value='Tex_CHP']");
    //  By.cssSelector("td[class='ng-binding'][value='Tex_CHP']");

    public static final By TABLES_LIST = By.xpath("//tr/td[4][contains(text()[2],'Tex_')]/../td[8][contains(b, 'Finished')]/../td[10]");

    public void openHomePageDesk(){
        LOGGER.info("Open HomePage for Desktop");
        core.openWebPage(WEB_PAGE);
        core.waitPageLoaded();
    }

    public List<WebElement> getTableNames0() {
        System.out.println("Getting List of Table name");
        tableNames = core.getListOfElements(TABLE_NAMES);
        //System.out.println(tableNames.get(0).getText());
        for (WebElement ele : tableNames){
            i = i+1;

            //System.out.println("Getting name ");
            System.out.println("Nr."+ i + "  " + ele.getText());
        }
        return tableNames;
    }

    public void startTables(){
        System.out.println("Starting tables");
        tables = core.getListOfElements(TABLES_LIST);
        for (WebElement table:tables){
            i = i+1;
            System.out.println("Clicking table Nr."+i);
            table.click();
        }
    }

    @Test
    public void autoDMlaunch(){
        openHomePageDesk();
        //getTableNames0();

        startTables();

        //core.printTablename(TABLE_NAMES);
        core.closeDriver();
    }


}
