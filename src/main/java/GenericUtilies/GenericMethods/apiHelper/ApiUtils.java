package GenericUtilies.GenericMethods.apiHelper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.*;

/**
 * Utility class for REST Assured API operations (GET, POST, PUT, DELETE).
 */
public class ApiUtils {
    /**
     * Performs a GET request.
     *
     * @param baseUrl     Base URL of the API (e.g., "https://api.example.com").
     * @param endpoint    API endpoint (e.g., "/users").
     * @param queryParams Query parameters as a Map (e.g., {"id": "1"}).
     * @param headers     Headers as a Map (e.g., {"Authorization": "Bearer token"}).
     * @return Response object.
     */
    public static Response getRequest(String baseUrl, String endpoint,
                                      Map<String, String> queryParams,
                                      Map<String, String> headers) {
        RequestSpecification request = given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON);

        // Add query parameters if provided
        if (queryParams != null && !queryParams.isEmpty()) {
            request.queryParams(queryParams);
        }

        // Add headers if provided
        if (headers != null && !headers.isEmpty()) {
            request.headers(headers);
        }

        try {
            return request
                    .when()
                    .get(endpoint)
                    .then()
                    .extract()
                    .response();
        } catch (Exception e) {
            throw new RuntimeException("GET request failed for endpoint: " + endpoint, e);
        }
    }

    /**
     * Performs a POST request.
     *
     * @param baseUrl  Base URL of the API.
     * @param endpoint API endpoint (e.g., "/users").
     * @param payload  JSON payload as a String or Object (e.g., "{\"name\": \"John\"}").
     * @param headers  Headers as a Map.
     * @return Response object.
     */
    public static Response postRequest(String baseUrl, String endpoint,
                                       Object payload,
                                       Map<String, String> headers) {
        RequestSpecification request = given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON);

        // Add headers if provided
        if (headers != null && !headers.isEmpty()) {
            request.headers(headers);
        }

        // Add payload if provided
        if (payload != null) {
            request.body(payload);
        }

        try {
            return request
                    .when()
                    .post(endpoint)
                    .then()
                    .extract()
                    .response();
        } catch (Exception e) {
            throw new RuntimeException("POST request failed for endpoint: " + endpoint, e);
        }
    }

    /**
     * Performs a PUT request.
     *
     * @param baseUrl  Base URL of the API.
     * @param endpoint API endpoint (e.g., "/users/1").
     * @param payload  JSON payload as a String or Object.
     * @param headers  Headers as a Map.
     * @return Response object.
     */
    public static Response putRequest(String baseUrl, String endpoint,
                                      Object payload,
                                      Map<String, String> headers) {
        RequestSpecification request = given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON);

        // Add headers if provided
        if (headers != null && !headers.isEmpty()) {
            request.headers(headers);
        }

        // Add payload if provided
        if (payload != null) {
            request.body(payload);
        }

        try {
            return request
                    .when()
                    .put(endpoint)
                    .then()
                    .extract()
                    .response();
        } catch (Exception e) {
            throw new RuntimeException("PUT request failed for endpoint: " + endpoint, e);
        }
    }

    /**
     * Performs a DELETE request.
     *
     * @param baseUrl  Base URL of the API.
     * @param endpoint API endpoint (e.g., "/users/1").
     * @param headers  Headers as a Map.
     * @return Response object.
     */
    public static Response deleteRequest(String baseUrl, String endpoint,
                                         Map<String, String> headers) {
        RequestSpecification request = given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON);

        // Add headers if provided
        if (headers != null && !headers.isEmpty()) {
            request.headers(headers);
        }

        try {
            return request
                    .when()
                    .delete(endpoint)
                    .then()
                    .extract()
                    .response();
        } catch (Exception e) {
            throw new RuntimeException("DELETE request failed for endpoint: " + endpoint, e);
        }
    }

    /**
     * Performs a GET request with Bearer token.
     *
     * @param baseUrl     Base URL of the API (e.g., "https://api.example.com").
     * @param endpoint    API endpoint (e.g., "/users").
     * @param queryParams Query parameters as a Map (e.g., {"id": "1"}).
     * @param headers     Additional headers as a Map (optional).
     * @param bearerToken Bearer token for authentication (e.g., "your_token_here").
     * @return Response object.
     */
    public static Response getRequest(String baseUrl, String endpoint,
                                      Map<String, String> queryParams,
                                      Map<String, String> headers,
                                      String bearerToken) {
        RequestSpecification request = given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON);

        // Add Bearer token to Authorization header
        if (bearerToken != null && !bearerToken.isEmpty()) {
            request.header("Authorization", "Bearer " + bearerToken);
        }

        // Add additional headers if provided
        if (headers != null && !headers.isEmpty()) {
            request.headers(headers);
        }

        // Add query parameters if provided
        if (queryParams != null && !queryParams.isEmpty()) {
            request.queryParams(queryParams);
        }

        try {
            return request
                    .when()
                    .get(endpoint)
                    .then()
                    .extract()
                    .response();
        } catch (Exception e) {
            throw new RuntimeException("GET request failed for endpoint: " + endpoint, e);
        }
    }

    /**
     * Performs a POST request with Bearer token.
     *
     * @param baseUrl     Base URL of the API.
     * @param endpoint    API endpoint (e.g., "/users").
     * @param payload     JSON payload as a String or Object (e.g., "{\"name\": \"John\"}").
     * @param headers     Additional headers as a Map (optional).
     * @param bearerToken Bearer token for authentication.
     * @return Response object.
     */
    public static Response postRequest(String baseUrl, String endpoint,
                                       Object payload,
                                       Map<String, String> headers,
                                       String bearerToken) {
        RequestSpecification request = given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON);

        // Add Bearer token to Authorization header
        if (bearerToken != null && !bearerToken.isEmpty()) {
            request.header("Authorization", "Bearer " + bearerToken);
        }

        // Add additional headers if provided
        if (headers != null && !headers.isEmpty()) {
            request.headers(headers);
        }

        // Add payload if provided
        if (payload != null) {
            request.body(payload);
        }

        try {
            return request
                    .when()
                    .post(endpoint)
                    .then()
                    .extract()
                    .response();
        } catch (Exception e) {
            throw new RuntimeException("POST request failed for endpoint: " + endpoint, e);
        }
    }

    /**
     * Performs a PUT request with Bearer token.
     *
     * @param baseUrl     Base URL of the API.
     * @param endpoint    API endpoint (e.g., "/users/1").
     * @param payload     JSON payload as a String or Object.
     * @param headers     Additional headers as a Map (optional).
     * @param bearerToken Bearer token for authentication.
     * @return Response object.
     */
    public static Response putRequest(String baseUrl, String endpoint,
                                      Object payload,
                                      Map<String, String> headers,
                                      String bearerToken) {
        RequestSpecification request = given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON);

        // Add Bearer token to Authorization header
        if (bearerToken != null && !bearerToken.isEmpty()) {
            request.header("Authorization", "Bearer " + bearerToken);
        }

        // Add additional headers if provided
        if (headers != null && !headers.isEmpty()) {
            request.headers(headers);
        }

        // Add payload if provided
        if (payload != null) {
            request.body(payload);
        }

        try {
            return request
                    .when()
                    .put(endpoint)
                    .then()
                    .extract()
                    .response();
        } catch (Exception e) {
            throw new RuntimeException("PUT request failed for endpoint: " + endpoint, e);
        }
    }

    /**
     * Performs a DELETE request with Bearer token.
     *
     * @param baseUrl     Base URL of the API.
     * @param endpoint    API endpoint (e.g., "/users/1").
     * @param headers     Additional headers as a Map (optional).
     * @param bearerToken Bearer token for authentication.
     * @return Response object.
     */
    public static Response deleteRequest(String baseUrl, String endpoint,
                                         Map<String, String> headers,
                                         String bearerToken) {
        RequestSpecification request = given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON);

        // Add Bearer token to Authorization header
        if (bearerToken != null && !bearerToken.isEmpty()) {
            request.header("Authorization", "Bearer " + bearerToken);
        }

        // Add additional headers if provided
        if (headers != null && !headers.isEmpty()) {
            request.headers(headers);
        }

        try {
            return request
                    .when()
                    .delete(endpoint)
                    .then()
                    .extract()
                    .response();
        } catch (Exception e) {
            throw new RuntimeException("DELETE request failed for endpoint: " + endpoint, e);
        }
    }


}
