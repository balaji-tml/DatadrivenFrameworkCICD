package com.bit.swaglabs;

import com.bit.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage extends BasePage {

    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    private By finish = By.id("finish");

    public void clickOnFinish()
    {
        driver.findElement(finish).click();

    }
}
