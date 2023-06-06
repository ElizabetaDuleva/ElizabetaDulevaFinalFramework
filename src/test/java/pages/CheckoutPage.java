package pages;

import base.TestUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {

    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCart;

    @FindBy(id = "checkout")
    private WebElement checkoutBtn;

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postCodeInput;

    @FindBy(id = "continue")
    private WebElement continueBtn;

    @FindBy(id = "finish")
    private WebElement finishBtn;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CheckoutPage checkInfo(String firstname, String lastname, String postcode) {
        firstNameInput.click();
        firstNameInput.clear();
        firstNameInput.sendKeys(firstname);

        lastNameInput.click();
        lastNameInput.clear();
        lastNameInput.sendKeys(lastname);

        postCodeInput.click();
        postCodeInput.clear();
        postCodeInput.sendKeys(postcode);

        continueBtn.click();

        finishBtn.click();

        return new CheckoutPage(driver);

    }
}