package com.example.demo.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.modal.Employee;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
 
@RestController
public class EmployeeController 
{
	
 	
	 
    @RequestMapping(value = "/addEmployee", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee addEmployees() 
    {
    	
    	Employee em = new Employee(1,"lokesh","malhotra","lokesh@gmail.com");
      
        return em;
    }
    
    @RequestMapping(value = "/greetings/{varX}", method= RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    public String getEmployees(@PathVariable("varX") String varX) 
    {
       JsonObject jsonObject = new JsonObject();
       String greetings = "Welcome " + varX;
       jsonObject.addProperty("greetings", greetings);
       return (new Gson().toJson(jsonObject));
    }
}