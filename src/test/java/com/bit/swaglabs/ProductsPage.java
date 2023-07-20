package com.bit.swaglabs;

import com.bit.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage extends BasePage {

    WebDriver driver;


    public ProductsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private By backpack = By.id(("add-to-cart-sauce-labs-backpack"));
    private By shoppingCart = By.xpath("//*[@id=\"shopping_cart_container\"]");

   public void clickOnBackpackProduct()
   {
       driver.findElement(backpack).click();
   }

    public void clickOnShoppingCart()
    {
        driver.findElement(shoppingCart).click();
    }



    
}
