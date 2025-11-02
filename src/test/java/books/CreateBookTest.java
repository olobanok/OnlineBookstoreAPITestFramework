package books;

import base.BaseApiTest;
import io.qameta.allure.Feature;
import com.example.model.Book;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import com.example.util.DataFactory;

import static constant.Groups.*;
import static com.example.spec.ResponseSpecifications.BAD_REQUEST_EMPTY_BODY_400;

@Feature(BOOKS)
public class CreateBookTest extends BaseApiTest {

    @Test(groups = {BOOKS, SMOKE})
    public void testCreateNewBook() {
        Book newBook = DataFactory.randomBook(0);
        Book createdBook = booksClient.create(newBook)
                .statusCode(200)
                .extract()
                .as(Book.class);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(createdBook.title()).as("Title").isEqualTo(newBook.title());
            softly.assertThat(createdBook.description()).as("Description").isEqualTo(newBook.description());
            softly.assertThat(createdBook.pageCount()).as("Page count").isEqualTo(newBook.pageCount());
            softly.assertThat(createdBook.excerpt()).as("Excerpt").isEqualTo(newBook.excerpt());
        });
    }

    @Test(groups = {BOOKS, NEGATIVE})
    public void testCreateBookWithEmptyRequestBody() {
        booksClient.create(StringUtils.EMPTY).spec(BAD_REQUEST_EMPTY_BODY_400);
    }

}
