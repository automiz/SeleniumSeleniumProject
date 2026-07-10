package com.automation.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BankingApiTest {

    @BeforeClass
    public void setupApiConfig() {
        // Updated stable Base URI for ParaBank's core banking REST services
        RestAssured.baseURI = "https://parabank.parasoft.com/parabank/services/bank";
    }

    @Test
    public void testGetAccountBalanceBackend() {
        System.out.println("Executing Core Banking Backend API Validation...");

        // Querying customer ID 12212 to fetch their active checking/savings accounts
        Response response = given()
            .header("Accept", "application/json")
        .when()
            .get("/customers/12212/accounts")
        .then()
            .extract().response();

        // Print the real bank account balance array back to CMD
        System.out.println("API Ledger Payload Response:\n" + response.getBody().asPrettyString());

        // Enterprise Checkpoints
        Assert.assertEquals(response.getStatusCode(), 200, "Backend service connection failed!");
        Assert.assertTrue(response.asString().contains("balance"), "Account payload missing balance arrays!");
    }
}