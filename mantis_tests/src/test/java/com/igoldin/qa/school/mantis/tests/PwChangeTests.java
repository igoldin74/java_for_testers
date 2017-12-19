package com.igoldin.qa.school.mantis.tests;

import com.igoldin.qa.school.mantis.appmanager.HttpSession;
import com.igoldin.qa.school.mantis.model.MailMessage;
import com.igoldin.qa.school.mantis.model.UserData;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PwChangeTests extends TestBase {


    private SessionFactory sessionFactory;
    private WebDriver wd;

    @BeforeClass(enabled = true)
    protected void setUpSessionFactory() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }

    }

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }


    @Test
    public void changePwTest() throws IOException {
        UserData user = app.db().users().iterator().next();
        int id = user.getId();
        String email = user.getEmail();
        String username = user.getUsername();
        String newPassword = "qwerty";
        app.getDriver();
        app.sh().login("administrator", "root");
        app.navigation().click(By.linkText("Manage Users"));
        app.navigation().click(By.cssSelector(String.format("a[href^='manage_user_edit_page.php?user_id=%s']", id)));
        app.navigation().click(By.cssSelector("input[value='Reset Password']"));
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.getDriver().get(confirmationLink);
        String verifyEmail = app.getDriver().findElement(By.name("email")).getAttribute("value");
        Assert.assertEquals(email, verifyEmail);
        app.getDriver().findElement(By.name("password")).sendKeys(newPassword);
        app.getDriver().findElement(By.name("password_confirm")).sendKeys(newPassword);
        app.navigation().click(By.cssSelector("input[value='Update User']"));

        HttpSession session = app.newSession();
        assertTrue(session.login(username, newPassword));
        assertTrue(session.isLoggedInAs(username));

    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}
