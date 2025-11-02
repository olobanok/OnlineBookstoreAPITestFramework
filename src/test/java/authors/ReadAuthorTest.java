package authors;

import base.BaseApiTest;
import io.qameta.allure.Feature;
import io.restassured.common.mapper.TypeRef;
import com.example.model.Author;
import com.example.model.Book;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

import static constant.Groups.*;
import static org.assertj.core.api.Assertions.assertThat;
import static com.example.spec.ResponseSpecifications.OK_200;

@Feature(AUTHORS)
public class ReadAuthorTest extends BaseApiTest {

    protected List<Author> authorList;

    @Test(groups = {AUTHORS, SMOKE})
    public void testGetAuthorsList() {
        authorList = authorsClient.list()
                .spec(OK_200)
                .extract()
                .as(new TypeRef<>() {});
        assertThat(authorList).as("Authors list should not be empty").isNotEmpty();
    }

    @Test(groups = {AUTHORS, SMOKE}, dependsOnMethods = "testGetAuthorsList")
    public void testGetAuthorByID() {
        Author randomAuthor = authorList.get(RANDOM.nextInt(authorList.size()));
        Author retrievedAuthor = authorsClient.getById(randomAuthor.id())
                .spec(OK_200)
                .extract()
                .as(Author.class);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(retrievedAuthor.id()).as("Author ID").isEqualTo(randomAuthor.id());
            softly.assertThat(retrievedAuthor.firstName()).as("First name").isEqualTo(randomAuthor.firstName());
            softly.assertThat(retrievedAuthor.lastName()).as("Last name").isEqualTo(randomAuthor.lastName());
        });
    }

    @Test(groups = {AUTHORS, SMOKE})
    public void testGetAuthorByBookID() {
        List<Book> bookList = booksClient.list().extract().as(new TypeRef<>() {});
        Book randomBook = bookList.get(RANDOM.nextInt(bookList.size()));
        List<Author> authorList = authorsClient.getByBookId(randomBook.id())
                .spec(OK_200)
                .extract()
                .as(new TypeRef<>() {});
        assertThat(authorList).as("Authors list should not be empty").isNotEmpty();
    }

}
