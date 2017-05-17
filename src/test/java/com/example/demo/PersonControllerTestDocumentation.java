package com.example.demo;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.Repository.PersonRepository;
import com.example.demo.modal.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@AutoConfigureMockMvc
public class PersonControllerTestDocumentation {
    
	@Autowired
    private WebApplicationContext context;
	
	@Autowired
    private PersonRepository personRepository;
	
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;
    
    @Value("${local.server.port}")
    int port;
    
    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    public void listPeople() throws Exception {
        createSamplePerson("George", "King");
        createSamplePerson("Mary", "Queen");
        RestDocumentationResultHandler document = documentPrettyPrintReqResp("insertUser");
        

       document.document(
        		
                responseFields(
                        fieldWithPath("[].id").description("The persons' ID"),
                        fieldWithPath("[].firstName").description("The persons' first name"),
                        fieldWithPath("[].lastName").description("The persons' last name")
                )
        );

        this.mockMvc.perform(
                get("/people").accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(document);
    }

    private Person createSamplePerson(String firstName, String lastName) {
        return personRepository.save(new Person(firstName, lastName));
    }
    private RestDocumentationResultHandler documentPrettyPrintReqResp(String useCase) {
        return document(useCase,
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()));
    }

}