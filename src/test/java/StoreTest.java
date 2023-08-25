import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StoreTest extends TestBase {

    /*NOTE: The values of 'approved' or 'delivered' items in Inventory change randomly*/
    @Test
    public void getInventory() {
        given()
                .baseUri(baseUrl)
                .when()
                .get("/store/inventory")
                .then()
                .statusCode(200)
                .body("approved", equalTo(62))
                .body("delivered", equalTo(50));
    }


    @Test
    public void postOrder() {

        String requestBody = "{"
                + "\"id\": 10009,"
                + "\"petId\": 123459,"
                + "\"quantity\": 5,"
                + "\"status\": \"approved\","
                + "\"complete\": null"
                + "}";

        given()
                .baseUri(baseUrl)
                .body(requestBody)
                .contentType("application/json")
                .when()
                .post("/store/order")
                .then()
                .statusCode(200);
    }


    @Test(dataProvider = "getOrder")
    public void getOrder(String orderId, int statusCode) {
        given()
                .baseUri(baseUrl)
                .when()
                .get("/store/order/" + orderId)
                .then()
                .statusCode(statusCode);
    }

    @DataProvider(name = "getOrder")
    public Object[][] getOrderData(){
        return new Object[][] {{"5", 404}, {"10", 200}};
    }


    @Test(dataProvider = "deleteOrder")
    public void deleteOrder(String orderId, int statusCode) {
        given()
                .baseUri(baseUrl)
                .log().all()
                .when()
                .delete("/store/order/" + orderId)
                .then()
                .statusCode(statusCode);
    }

    @DataProvider(name = "deleteOrder")
    public Object[][] deleteOrderData(){
        return new Object[][] {{"5", 200}, {"-1", 200}, {"0", 200},
                {"-1521#$df5gj", 400}, {"jyhgu", 400}};
    }
}