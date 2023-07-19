package com.bit.swaglabs;

import com.bit.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    private final WebElement usernameTXT = driver.findElement(By.xpath("//*[@id='user-name']"));
    private final WebElement passwordTXT = driver.findElement(By.xpath("//*[@id='password']"));
    private final WebElement loginBTN = driver.findElement(By.xpath("//*[@id='login-button']"));
    public void login(String user, String pwd) {
        if (usernameTXT.isDisplayed()) {
            usernameTXT.clear();
            usernameTXT.sendKeys(user + Keys.ENTER);
        }
        if (passwordTXT.isDisplayed()) {
            passwordTXT.clear();
            passwordTXT.sendKeys(pwd + Keys.ENTER);
        }
        if (loginBTN.isDisplayed()) {
            loginBTN.click();
        }
    }
}
