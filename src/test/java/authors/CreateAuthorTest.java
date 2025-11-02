package authors;

import base.BaseApiTest;
import io.qameta.allure.Feature;
import com.example.model.Author;
import org.testng.annotations.Test;
import com.example.util.DataFactory;

import static constant.Groups.AUTHORS;
import static constant.Groups.SMOKE;
import static org.assertj.core.api.Assertions.assertThat;
import static com.example.spec.ResponseSpecifications.OK_200;

@Feature(AUTHORS)
public class CreateAuthorTest extends BaseApiTest {

    @Test(groups = {AUTHORS, SMOKE})
    public void testCreateAuthor() {
        Author newAuthor = DataFactory.randomAuthor(0, 1);
        Author createdAuthor = authorsClient.create(newAuthor)
                .spec(OK_200).extract().as(Author.class);
        assertThat(createdAuthor).as("New author should be created with correct fields")
                .isEqualTo(newAuthor);
    }
}
