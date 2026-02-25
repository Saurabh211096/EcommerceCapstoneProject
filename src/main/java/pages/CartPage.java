package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By cartItem = By.className("float-cart__content");
    By checkoutButton = By.xpath("//div[@class='buy-btn' and text()='Checkout']");
    By removeButton = By.className("shelf-item__del");
    By emptyCartMessage = By.className("shelf-empty");

    // Verify item displayed
    public boolean isProductDisplayedInCart() {
        return driver.findElement(cartItem).isDisplayed();
    }

    // Remove product
    public void removeProduct() {
        driver.findElement(removeButton).click();
    }

    // Verify cart empty
    public boolean isCartEmpty() {
        return driver.findElement(emptyCartMessage).isDisplayed();
    }

    // Checkout click (already working)
    public void clickCheckout() {
        WebElement checkout = driver.findElement(checkoutButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkout);
    }
}