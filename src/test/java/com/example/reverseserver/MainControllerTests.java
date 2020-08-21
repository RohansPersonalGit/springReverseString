package com.example.reverseserver;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainRestController.class)
@AutoConfigureMockMvc
public class MainControllerTests {


    @Autowired
    private MockMvc mvc;

    private final int STATUS_OK = 200;
    private final int STATUS_BAD = 400;


    @Test
    public void getReverseString()   throws Exception {
        StringBuilder s = new StringBuilder();
        s.append("hello");
        MvcResult res = this.mvc.perform(MockMvcRequestBuilders.get("/reverse/" + s.toString()).accept(MediaType.TEXT_PLAIN)).andReturn();
        assertEquals(res.getResponse().getStatus(),STATUS_OK);
        assertEquals(res.getResponse().getContentAsString(), s.reverse().toString());

    }

    @Test
    public void stringTooLong() throws Exception{
        StringBuilder s = new StringBuilder();
        int i = 0;
        while(i<3000){
            s.append("d");
            i += 1;
        }
        MvcResult res = this.mvc.perform(MockMvcRequestBuilders.get("/reverse/" + s.toString()).accept(MediaType.TEXT_PLAIN)).andReturn();
        assertEquals(res.getResponse().getStatus(),STATUS_BAD);
    }

    @Test
    public void justShortEnough() throws Exception{
        StringBuilder s = new StringBuilder();
        int i = 0;
        while(i<1999){
            if(i%2==0){
                s.append("i");
            }
            else {
                s.append(0);
            }
            i++;
        }
        MvcResult res = this.mvc.perform(MockMvcRequestBuilders.get("/reverse/" + s.toString().trim()).accept(MediaType.TEXT_PLAIN)).andReturn();
        assertEquals(res.getResponse().getStatus(),STATUS_OK);
        assertEquals(res.getResponse().getContentAsString(), s.reverse().toString());
    }

    @Test
    public void alphaNum() throws Exception {
        StringBuilder s = new StringBuilder();
        int i = 0;
        while(i<60){
            if(i%2==0){
                s.append("i");
            }
            else {
                s.append(i);
            }
            i++;
        }
        MvcResult res = this.mvc.perform(MockMvcRequestBuilders.get("/reverse/" + s.toString()).accept(MediaType.TEXT_PLAIN)).andReturn();
        assertEquals(res.getResponse().getStatus(),STATUS_OK);
        assertEquals(res.getResponse().getContentAsString(), s.reverse().toString());
    }

    @Test
    public void someSpaces() throws Exception {
        StringBuilder s = new StringBuilder();
        int i = 0;
        while(i<1999){
            if(i%2==0){
                s.append("i");
            }
            else {
                s.append(" ");
            }
            i++;
        }
        MvcResult res = this.mvc.perform(MockMvcRequestBuilders.get("/reverse/" + s.toString().trim()).accept(MediaType.TEXT_PLAIN)).andReturn();
        assertEquals(res.getResponse().getStatus(),STATUS_OK);
        assertEquals(res.getResponse().getContentAsString(), s.reverse().toString());
    }

    @Test
    public void backToBack() throws Exception{
        int i = 0;
        StringBuilder s = new StringBuilder().append("The Quick Brown Fox Jumped Over the Lazy River");
        while(i<500){
            MvcResult res = this.mvc.perform(MockMvcRequestBuilders.get("/reverse/" + s.toString().trim()).accept(MediaType.TEXT_PLAIN)).andReturn();
            assertEquals(res.getResponse().getStatus(),STATUS_OK);
            assertEquals(res.getResponse().getContentAsString(), s.reverse().toString());
            i+=1;
        }
    }

    @Test
    public void testSpecialChars() throws Exception {
        StringBuilder s = new StringBuilder().append("+-*&!@$$%");
        MvcResult res = this.mvc.perform(MockMvcRequestBuilders.get("/reverse/" + s.toString()).accept(MediaType.TEXT_PLAIN)).andReturn();
        assertEquals(res.getResponse().getStatus(),STATUS_OK);
        assertEquals(res.getResponse().getContentAsString(), s.reverse().toString());
    }
    @Test
    public void testOne() throws Exception {
        StringBuilder s = new StringBuilder().append("s");
        MvcResult res = this.mvc.perform(MockMvcRequestBuilders.get("/reverse/" + s.toString()).accept(MediaType.TEXT_PLAIN)).andReturn();
        assertEquals(res.getResponse().getStatus(),STATUS_OK);
        assertEquals(res.getResponse().getContentAsString(), s.reverse().toString());
    }

}
