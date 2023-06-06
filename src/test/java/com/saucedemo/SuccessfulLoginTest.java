package com.saucedemo;

import base.TestUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class SuccessfulLoginTest extends TestUtil {


    @Test(dataProvider = "correctUsers")
    public void successfulLoginTest(String username,String password){
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.click();
        userNameInput.clear();
        userNameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement productPage = driver.findElement(By.className("title"));
        Assert.assertTrue(productPage.isDisplayed());

        WebElement menuBtn = driver.findElement(By.id("react-burger-menu-btn"));
        Assert.assertTrue(menuBtn.isDisplayed());
        menuBtn.click();

        WebElement logoutLink = driver.findElement(By.id("logout_sidebar_link"));

        FluentWait fluentWait = new FluentWait(driver)
                .pollingEvery(Duration.ofSeconds(3));
        fluentWait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        Assert.assertTrue(logoutLink.isDisplayed());

    }
    @DataProvider(name = "correctUsers")
    public Object [][] readUsersFromCsv(){
        try {
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/correctUsers.csv"));
            List<String[]> csvData = csvReader.readAll();
            Object [][] csvDataObj = new Object[csvData.size()][2];

            for (int i = 0; i < csvData.size(); i++) {
                csvDataObj[i]=csvData.get(i);
            }
            return csvDataObj;
        }catch (IOException e){
            System.out.println("Not possible to find the file!");
            return null;
        }catch (CsvException e){
            return null;
        }

    }


}
