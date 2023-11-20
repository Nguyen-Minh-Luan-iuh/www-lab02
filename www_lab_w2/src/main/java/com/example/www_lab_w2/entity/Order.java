package com.example.www_lab_w2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "Order.STATISTIC_BY_DAY", query = "SELECT o FROM Order o WHERE DATE_FORMAT(o.orderDate, '%Y-%m-%d') = :dateTime"),
        @NamedQuery(name = "Order.STATISTIC_FROM_RANGE", query = "SELECT o FROM Order o WHERE DATE_FORMAT(o.orderDate, '%Y-%m-%d') >= :fromDate AND DATE_FORMAT(o.orderDate, '%Y-%m-%d') <= :toDate"),
        @NamedQuery(name = "Order.GET_ALL", query = "SELECT o FROM Order o"),
        @NamedQuery(name = "Order.STATISTIC_FROM_RANGE_BY_EMPLOYEE", query = "SELECT o FROM Order o WHERE " +
                "     DATE_FORMAT(o.orderDate, '%Y-%m-%d') >= :fromDate AND DATE_FORMAT(o.orderDate, '%Y-%m-%d') <= :toDate AND o.employee.empId = :empId"),
        @NamedQuery(name = "Order.STATISTIC_BY_EMPLOYEE", query = "SELECT o FROM Order o WHERE o.employee.empId = :empId"),
        @NamedQuery(name = "Order.STATISTIC_BY_DAY_BY_EMPLOYEE", query = "SELECT o FROM Order o WHERE DATE_FORMAT(o.orderDate, '%Y-%m-%d') = :date AND o.employee.empId = :empId")
})
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long orderId;
    @Column(name = "order_date")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private DateTime orderDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cust_id")
    private Customer customer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderDetail> details;

    public Order() {
    }

    public Order(DateTime orderDate, Customer customer, Employee employee) {
        this.orderDate = orderDate;
        this.customer = customer;
        this.employee = employee;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public DateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(DateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", customer=" + customer +
                ", employee=" + employee +
                '}';
    }
}
