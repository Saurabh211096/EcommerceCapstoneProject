package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;
import utils.BaseTest;

public class AddToCartTest extends BaseTest {

    @Test
    public void TC_004_AddSingleItem() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithValidCredentials();

        ProductPage productPage = new ProductPage(driver);
        productPage.addFirstProductToCart();
        productPage.openCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isProductDisplayedInCart(),
                "Product not displayed in cart");
    }

    @Test
    public void TC_005_AddMultipleItems_VerifyCartCount() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithValidCredentials();

        ProductPage productPage = new ProductPage(driver);
        productPage.addMultipleProducts(2);

        int cartCount = productPage.getCartQuantity();
        Assert.assertEquals(cartCount, 2,
                "Cart count mismatch for multiple items");
    }

    @Test
    public void TC_006_RemoveItemFromCart() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithValidCredentials();

        ProductPage productPage = new ProductPage(driver);
        productPage.addFirstProductToCart();
        productPage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.removeProduct();

        Assert.assertTrue(cartPage.isCartEmpty(),
                "Cart is not empty after removing item");
    }
}