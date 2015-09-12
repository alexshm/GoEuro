package goEuro.com.pages;

import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.IOException;
import java.util.ArrayList;


public class ResultPage extends Page {
    private static Logger Log = Logger.getLogger(ResultPage.class);


    @FindBy(id = "sortby-price")
    WebElement sortingPrice;



    public ResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public ResultPage waitUntilResultIsLoaded() {
        try {
            waitUntilElementIsLoaded(sortingPrice);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }


    public ResultPage sortByPrice() {
        clickElement(sortingPrice);
        return this;
    }

    public ArrayList<Float> getResultsArray() {
        ArrayList<Float> arrayList = new ArrayList<>();
        for (int i = 1; i <11; i++) {
            try{
                String beforeComa = driver.findElement(By.xpath("//*[@id=\"results-train\"]/div[1]/div[" + i + "]/div[1]/div/div[2]/table/tbody/tr/td/div/span")).getText();
                String priceString = beforeComa.substring(2);
                float price = Float.parseFloat(priceString);
                System.out.println(price);
                arrayList.add(price);
                continue;
            }    catch (org.openqa.selenium.NoSuchElementException e) {
                    Log.error(e);
            }
        }
        return arrayList;

    }
}