package com.example.demo;

import static com.jayway.restassured.RestAssured.get;
import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Value;





@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class SpringStarterApplicationTests {
	
	    @Value("${local.server.port}")
	    int port;
	
	    @Before
	    public void setup() {
	        RestAssured.baseURI = "http://localhost";
	        RestAssured.port = port;
	    }

	    @Test
	    public void addEmployeeTest() {
	        when().get("/addEmployee").
	                then().statusCode(200).body("id",equalTo(1));
	    }
	    
	    @Test
	    public void greetingsTest(){
	    	given().contentType("application/json").
			when().get("/greetings/Rajan").
			then().body("greetings", equalTo("Welcome Rajan"));
	    }

}
