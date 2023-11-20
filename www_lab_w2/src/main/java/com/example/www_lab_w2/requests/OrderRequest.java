package com.example.www_lab_w2.requests;

import java.util.List;

public class OrderRequest {
    private long custId;
    private long empId;
    private List<OrderDetailRequest> details;

    public OrderRequest() {
    }

    public OrderRequest(long custId, long empId) {
        this.custId = custId;
        this.empId = empId;
    }

    public OrderRequest(long custId, long empId, List<OrderDetailRequest> details) {
        this.custId = custId;
        this.empId = empId;
        this.details = details;
    }

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public List<OrderDetailRequest> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetailRequest> details) {
        this.details = details;
    }
}
