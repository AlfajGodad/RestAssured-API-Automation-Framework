package api.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import api.endpoints.UserEndpoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {

    @Test(
        dataProvider = "UserData",
        dataProviderClass = DataProviders.class
    )
    public void testUserLifecycle(
            String id,
            String username,
            String firstName,
            String lastName,
            String email,
            String password,
            String phone,
            String userStatus) {

        // ---------- Create Payload ----------

        User payload = new User();

        payload.setId(Integer.parseInt(id));
        payload.setUsername(username);
        payload.setFirstName(firstName);
        payload.setLastName(lastName);
        payload.setEmail(email);
        payload.setPassword(password);
        payload.setPhone(phone);
        payload.setUserStatus(Integer.parseInt(userStatus));


        // ---------- POST : Create User ----------

        Response createResponse =
                UserEndpoints.createUser(payload);

        AssertJUnit.assertEquals(
                createResponse.getStatusCode(),
                200);


        // ---------- GET : Verify User ----------

        Response getResponse =
                UserEndpoints.readUser(username);

        AssertJUnit.assertEquals(
                getResponse.getStatusCode(),
                200);

        AssertJUnit.assertEquals(
                getResponse.jsonPath().getString("username"),
                username);

        AssertJUnit.assertEquals(
                getResponse.jsonPath().getString("firstName"),
                firstName);

        AssertJUnit.assertEquals(
                getResponse.jsonPath().getString("lastName"),
                lastName);

        AssertJUnit.assertEquals(
                getResponse.jsonPath().getString("email"),
                email);

        AssertJUnit.assertEquals(
                getResponse.jsonPath().getString("phone"),
                phone);


        // ---------- DELETE : Delete User ----------

        Response deleteResponse =
                UserEndpoints.deleteUser(username);

        AssertJUnit.assertEquals(
                deleteResponse.getStatusCode(),
                200);


        // ---------- GET : Verify Deletion ----------

        Response deletedUserResponse =
                UserEndpoints.readUser(username);

        AssertJUnit.assertEquals(
                deletedUserResponse.getStatusCode(),
                404);
    }
}