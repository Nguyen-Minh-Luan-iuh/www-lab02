package com.example.www_lab_w2.resources;

import com.example.www_lab_w2.entity.Product;
import com.example.www_lab_w2.entity.ProductImage;
import com.example.www_lab_w2.entity.ProductPrice;
import com.example.www_lab_w2.servies.ProductImageService;
import com.example.www_lab_w2.servies.ProductPriceService;
import com.example.www_lab_w2.servies.ProductService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/products")
public class ProductResource {
    private ProductService productService;
    private ProductImageService productImageService;
    private ProductPriceService productPriceService;

    public ProductResource() {
        this.productService = new ProductService();
        this.productImageService = new ProductImageService();
        this.productPriceService = new ProductPriceService();
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addProduct(Product product){
        productService.addProduct(product);

        return Response.ok().entity("{\"message\": \"Create product successfully\"}").build();
    }

    @GET
    @Path("/")
    @Produces("application/json")
    public Response getAll(){
        List<Product> products = productService.getAll();

        return Response.ok(products).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response findById(@PathParam("id") long id){
        Optional<Product> product = productService.findById(id);
        if(product.isEmpty()){
            return Response.status(404).entity("{\"message\": \"Product not found\"}").build();
        }
        return Response.ok(product.get()).build();
    }

    @PUT
    @Path("/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response updateProduct(@PathParam("id") long id, Product product){
        boolean result = productService.updateProduct(id, product);
        if(!result){
            return Response.status(404).entity("{\"message\": \"Product not found\"}").build();
        }
        return Response.ok().entity("{\"message\": \"Update product successfully\"}").build();
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response deleteProduct(@PathParam("id") long id){
        boolean result = productService.deleteProduct(id);
        if(!result){
            return Response.status(404).entity("{\"message\": \"Product not found\"}").build();
        }
        return Response.ok().entity("{\"message\": \"Delete product successfully\"}").build();
    }

    @POST
    @Path("/{id}/product_image")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addProductImage(ProductImage image, @PathParam("id") long id){
        productImageService.addProductImage(image, id);

        return Response.ok().entity("{\"message\": \"Add product image successfully\"}").build();
    }

    @DELETE
    @Path("/{id}/product_image/{imgId}")
    @Produces("application/json")
    public Response deleteProductImage(@PathParam("id")long pId, @PathParam("imgId")long id){
        productImageService.deleteProductImage(id, pId);
        return Response.ok().entity("{\"message\": \"Delete product image successfully\"}").build();
    }

    @POST
    @Path("/{id}/product_price")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addProductPrice(@PathParam("id") long pId, ProductPrice productPrice){
        boolean rs = productPriceService.addProductPrice(pId, productPrice);
        if(!rs){
            return Response.status(404).entity("{\"message\": \"Product not found\"}").build();
        }
        return Response.ok().entity("{\"message\": \"Add product price successfully\"}").build();
    }
}
