package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.example.demo.modal.Employee;
 
@RestController
public class EmployeeController 
{
	
    @RequestMapping(value = "/addEmployee", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee addEmployees() 
    {
    	/*List<Employee> employeesList = new ArrayList<Employee>();
        */
    	Employee em = new Employee(1,"lokesh","malhotra","lokesh@gmail.com");
       // Gson gson = new Gson(employeesList);
        return em;
    }
    
    @RequestMapping(value = "/greetings/{varX}", method= RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    public String getEmployees(@PathVariable("varX") String varX) 
    {
    	

    	String greeting = "Welcome "+varX;
        return greeting;
    }
}