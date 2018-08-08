import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Launcher {
    private Core core = new Core();
    private static final String WEB_PAGE = "http://autodm:8080/assets/index.html#/dealers";

    private static final Logger LOGGER = LogManager.getLogger(Launcher.class);

    int i = 0;

    private static final String NAME = "Tex_"; //Change to necessary table name component

    private static final By TABLE_NAMES = By.xpath("//tr/td[4][contains(text()[2],'" + NAME + "')]/../td[8][contains(b, 'Finished') or contains(b, 'Failed')]/../td[10]/../td[4]");
    private static final By TABLES_LIST = By.xpath("//tr/td[4][contains(text()[2],'" + NAME + "')]/../td[8][contains(b, 'Finished') or contains(b, 'Failed')]/../td[10]");
    private static final By TABLES_STATUS = By.xpath("//tr/td[4][contains(text()[2],'" + NAME + "')]/../td[8]");
    private static final By TABLES_STATUS_NAME = By.xpath("//tr/td[4][contains(text()[2],'" + NAME + "')]/../td[8]/../td[4]");

    //private List<WebElement> tableNames = core.getListOfElements(TABLE_NAMES);
    //private List<WebElement> tables = core.getListOfElements(TABLES_LIST);
    //private List<WebElement> tableStatus = core.getListOfElements(TABLES_STATUS);
    //private List<WebElement> tableStatusNames = core.getListOfElements(TABLES_STATUS_NAME);

    private void openHomePageDesk() {
        LOGGER.info("Open HomePage for Desktop");
        core.openWebPage(WEB_PAGE);
        core.waitPageLoaded();
    }


    private void printTableStatus() {
        LOGGER.info("Collecting table status info");
        List<WebElement> tableStatus = core.getListOfElements(TABLES_STATUS);
        List<WebElement> tableStatusNames = core.getListOfElements(TABLES_STATUS_NAME);
        System.out.println("  ");
        System.out.println("Table status:");
        for (i = 0; i < tableStatus.size(); i++) {
            System.out.println(i + ".  " + tableStatusNames.get(i).getText() + "  " + tableStatus.get(i).getText());
        }
    }

    private void startTables() {
        List<WebElement> tableNames = core.getListOfElements(TABLE_NAMES);
        List<WebElement> tables = core.getListOfElements(TABLES_LIST);

        //try {
        //    System.out.println("  ");
        //    LOGGER.info("Asserting list");
        //    Assert.assertFalse("No tables with Finished or Failed status", tables.isEmpty());
        //} finally {

            if (!tables.isEmpty()) {

                LOGGER.info("Starting tables with 'Finished' or 'Failed' status:");
                System.out.println("  ");
                System.out.println("Starting tables:");
                for (i = 0; i < tables.size(); i++) {
                    System.out.println(i + ".  Starting table: " + tableNames.get(i).getText());
                    tables.get(i).click();
                }
                core.closeDriver();
            } else {
                System.out.println("  ");
                System.out.println("No tables with Finished or Failed status");
                core.closeDriver();
           // }
        }
    }

    @Test
    public void autoDMlaunch() {
        openHomePageDesk();
        printTableStatus();
        startTables();
    }
}
