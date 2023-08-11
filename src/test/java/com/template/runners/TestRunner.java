package com.template.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;




    @RunWith(Cucumber.class)
    @CucumberOptions(
            plugin = {
                    "json:target/cucumber.json",
                    "html:target/default-html-reports",
                    "me.jvt.cucumber.report.PrettyReports:target",
                    "pretty",
                    "rerun:target/rerun.txt"
            },

            features = "src/test/resources/features",
            glue ="com/template/step_defs",
            publish = false,

            tags = "@wip",

            dryRun =false




    )
    public class TestRunner {

    }


