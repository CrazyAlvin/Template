package com.template.step_defs;

import io.cucumber.java.en.*;

public class MainPage_step_defs {
    @Given("user is on the home page")
    public void user_is_on_the_home_page() {
        System.out.println("User will go to page in Hooks");
    }
}
