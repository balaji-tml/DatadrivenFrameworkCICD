package com.bit.utilities;

import org.openqa.selenium.WebElement;

public class ReusableFunctions {

    public void click(WebElement elm)
    {
        if(elm.isDisplayed())
        {
            elm.click();
        }
        else {
            new RuntimeException();
        }

    }
}
