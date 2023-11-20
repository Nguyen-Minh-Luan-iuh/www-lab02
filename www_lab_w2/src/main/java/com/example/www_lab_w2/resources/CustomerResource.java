package com.example.www_lab_w2.resources;

import com.example.www_lab_w2.entity.Customer;
import com.example.www_lab_w2.servies.CustomerService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/customers")
public class CustomerResource {
    private CustomerService customerService;

    public CustomerResource() {
        this.customerService = new CustomerService();
    }

    @POST
    @Path("/")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addCustomer(Customer customer){
        customerService.addCustomer(customer);
        return Response.ok("{\"message\": \"Create customer successfully\"  }").build();
    }

    @GET
    @Path("/")
    @Produces("application/json")
    public Response getAll(){
        List<Customer> customers = customerService.getAll();
        return Response.ok(customers).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response findById(@PathParam("id") long id){
        Optional<Customer> customer = customerService.findById(id);
        if(customer.isEmpty()){
            return Response.
                    status(Response.Status.NOT_FOUND).
                    entity("{\"error\": \"Customer not found\"}").build();
        }

        return Response.ok(customer.get()).build();
    }

    @PUT
    @Path("/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response updateCustomer(@PathParam("id") long id, Customer customer) {
        boolean result = customerService.updateCustomer(id, customer);
        if (!result) {
            return Response.
                    status(Response.Status.NOT_FOUND).
                    entity("{\"error\": \"Customer not found\"}").build();
        }
        return Response.ok().entity("{\"message\": \"Update customer successfully\"}").build();
    }
}