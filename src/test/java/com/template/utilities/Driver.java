package com.template.utilities;

import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {
    private Driver() {

    }




    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();


    public static WebDriver get() {

        if (driverPool.get() == null) {


            String browser = System.getProperty("browser") != null ? browser = System.getProperty("browser") : ConfigReader.get("browser");

            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();

                    ChromeOptions optionsReal = new ChromeOptions();

                    optionsReal.addArguments("--start-maximized");
                    optionsReal.addArguments("--remote-allow-origins=*");
                    driverPool.set(new ChromeDriver(optionsReal));
                    WebDriverRunner.setWebDriver(driverPool.get());
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(true);

                    options.addArguments("--window-size=1325x744");
                    driverPool.set(new ChromeDriver(options));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    driverPool.get().manage().window().maximize();

                    break;
                case "firefox-headless":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                    break;


                case "safari":
                    if (!System.getProperty("os.name").toLowerCase().contains("mac"))
                        throw new WebDriverException("Your OS doesn't support Safari");
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driverPool.set(new SafariDriver());
                    break;

            }


        }
        return driverPool.get();
    }



    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }

    }
    public static WebDriver getInstance(){
        return  driverPool.get();
    }

    public static void closeSelenideDriver() {


        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
            closeDriver();
        }


    }
}
