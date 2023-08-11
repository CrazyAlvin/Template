package com.template.step_defs;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.template.utilities.ConfigReader;
import com.template.utilities.Driver;
import com.template.utilities.Environment;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import static com.codeborne.selenide.Selenide.open;

public class Hooks {
    @Before(value = "@ui")
    public synchronized void setUp() {
        System.out.println("go on before");
        Configuration.browserSize = "2560x1440";
        Configuration.timeout = 30000;
        Configuration.browser = ConfigReader.get("browser");
        Configuration.baseUrl = "https://dev.communitygaming.io/";
        WebDriverRunner.setWebDriver(Driver.get());
        open(Environment.URL);


    }
    @After(value = "@ui")
    public void tearDownUI(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = new byte[0];
            try {
                screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            } catch (Exception e) {
                screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            }

        try {
            Driver.closeSelenideDriver();
        } catch (Exception e) {

        }
    }
}}