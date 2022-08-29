package org.testing.tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testing.sources.entity.User;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;


import java.util.*;

import static io.restassured.RestAssured.given;

public class Users extends Base {
    private final List<User> createdUsers = new ArrayList<>();

    @Test
    public void checkNotExistingUrl() {
        Response response = given()
                .when()
                .get(apiProperties.getEndPointUrl("users"));
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test
    public void checkLoggedOutState() {
        Response response = given()
                .when()
                .get(apiProperties.getBaseUrl() + "users");
        Assert.assertEquals(response.getStatusCode(), 401);
    }

    @Test
    public void checkLoggedInOutState() {
        Response response = given().accept(ContentType.JSON).auth().oauth2(apiProperties.getToken())
                .when()
                .get(apiProperties.getBaseUrl() + "users");
        System.out.println(response.body().prettyPrint());
        Assert.assertEquals(response.getStatusCode(), 200);
    }


    @Test
    public void checkUserCreation() {
        User user = User.generateUser();
        RequestSpecification requestSpecification = given().accept(ContentType.JSON).auth().oauth2(apiProperties.getToken());
        Response createUserResponse = requestSpecification .body(user.asMap())
                .post(apiProperties.getBaseUrl() + "users");
        Assert.assertEquals(createUserResponse.getStatusCode(), 201);

        Response  response =  requestSpecification
                .when()
                .get(apiProperties.getBaseUrl() + "users");
        JsonPath jsonPathEvaluator = response.body().jsonPath();
        List<String> emails = jsonPathEvaluator.get("email");
        emails.forEach(email -> Assert.assertEquals(email, user.getEmail()));
        createdUsers.add(user);
    }

    @AfterClass
    public void deleteCreatedUsers() {
        //delete created users
    }
}