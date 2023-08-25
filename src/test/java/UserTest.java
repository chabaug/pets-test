import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserTest extends TestBase {

    @Test(dataProvider = "postUser")
    public void postUser(int id, String username, String firstName, String lastName,
                         String email, String password, String phone, int userStatus) {

        String requestBody = "{"
                + "\"id\":" + id + ","
                + "\"username\": \"" + username + "\","
                + "\"firstName\": \"" + firstName + "\","
                + "\"lastName\": \"" + lastName + "\","
                + "\"email\": \"" + email + "\","
                + "\"password\": \"" + password + "\","
                + "\"phone\": \"" + phone + "\","
                + "\"userStatus\":" + userStatus
                + "}";

        given()
                .baseUri(baseUrl)
                .body(requestBody)
                .contentType("application/json")
                .when()
                .post("/user")
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("username", equalTo(username))
                .body("firstName", equalTo(firstName))
                .body("lastName", equalTo(lastName))
                .body("email", equalTo(email))
                .body("password", equalTo(password))
                .body("phone", equalTo(phone))
                .body("userStatus", equalTo(userStatus))
        ;
    }

    @DataProvider(name = "postUser")
    public Object[][] postUserData(){
        return new Object[][] {{1, "AugustoNasso", "Augusto", "Nasso",
                "augustonasso@mail.com", "admin1234", "115388975", 1}};
    }

    @Test
    public void getUserLogin() {
        given()
                .baseUri(baseUrl)
                .queryParam("username", "a")
                .queryParam("password", "a")
                .when()
                .get("/user/login")
                .then()
                .statusCode(200)
                .body(containsString("Logged in user session:"));
    }

    @Test
    public void getUserLogout() {
        given()
                .baseUri(baseUrl)
                .when()
                .get("/user/logout")
                .then()
                .statusCode(200)
                .body(containsString("User logged out"));
    }
}