package goEuro.com.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;


public class SearchPage extends Page {

    private static Logger Log = Logger.getLogger(SearchPage.class);

    @FindBy(id = "search")
    WebElement searchBlock;

    @FindBy(id = "from_filter")
    WebElement from;

    @FindBy(id = "to_filter")
    WebElement to;

    @FindBy(id ="search-form__submit-btn")
    WebElement searchButton;


    public SearchPage(WebDriver driver) {
        super(driver);
        this.PAGE_URL = "http://www.goeuro.com/";
        PageFactory.initElements(driver, this);
    }

    public SearchPage openSearchPage() {
        Log.info("Opening login page");
        driver.get(PAGE_URL);
        return this;
    }

    public SearchPage waitUntilLoginPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(searchBlock);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }return this;
    }



    public SearchPage fillfromField(String user) {
        setElementText(from, user);
        Log.info("entering from: " + user + " ");
        return this;
    }

    public SearchPage fillToField(String password) {
        setElementText(to, password);
        Log.info("entering to: " + password + " ");
        return this;
    }

    public SearchPage clickOnSearch() {
        clickElement(searchButton);
        Log.info("clicking on sort: " + searchButton + " ");
        return this;
    }



}
