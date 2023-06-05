package com.saucedemo;

import base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UnsuccessfulLoginTest extends TestUtil {
    @DataProvider(name = "wrongUsers")
    public Object [][] getUsers(){
        return new Object[][]{
                {"wrongUsername", "secret_sauce"},
                {"standard_user", "wrongPassword"},
                {"wrong", "wrong"}
        };
    }
    @Test(dataProvider = "wrongUsers")
    public void unsuccessfulLogin(String username, String password){
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.click();
        userNameInput.clear();
        userNameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='password']"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.xpath("//input[@name='login-button']"));
        loginBtn.click();

        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMessage.isDisplayed());
    }
}
