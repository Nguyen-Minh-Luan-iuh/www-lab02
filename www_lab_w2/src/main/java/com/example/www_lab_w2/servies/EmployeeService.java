package com.example.www_lab_w2.servies;

import com.example.www_lab_w2.entity.Employee;
import com.example.www_lab_w2.entity.Order;
import com.example.www_lab_w2.enums.EmployeeStatus;
import com.example.www_lab_w2.repositories.EmployeeRepository;
import com.example.www_lab_w2.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private OrderRepository orderRepository;

    public EmployeeService() {
        this.employeeRepository = new EmployeeRepository();
        this.orderRepository = new OrderRepository();
    }

    public void addEmployee(Employee employee){
        employee.setStatus(EmployeeStatus.ACTIVE);
        employeeRepository.addEmployee(employee);
    }

    public List<Employee> findAll(){
        return employeeRepository.getAll();
    }

    public Optional<Employee> findById(long id){
        return employeeRepository.findById(id);
    }

    public boolean updateEmployee(long id, Employee employee){
        Optional<Employee> rs = employeeRepository.findById(id);
        if (rs.isEmpty()) {
            return false;
        }
        Employee existingEmployee = rs.get();

        if(employee.getAddress() != null){
            existingEmployee.setAddress(employee.getAddress());
        }
        if(employee.getDob() != null){
            existingEmployee.setDob(employee.getDob());
        }
        if(employee.getEmail() != null){
            existingEmployee.setEmail(employee.getEmail());
        }
        if(employee.getPhone() != null){
            existingEmployee.setPhone(employee.getPhone());
        }
        if(employee.getFullName() != null){
            existingEmployee.setFullName(employee.getFullName());
        }
        if(employee.getStatus() != null){
            existingEmployee.setStatus(employee.getStatus());
        }
        employeeRepository.updateEmployee(existingEmployee);
        return true;
    }

    public boolean deleteEmployee(long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){
            return false;
        }
        employeeRepository.deleteEmployee(id);
        return true;
    }

    public List<Order>getOrder(String date, String fromDate, String toDate, long empId){
        if(date != null){
            return orderRepository.statisticByDayByEmployee(date, empId);
        }
        if(fromDate != null && toDate != null){
            return orderRepository.statisicFromRangeByEmployee(fromDate, toDate, empId);
        }

        return orderRepository.statisticByEmployee(empId);
    }
}