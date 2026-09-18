package api.endpoints;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {

    private static final Logger logger =
            LogManager.getLogger(UserEndpoints.class);


    // ==================== CREATE USER ====================

    public static Response createUser(User payload) {

        logger.info(
                "Sending POST request to create user: {}",
                payload.getUsername());

        Response response =

                given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(payload)

                .when()
                    .post(Routes.post_url)

                .then()
                    .log().all()
                    .extract().response();

        logger.info(
                "POST completed for user: {} | Status Code: {}",
                payload.getUsername(),
                response.getStatusCode());

        return response;
    }


    // ==================== GET USER ====================

    public static Response readUser(String username) {

        logger.info(
                "Sending GET request for user: {}",
                username);

        Response response =

                given()
                    .pathParam("username", username)
                    .accept(ContentType.JSON)

                .when()
                    .get(Routes.get_url)

                .then()
                    .log().all()
                    .extract().response();

        logger.info(
                "GET completed for user: {} | Status Code: {}",
                username,
                response.getStatusCode());

        return response;
    }


    // ==================== UPDATE USER ====================

    public static Response updateUser(
            String username,
            User payload) {

        logger.info(
                "Sending PUT request to update user: {}",
                username);

        Response response =

                given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .pathParam("username", username)
                    .body(payload)

                .when()
                    .put(Routes.update_url)

                .then()
                    .log().all()
                    .extract().response();

        logger.info(
                "PUT completed for user: {} | Status Code: {}",
                username,
                response.getStatusCode());

        return response;
    }


    // ==================== DELETE USER ====================

    public static Response deleteUser(String username) {

        logger.info(
                "Sending DELETE request for user: {}",
                username);

        Response response =

                given()
                    .pathParam("username", username)
                    .accept(ContentType.JSON)

                .when()
                    .delete(Routes.delete_url)

                .then()
                    .log().all()
                    .extract().response();

        logger.info(
                "DELETE completed for user: {} | Status Code: {}",
                username,
                response.getStatusCode());

        return response;
    }
}