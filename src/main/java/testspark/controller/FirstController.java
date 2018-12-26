package testspark.controller;

import spark.Request;
import spark.Response;
import spark.Route;
import testspark.testspark.application.command.NewPostPayload;

import java.util.ArrayList;
import java.util.Arrays;

public class FirstController {

    public static String main(Request req, Response res) {
        return "hello from sparkjava.com";
    }

    public Route closure = (Request request, Response response) -> {
        response.status(200);
        NewPostPayload newPostPayload = new NewPostPayload();
        newPostPayload.setTitle("A new title");
        newPostPayload.setContent("With a new content");
        newPostPayload.setCategories(Arrays.asList("Test 1", "Test 2", "Test 3"));

        return newPostPayload;
    };
}
