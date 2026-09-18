package api.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import api.endpoints.UserEndpoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests {

    User userPayload;

    @BeforeClass
    public void setupData() {

        userPayload = new User();

        userPayload.setId(101);
        userPayload.setUsername("alfaj101");
        userPayload.setFirstName("Alfaj");
        userPayload.setLastName("Godad");
        userPayload.setEmail("alfaj101@example.com");
        userPayload.setPassword("Test@123");
        userPayload.setPhone("9876543210");
        userPayload.setUserStatus(1);

        System.out.println("Setup executed");
    }

    @Test(priority = 1)
    public void testCreateUser() {

        System.out.println("Payload = " + userPayload);

        Response response = UserEndpoints.createUser(userPayload);

        AssertJUnit.assertEquals(response.getStatusCode(), 200);
    }
    
    
    @Test(priority = 2)
    public void testGetUser() {

        Response response =
                UserEndpoints.readUser(userPayload.getUsername());

        AssertJUnit.assertEquals(response.getStatusCode(), 200);

        AssertJUnit.assertEquals(
                response.jsonPath().getString("username"),
                userPayload.getUsername());

        AssertJUnit.assertEquals(
                response.jsonPath().getString("firstName"),
                userPayload.getFirstName());

        AssertJUnit.assertEquals(
                response.jsonPath().getString("lastName"),
                userPayload.getLastName());

        AssertJUnit.assertEquals(
                response.jsonPath().getString("email"),
                userPayload.getEmail());
    }
    
    
    
    @Test(priority = 3)
    public void testUpdateUser() {

        // Change some existing user details
        userPayload.setFirstName("Alfaj Mustak");
        userPayload.setEmail("alfaj.updated@example.com");
        userPayload.setPhone("9999999999");

        Response response =
                UserEndpoints.updateUser(
                        userPayload.getUsername(),
                        userPayload);

        AssertJUnit.assertEquals(response.getStatusCode(), 200);
    }
    
    
    @Test(priority = 4)
    public void testGetUpdatedUser() {

        Response response =
                UserEndpoints.readUser(userPayload.getUsername());

        AssertJUnit.assertEquals(response.getStatusCode(), 200);

        AssertJUnit.assertEquals(
                response.jsonPath().getString("firstName"),
                "Alfaj Mustak");

        AssertJUnit.assertEquals(
                response.jsonPath().getString("email"),
                "alfaj.updated@example.com");

        AssertJUnit.assertEquals(
                response.jsonPath().getString("phone"),
                "9999999999");
    }
    
    
    @Test(priority = 5)
    public void testDeleteUser() {

        Response response =
                UserEndpoints.deleteUser(userPayload.getUsername());

        AssertJUnit.assertEquals(response.getStatusCode(), 200);
    }
    
    
    @Test(priority = 6)
    public void testGetDeletedUser() {

        Response response =
                UserEndpoints.readUser(userPayload.getUsername());

        AssertJUnit.assertEquals(response.getStatusCode(), 404);
    }
}