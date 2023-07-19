package com.bit.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.bit.utilities.ExcelReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.bit.utilities.TestConfiguration.getCurrentPlatform;

public class BaseTest {

    protected static WebDriver driver;
    protected Properties config = new Properties();
    Properties OR = new Properties();
    FileInputStream fis = null;
    protected static Logger log;
//    public ExcelReader excel;

    protected String userDir = System.getProperty("user.dir");
    public String path = userDir + "/src/test/resources/excel/Testdata.xlsx";
    public ExcelReader excel = new ExcelReader(path);

    //public ExtentReports rep = ExtentManager.createExtentReports();
    public ExtentReports extent = null;

    public ExtentTest test = null;


    //String browser,url;

    @BeforeSuite
    public void setUp()
    {
        log = LogManager.getLogger(BaseTest.class);
        log.info("In start of the setUp()");
        String userDir,browser,url;
        Platform platform = getCurrentPlatform();
        log.info("Platform is: "+platform.toString());
        //extent = ExtentManager.createExtentReports();
        userDir = System.getProperty("user.dir");
//        String path = "src/test/resources/excel/Testdata.xlsx";
        log = LogManager.getLogger(BaseTest.class);
//        excel = new ExcelReader(path);
        if(driver == null)
        {
            try {
                fis = new FileInputStream(userDir + "/src/test/resources/properties/Config.properties");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                config.load(fis);
                log.info("Config file is loaded!");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            browser = config.getProperty("browser");
            if(browser.equals("chrome"))
            {
                switch (platform) {
                    case MAC:
//                        System.setProperty("webdriver.chrome.driver", userDir + "/src/test/resources/executables/chromedriver.exe");
//                        break;
                    case WINDOWS:
                        System.setProperty("webdriver.chrome.driver", userDir + "/src/test/resources/executables/chromedriver.exe");
                        break;
                    case LINUX:
                        System.setProperty("webdriver.chrome.driver", userDir + "/src/test/resources/executables/chromedriver");
                        break;
                    default:
                        System.out.println("Please select valid browser!\n");
                        break;
                }

                //System.setProperty("webdriver.chrome.driver", userDir + "/src/test/resources/executables/chromedriver");
                ChromeOptions options = new ChromeOptions();
                //options.setExperimentalOption("prefs", chromePrefs);
                options.addArguments("--no-sandbox");
                options.addArguments("--headless"); //!!!should be enabled for Jenkins
                options.addArguments("--disable-dev-shm-usage"); //!!!should be enabled for Jenkins
                options.addArguments("--window-size=1920x1080"); //!!!should be enabled for Jenkins
                driver = new ChromeDriver();
                log.info("Chrome browser is loaded!");
            }
            driver.manage().window().maximize();
        }
        log.info("In end of the setUp()");
    }

    @AfterSuite
    public void tearDown()
    {
        log.info("In start of the tearDown()");
        if(driver !=null)
        {
            driver.quit();
            log.info("Browser is closed!");
        }
        log.info("In end of the tearDown()");
    }

    public WebDriver getDriver() {
        return driver;
    }
}
