package base;

import com.example.api.AuthorsClient;
import com.example.api.BooksClient;
import com.example.util.report.AllureHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.util.Random;

public abstract class BaseApiTest {

    protected static final Random RANDOM = new Random();

    protected BooksClient booksClient;
    protected AuthorsClient authorsClient;

    @BeforeTest(alwaysRun = true)
    public void setUpAllure() {
        AllureHelper.addEnvironmentInfo();
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClients() {
        booksClient = new BooksClient();
        authorsClient = new AuthorsClient();
    }
}
