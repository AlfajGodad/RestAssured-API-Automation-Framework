package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import io.restassured.response.Response;

public class NegativeUserTests {

    @Test
    public void testGetNonExistingUser() {

        Response response =
                UserEndpoints.readUser("invalid_user_999999");

        Assert.assertEquals(response.getStatusCode(), 404);

        Assert.assertEquals(
                response.jsonPath().getString("message"),
                "User not found");
    }


    @Test
    public void testDeleteNonExistingUser() {

        Response response =
                UserEndpoints.deleteUser("invalid_user_999999");

        Assert.assertEquals(response.getStatusCode(), 404);
    }


    @Test
    public void testGetUserWithInvalidUsername() {

        Response response =
                UserEndpoints.readUser("%%%invalid%%%");

        Assert.assertEquals(response.getStatusCode(), 404);
    }
}