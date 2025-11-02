package base;

import com.example.api.AuthorsClient;
import com.example.api.BooksClient;
import org.testng.annotations.BeforeClass;

import java.util.Random;

public abstract class BaseApiTest {

    protected static final Random RANDOM = new Random();

    protected BooksClient booksClient;
    protected AuthorsClient authorsClient;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        booksClient = new BooksClient();
        authorsClient = new AuthorsClient();
    }
}
