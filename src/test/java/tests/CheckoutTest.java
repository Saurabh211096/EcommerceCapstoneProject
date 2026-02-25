package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;
import pages.CheckoutPage;
import utils.BaseTest;

public class CheckoutTest extends BaseTest {

    @Test
    public void TC_007_PlaceOrderWithValidDetails() {

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithValidCredentials();

        // Add product
        ProductPage productPage = new ProductPage(driver);
        productPage.addFirstProductToCart();
        productPage.openCart();

        // Click Checkout
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        // Fill shipping details
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterShippingDetails();
        checkoutPage.clickSubmit();

        // Verify order placed
        boolean orderPlaced = checkoutPage.isOrderPlacedSuccessfully();

        Assert.assertTrue(orderPlaced, "Order was not placed successfully");
    }


    @Test
    public void TC_008_CheckoutWithoutAddingItems() {

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithValidCredentials();

        // Open cart without adding product
        ProductPage productPage = new ProductPage(driver);
        productPage.openCart();

        // Verify cart is empty
        CartPage cartPage = new CartPage(driver);

        Assert.assertTrue(cartPage.isCartEmpty(),
                "Cart is not empty when no products were added");
    }
}