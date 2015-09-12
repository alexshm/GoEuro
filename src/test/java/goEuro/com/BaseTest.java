package goEuro.com;


import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.selenium.factory.WebDriverFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Base class for TestNG-based test classes
 */
public class BaseTest {

  public WebDriver driver;

  @Before
  public void init() throws IOException {
    PropertyConfigurator.configure("log4j.properties");
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
  }

  @After
  public void tearDown() {
    if (driver != null) {
     driver.close();
    }
  }
}
