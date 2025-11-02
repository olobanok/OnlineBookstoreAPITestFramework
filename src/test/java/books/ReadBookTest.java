package books;

import base.BaseApiTest;
import data.CommonDataProvider;
import io.qameta.allure.Feature;
import io.restassured.common.mapper.TypeRef;
import com.example.model.Book;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

import static data.CommonDataProvider.BAD_REQUEST_IDS;
import static data.CommonDataProvider.NOT_FOUND_IDS;
import static org.assertj.core.api.Assertions.assertThat;
import static com.example.spec.ResponseSpecifications.*;
import static constant.Groups.*;

@Feature(BOOKS)
public class ReadBookTest extends BaseApiTest {

    private List<Book> bookList;


    @Test(groups = {BOOKS, SMOKE})
    public void testGetBookList() {
        bookList = booksClient.list()
                .spec(OK_200)
                .extract()
                .as(new TypeRef<>() {});
        assertThat(bookList).as("Book list should not be empty").isNotEmpty();
    }

    @Test(groups = {BOOKS, SMOKE}, dependsOnMethods = "testGetBookList")
    public void testGetBookByID() {
        Book randomBook = bookList.get(RANDOM.nextInt(bookList.size()));
        Book retrievedBook = booksClient.getById(randomBook.id())
                .spec(OK_200)
                .extract()
                .as(Book.class);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(retrievedBook.id()).as("Book ID").isEqualTo(randomBook.id());
            softly.assertThat(retrievedBook.title()).as("Title").isEqualTo(randomBook.title());
        });
    }

    @Test(groups = {BOOKS, NEGATIVE}, dataProvider = NOT_FOUND_IDS, dataProviderClass = CommonDataProvider.class)
    public void testGetBookByNonExistentID(Integer id) {
        booksClient.getById(id).spec(NOT_FOUND_404);
    }

    @Test(groups = {BOOKS, NEGATIVE}, dataProvider = BAD_REQUEST_IDS, dataProviderClass = CommonDataProvider.class)
    public void testGetBookByInvalidID(Object id) {
        booksClient.getById(id).spec(BAD_REQUEST_400);
    }

}
