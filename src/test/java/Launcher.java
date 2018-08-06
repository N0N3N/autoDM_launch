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


    public static final By TABLE_NAMES = By.xpath("//tr/td[4][contains(text()[2],'Tex_')]/../td[8][contains(b, 'Finished')]/../td[10]/../td[4]");
    public static final By TABLES_LIST = By.xpath("//tr/td[4][contains(text()[2],'Tex_')]/../td[8][contains(b, 'Finished') or contains(b, 'Failed')]/../td[10]");

    public void openHomePageDesk() {
        LOGGER.info("Open HomePage for Desktop");
        core.openWebPage(WEB_PAGE);
        core.waitPageLoaded();
    }


    public void startTablesWithNames() {
        System.out.println("Starting tables");

        tableNames = core.getListOfElements(TABLE_NAMES);
        tables = core.getListOfElements(TABLES_LIST);

        for (i = 0; i < tables.size(); i++) {
            System.out.println("Clicking table Nr." + i + "  " + tableNames.get(i).getText());
            tables.get(i).click();
        }
    }

    @Test
    public void autoDMlaunch() {
        openHomePageDesk();
        startTablesWithNames();
        core.closeDriver();
    }


}
