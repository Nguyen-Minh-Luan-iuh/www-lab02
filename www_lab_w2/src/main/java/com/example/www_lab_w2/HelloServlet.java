package com.example.www_lab_w2;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/hello-world")
public class HelloServlet {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}