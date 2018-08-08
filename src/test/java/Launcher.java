import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class Launcher {
    private Core core = new Core();
    private static final String WEB_PAGE = "http://autodm:8080/assets/index.html#/dealers";

    private static final Logger LOGGER = LogManager.getLogger(Launcher.class);

    private int i;
    private int numberToStart;
    private int numberSemiState;

    private static final String NAME = "Tex_"; //Change to necessary table name component
    //private static final String NAME = "DNT release QA";

    private static final String FINISHED = "Finished";
    private static final String FAILED = "Failed";
    private static final String RUNNING = "Running";
    private static final By TABLE_NAMES = By.xpath("//tr/td[4][contains(text()[2],'" + NAME + "')]/../td[8][contains(b, '"+FINISHED+"') or contains(b, '"+FAILED+"')]/../td[10]/../td[4]");
    private static final By TABLES_LIST = By.xpath("//tr/td[4][contains(text()[2],'" + NAME + "')]/../td[8][contains(b, '"+FINISHED+"') or contains(b, '"+FAILED+"')]/../td[10]");
    private static final By TABLES_STATUS = By.xpath("//tr/td[4][contains(text()[2],'" + NAME + "')]/../td[8]");
    private static final By TABLES_STATUS_NAME = By.xpath("//tr/td[4][contains(text()[2],'" + NAME + "')]/../td[8]/../td[4]");


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
            System.out.println(i + ".  " + tableStatus.get(i).getText() + "  " + tableStatusNames.get(i).getText());
            if (tableStatus.get(i).getText().contains(FINISHED) || tableStatus.get(i).getText().contains(FAILED)) {
                numberToStart = numberToStart + 1;
            }
            if (!tableStatus.get(i).getText().contains(FINISHED) && !tableStatus.get(i).getText().contains(FAILED)&& !tableStatus.get(i).getText().contains(RUNNING)) {
                numberSemiState = numberSemiState + 1;
            }
        }
        System.out.println("  ");
        System.out.println("Number of tables to start: " + numberToStart);
        if (numberSemiState < 0) {
            System.out.println("  ");
            System.out.println("WARNING: Number of tables with semi-state: " +numberSemiState+" !     Recommended to wait and relaunch tables later");
        }
        if (numberToStart == 0) {
            System.out.println("No tables with "+FINISHED+" or "+FAILED+" status");
            core.closeDriver();
            System.exit(100);
        }
    }


    private void startTables() {
        List<WebElement> tableNames = core.getListOfElements(TABLE_NAMES);
        List<WebElement> tables = core.getListOfElements(TABLES_LIST);

        LOGGER.info("Starting tables with "+FINISHED+" or "+FAILED+" status:");
        System.out.println("  ");
        System.out.println("Starting tables:");
        for (i = 0; i < tables.size(); i++) {
            System.out.println(i + ".  Starting table: " + tableNames.get(i).getText());
            tables.get(i).click();
        }
    }

    @Test
    public void autoDMLaunch() {
        openHomePageDesk();
        printTableStatus();
        startTables();
        core.closeDriver();
    }
}
