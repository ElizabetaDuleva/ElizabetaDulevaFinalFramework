package testPOM;

import base.TestUtil;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.ProductPage;

public class ProblemTest extends TestUtil {

    @Test
    public void successfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login("problem_user", "secret_sauce");

        SoftAssert softAssert = new SoftAssert();

        productPage.addItemToTheCart("backpack");
        softAssert.assertEquals(productPage.getItemsInCart(), 1, "Element was not added to the cart");

        productPage.addItemToTheCart("bike-light");
        softAssert.assertEquals(productPage.getItemsInCart(), 2, "Element was not added to the cart");

        productPage.addItemToTheCart("bolt-t-shirt");
        softAssert.assertEquals(productPage.getItemsInCart(), 3, "Element was not added to the cart");

        softAssert.assertAll();
    }
}
