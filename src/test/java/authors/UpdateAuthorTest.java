package authors;

import base.BaseApiTest;
import io.qameta.allure.Feature;
import io.restassured.common.mapper.TypeRef;
import com.example.model.Author;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import com.example.util.DataFactory;

import java.util.List;

import static constant.Groups.AUTHORS;
import static constant.Groups.SMOKE;
import static com.example.spec.ResponseSpecifications.OK_200;

@Feature(AUTHORS)
public class UpdateAuthorTest extends BaseApiTest {

    @Test(groups = {AUTHORS, SMOKE})
    public void testUpdateAuthor() {
        List<Author> authorList = authorsClient.list().extract().as(new TypeRef<>() {});
        Author authorForUpdate = authorList.get(RANDOM.nextInt(authorList.size()));
        Author newDataAuthor = DataFactory.randomAuthor(authorForUpdate.id(), 1);
        Author updatedAuthor = authorsClient.update(authorForUpdate.id(), newDataAuthor)
                .spec(OK_200)
                .extract()
                .as(Author.class);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(updatedAuthor.id()).as("Author ID").isEqualTo(authorForUpdate.id());
            softly.assertThat(updatedAuthor.firstName()).as("First name").isEqualTo(newDataAuthor.firstName());
            softly.assertThat(updatedAuthor.lastName()).as("Last name").isEqualTo(newDataAuthor.lastName());
        });
    }

}
