package org.testing.sources.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testing.sources.entity.User;

import static io.restassured.RestAssured.given;

public class UserApi {
    private User user;
    private final String userURL;
    private  RequestSpecification requestSpecification;

    public UserApi(String userURL, String token){
        this.userURL = userURL;
        requestSpecification = given().accept(ContentType.JSON).auth().oauth2(token);
    }

    public UserApi init(User user){
        this.user = user;
        return this;
    }

    public UserApi logOut(){
        requestSpecification = given().accept(ContentType.JSON);
        return this;
    }


    public Response create() {
        return requestSpecification .body(user.asMap())
                .post(userURL);
    }

    public Response delete() {
        return requestSpecification
                .delete(userURL + user.getId());
    }

    public Response update(User user) {

        return requestSpecification
                .body(user.asMap())
                .put( userURL + this.user.getId());
    }

    public Response getAll() {
        return  requestSpecification
                .when()
                .get(userURL);

    }

    public Response get() {
        return  requestSpecification
                .when()
                .get(userURL +  this.user.getId());

    }
}
