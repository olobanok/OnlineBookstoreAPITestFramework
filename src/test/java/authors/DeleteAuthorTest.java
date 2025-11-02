package authors;

import base.BaseApiTest;
import io.qameta.allure.Feature;
import io.restassured.common.mapper.TypeRef;
import com.example.model.Author;
import org.testng.annotations.Test;
import com.example.util.DataFactory;

import java.util.List;

import static constant.Groups.*;
import static org.assertj.core.api.Assertions.assertThat;
import static com.example.spec.ResponseSpecifications.OK_200;

@Feature(AUTHORS)
public class DeleteAuthorTest extends BaseApiTest {

    @Test(groups = {AUTHORS, SMOKE})
    public void testDeleteAuthor() {
        Integer newAuthorId = 9999;
        authorsClient.create(DataFactory.randomAuthor(newAuthorId, 1)).spec(OK_200);
        authorsClient.delete(newAuthorId).statusCode(200);
        List<Author> authorList = authorsClient.list().spec(OK_200)
                .extract().as(new TypeRef<>() {});
        assertThat(authorList.stream().noneMatch(author -> newAuthorId.equals(author.id())))
                .as("Deleted author should not be present in list").isTrue();
    }
}
