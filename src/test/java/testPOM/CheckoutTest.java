package testPOM;

import base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;

public class CheckoutTest extends TestUtil {

    @Test
    public void checkout (){
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login("standard_user","secret_sauce");

        SoftAssert softAssert = new SoftAssert();

        productPage.addItemToTheCart("bike-light");
        softAssert.assertEquals(productPage.getItemsInCart(), "1");

        productPage.addItemToTheCart("backpack");
        softAssert.assertEquals(productPage.getItemsInCart(), "2");


        WebElement shoppingCart = driver.findElement(By.className("shopping_cart_badge"));
        shoppingCart.click();

        WebElement checkoutBtn = driver.findElement(By.id("checkout"));
        checkoutBtn.click();

        CheckoutPage checkout = new CheckoutPage(driver);
        CheckoutPage checkoutPage = checkout.checkInfo("Elizabeta", "Duleva", "1404");

        WebElement backHomeBtn = driver.findElement(By.id("back-to-products"));
        Assert.assertTrue(backHomeBtn.isDisplayed());
        backHomeBtn.click();

    }
}
