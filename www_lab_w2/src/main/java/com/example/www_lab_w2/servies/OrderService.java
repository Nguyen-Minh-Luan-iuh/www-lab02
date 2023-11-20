package com.example.www_lab_w2.servies;

import com.example.www_lab_w2.entity.*;
import com.example.www_lab_w2.repositories.CustomerRepository;
import com.example.www_lab_w2.repositories.EmployeeRepository;
import com.example.www_lab_w2.repositories.OrderRepository;
import com.example.www_lab_w2.repositories.ProductRepository;
import com.example.www_lab_w2.requests.OrderDetailRequest;
import com.example.www_lab_w2.requests.OrderRequest;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderService {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;
    private ProductRepository productRepository;

    public OrderService() {
        this.orderRepository = new OrderRepository();
        this.customerRepository = new CustomerRepository();
        this.employeeRepository = new EmployeeRepository();
        this.productRepository = new ProductRepository();
    }

    public boolean addOrder(OrderRequest orderRequest){
        Optional<Customer> customer = customerRepository.findById(orderRequest.getCustId());
        if(customer.isEmpty()){
            return false;
        }
        Optional<Employee>employee = employeeRepository.findById(orderRequest.getEmpId());
        if(employee.isEmpty()){
            return false;
        }
        Order order = new Order();
        order.setOrderDate(DateTime.now());
        order.setEmployee(employee.get());
        order.setCustomer(customer.get());

        List<OrderDetail> details = new ArrayList<>();
        for(int i = 0; i < orderRequest.getDetails().size(); i++){
            OrderDetailRequest currDetail = orderRequest.getDetails().get(i);
            Optional<Product> existingProduct = productRepository.findById(currDetail.getpId());
            if(existingProduct.isEmpty()){
                return false;
            }
            double price = existingProduct.get().getCurrentPrice();
            if(price == -1) return false;
            productRepository.decreaseQuantity(existingProduct.get().getProductId(), currDetail.getQuantity());
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(existingProduct.get());
            orderDetail.setQuantity(currDetail.getQuantity());
            orderDetail.setPrice(price);
            orderDetail.setOrder(order);

            details.add(orderDetail);
        }

        order.setDetails(details);
        orderRepository.addOrder(order);
        return true;
    }

    public List<Order> getAll(String date, String fromDate, String toDate){
        if(date != null){
            return orderRepository.statisticByDay(date);
        }
        if(fromDate != null && toDate != null){
            return orderRepository.statisticFromRange(fromDate, toDate);
        }
        return orderRepository.getAll();
    }
}
