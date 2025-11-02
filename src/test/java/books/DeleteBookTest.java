package books;

import base.BaseApiTest;
import data.CommonDataProvider;
import io.qameta.allure.Feature;
import io.restassured.common.mapper.TypeRef;
import com.example.model.Book;
import org.testng.annotations.Test;
import com.example.util.DataFactory;

import java.util.List;

import static constant.Groups.*;
import static data.CommonDataProvider.BAD_REQUEST_IDS;
import static data.CommonDataProvider.NOT_FOUND_IDS;
import static org.assertj.core.api.Assertions.assertThat;
import static com.example.spec.ResponseSpecifications.*;

@Feature(BOOKS)
public class DeleteBookTest extends BaseApiTest {

    @Test(groups = {BOOKS, SMOKE})
    public void testDeleteBook() {
        Integer newBookId = 201;
        booksClient.create(DataFactory.randomBook(newBookId)).spec(OK_200);
        booksClient.delete(newBookId).statusCode(200);
        List<Book> bookList = booksClient.list().spec(OK_200)
               .extract().as(new TypeRef<>() {});
        assertThat(bookList.stream().noneMatch(book -> newBookId.equals(book.id())))
                .as("Deleted book should not be present in list").isTrue();
    }

    @Test(groups = {BOOKS, NEGATIVE}, dataProvider = NOT_FOUND_IDS, dataProviderClass = CommonDataProvider.class)
    public void testDeleteBookByNonExistentID(Integer id) {
        booksClient.getById(id).spec(NOT_FOUND_404);
    }

    @Test(groups = {BOOKS, NEGATIVE}, dataProvider = BAD_REQUEST_IDS, dataProviderClass = CommonDataProvider.class)
    public void testDeleteBookByInvalidID(Object id) {
        booksClient.getById(id).spec(BAD_REQUEST_400);
    }

}
