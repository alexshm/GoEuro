package goEuro.com;


import goEuro.com.pages.ResultPage;
import goEuro.com.pages.SearchPage;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.Comparator;

import static org.testng.AssertJUnit.assertTrue;

public class sortTest extends BaseTest {
    public static String from = "Madrid, Spain";
    public static String to = "Barcelona, Spain";
    private static Logger Log = Logger.getLogger(sortTest.class);
    public SearchPage searchPage;
    public ResultPage resultPage;

    @Before
    public void setup() {
        searchPage = new SearchPage(driver);
        resultPage = new ResultPage(driver);
        try {
            searchPage.openSearchPage()
                    .waitUntilLoginPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   @Test
    public void sortTest() {
        Log.info("Checking that all correct data added successfully");
        try {
            searchPage
                    .fillfromField(from)
                    .fillToField(to);
            driver.findElement(By.id("search-form__submit-btn")).click();
            searchPage.clickOnSearch();
//            searchPage.clickOnSearch();
//            String winHandle = driver.getWindowHandles().iterator().next();
//            driver.switchTo().window(winHandle);
            resultPage.waitUntilResultIsLoaded();
            resultPage.sortByPrice();
            checkIfSorted();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Login successful");

    }

    private void checkIfSorted() {
        ArrayList<Float> array = resultPage.getResultsArray();
        ArrayList<Float> copy = new ArrayList<Float>(array);
        copy.sort(Comparator.<Float>naturalOrder());
        assertTrue(CollectionUtils.isEqualCollection(array, copy));

    }


}
