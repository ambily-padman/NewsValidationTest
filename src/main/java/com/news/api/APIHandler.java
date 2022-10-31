package com.news.api;

import com.news.utils.utilities.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIHandler {

    public Response postWithJsonPayLoad(String endpoint, String json) {
        var s = RestAssured
                .given()
                .baseUri(PropertyReader.getInstance().getProperty("api.base.url") + endpoint)
                .header("Authorization", "Token " + PropertyReader.getInstance().getProperty("api.token"))
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post();
        return s;
    }
}
