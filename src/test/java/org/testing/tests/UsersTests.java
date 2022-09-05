package org.testing.tests;

import io.restassured.response.Response;
import org.testing.sources.api.UserApi;
import org.testing.sources.entity.Endpoint;
import org.testing.sources.entity.User;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;


import java.util.*;

import static io.restassured.RestAssured.given;
import static org.testing.sources.support.Config.apiProperties;

public class UsersTests extends BaseTest {
    private final List<User> createdUsers = new ArrayList<>();
    private final UserApi userApi = new UserApi(apiProperties.getBaseUrl() + Endpoint.USERS.getPath(),
            apiProperties.getToken());
    @Test
    public void checkNotExistingUrl() {
        String invalidEndpoint = "invalidEndpoint";
        Response response = given()
                .when()
                .get(apiProperties.getBaseUrl() + invalidEndpoint);
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test
    public void checkLoggedOutState() {
        Response response = userApi.logOut().getAll();
        Assert.assertEquals(response.getStatusCode(), 401);
    }

    @Test
    public void checkLoggedInState() {
        Assert.assertEquals(userApi.getAll().getStatusCode(), 200);
    }


    @Test
    public void checkUserCreation() {
        User user = User.generateUser();
        Response createUserResponse = userApi.create();
        Assert.assertEquals(createUserResponse.getStatusCode(), 201);
        Response userResponse = userApi.init(user).get();
        Assert.assertEquals(userResponse.statusCode(), 200);
        createdUsers.add(user);
    }
    @Test
    public void checkUserUpdate() {
        User user = User.generateUser();
        Response updateResponse = userApi.update(user);
        Assert.assertEquals(updateResponse.statusCode(), 200);
        Response userResponse = userApi.init(user).get();
        Assert.assertEquals(userResponse.statusCode(), 200);
        createdUsers.add(user);
    }

    @AfterClass
    public void deleteCreatedUsers() {
        createdUsers.forEach(user-> userApi.init(user).delete());

    }
}