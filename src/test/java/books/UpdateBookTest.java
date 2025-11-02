package books;

import base.BaseApiTest;
import data.CommonDataProvider;
import io.qameta.allure.Feature;
import io.restassured.common.mapper.TypeRef;
import com.example.model.Book;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import com.example.util.DataFactory;

import java.util.List;

import static constant.Groups.*;
import static data.CommonDataProvider.BAD_REQUEST_IDS;
import static data.CommonDataProvider.NOT_FOUND_IDS;
import static com.example.spec.ResponseSpecifications.*;

@Feature(BOOKS)
public class UpdateBookTest extends BaseApiTest {

    @Test(groups = {BOOKS, SMOKE})
    public void testUpdateBook() {
        List<Book> bookList = booksClient.list().extract().as(new TypeRef<>() {});
        Book bookForUpdate = bookList.get(RANDOM.nextInt(bookList.size()));
        Book newDataBook = DataFactory.randomBook(bookForUpdate.id());
        Book updatedBook = booksClient.update(bookForUpdate.id(), newDataBook)
                .spec(OK_200)
                .extract()
                .as(Book.class);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(updatedBook.id()).as("Book ID").isEqualTo(bookForUpdate.id());
            softly.assertThat(updatedBook.title()).as("Title").isEqualTo(newDataBook.title());
            softly.assertThat(updatedBook.description()).as("Description").isEqualTo(newDataBook.description());
            softly.assertThat(updatedBook.pageCount()).as("Page count").isEqualTo(newDataBook.pageCount());
            softly.assertThat(updatedBook.excerpt()).as("Excerpt").isEqualTo(newDataBook.excerpt());
            softly.assertThat(updatedBook.publishDate()).as("Publish date").isEqualTo(newDataBook.publishDate());
        });
    }

    @Test(groups = {BOOKS, NEGATIVE})
    public void testUpdateBookWithEmptyRequestBody() {
        booksClient.update(1, StringUtils.EMPTY).spec(BAD_REQUEST_EMPTY_BODY_400);
    }

    @Test(groups = {BOOKS, NEGATIVE}, dataProvider = NOT_FOUND_IDS, dataProviderClass = CommonDataProvider.class)
    public void testUpdateBookWithNonExistentID(Integer id) {
        Book book = DataFactory.randomBook(0);
        booksClient.update(id, book).spec(NOT_FOUND_404);
    }

    @Test(groups = {BOOKS, NEGATIVE}, dataProvider = BAD_REQUEST_IDS, dataProviderClass = CommonDataProvider.class)
    public void testUpdateBookWithInvalidID(Object id) {
        Book book = DataFactory.randomBook(0);
        booksClient.update(id, book).spec(BAD_REQUEST_400);
    }


}
