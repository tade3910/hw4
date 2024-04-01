import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.xml.bind.annotation.XmlRootElement;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class JsonplaceholderRestAPITest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testAlbumsEndpoint() {
        given()
                .when()
                .get("/albums")
                .then()
                .assertThat()
                .body("title", hasItem("omnis laborum odio"));
    }

    @Test
    public void testCommentsEndpoint() {
        given()
                .when()
                .get("/comments")
                .then()
                .assertThat()
                .body("size()", greaterThanOrEqualTo(200));
    }

    @Test
    public void testUsersEndpoint() {
        given()
                .when()
                .get("/users")
                .then()
                .assertThat()
                .body("findAll { it.name == 'Ervin Howell' && it.username == 'Antonette' && it.address.zipcode == '90566-7771' }", hasSize(1));
    }

    @Test
    public void testCommentsFromBizEmails() {
        given()
                .when()
                .get("/comments")
                .then()
                .assertThat()
                .body("email", hasItems(endsWith(".biz")));
    }

    @XmlRootElement
    static class JSONObject {
        private int userId;
        private int id;
        private String title;
        private String body;

        private void setUserId(int userId) {
            this.userId = userId;
        }

        private void setId(int id) {
            this.id = id;
        }

        private void setTitle(String title) {
            this.title = title;
        }

        private void setBody(String body) {
            this.body = body;
        }
    }

    @Test
    public void testCreatePost() {
        JSONObject requestBody = new JSONObject();
        requestBody.setUserId(11);
        requestBody.setId(101);
        requestBody.setTitle("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        requestBody.setBody("""
                quia et suscipit
                suscipit recusandae consequuntur expedita et cum
                reprehenderit molestiae ut ut quas totam
                nostrum rerum est autem sunt rem eveniet architecto
                """);
        given()
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201); // Assuming that 201 is the status code for successful post creation
    }
}
