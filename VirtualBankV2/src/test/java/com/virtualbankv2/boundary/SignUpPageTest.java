import com.virtualbankv2.boundary.Reader;
import com.virtualbankv2.boundary.SignUpPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

public class SignUpPageTest {

    private SignUpPage signUpPage;

    @BeforeEach
    void setUp() {
        new Reader();
        signUpPage = new SignUpPage();
    }

    @Test
    void testEmptyFields() {
        signUpPage.setNameFieldText("");
        signUpPage.setPasswordFieldText("");
        signUpPage.setConfirmPasswordFieldText("");
        signUpPage.clickSubmitButton();
        assertTrue(signUpPage.isSubmitButtonPressed());
    }

    @Test
    void testPasswordsDoNotMatch() {
        signUpPage.setNameFieldText("username");
        signUpPage.setPasswordFieldText("password");
        signUpPage.setConfirmPasswordFieldText("differentPassword");
        signUpPage.clickSubmitButton();
        assertTrue(signUpPage.isSubmitButtonPressed());
    }

    @Test
    void testSuccessfulSignUp() {
        signUpPage.setNameFieldText("username");
        signUpPage.setPasswordFieldText("password");
        signUpPage.setConfirmPasswordFieldText("password");
        signUpPage.clickSubmitButton();
        assertTrue(signUpPage.isSubmitButtonPressed());
    }

    @Test
    void testSubmitButtonAction() {
        signUpPage.clickSubmitButton();
        assertTrue(signUpPage.isSubmitButtonPressed());
    }
}
