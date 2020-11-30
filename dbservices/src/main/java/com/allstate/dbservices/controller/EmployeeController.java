package com.allstate.dbservices.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allstate.dbservices.exception.EmployeeNotFoundException;
import com.allstate.dbservices.model.Employee;
import com.allstate.dbservices.model.Invoice;
import com.allstate.dbservices.model.Salary;
import com.allstate.dbservices.model.Tax;
import com.allstate.dbservices.payload.CustomResponse;
import com.allstate.dbservices.repository.EmployeeRepository;
import com.allstate.dbservices.repository.InvoiceRepository;
import com.allstate.dbservices.repository.TaxRepository;



@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaxRepository taxRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @GetMapping("/")
    public List<Employee> getEmployee() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id", required = true) Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/{id}")
    public CustomResponse<Employee> editEmployee(@Valid @RequestBody Employee newEmployee, @PathVariable(value = "id", required = true) Long id) {
        employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        Employee updatedEmp = new Employee();
        updatedEmp.setFirstName(newEmployee.getFirstName());
        updatedEmp.setLastName(newEmployee.getLastName());
        updatedEmp.setDateOfBirth(newEmployee.getDateOfBirth());
        updatedEmp.setHiringDate(newEmployee.getHiringDate());
        updatedEmp.setPostalCode(newEmployee.getPostalCode());
        updatedEmp.setProvince(newEmployee.getProvince());
        CustomResponse<Employee> response =  new CustomResponse<>();
        response.setData(employeeRepository.save(updatedEmp));
        response.setMessage("Employee information saved successfully");
        return response;
    }

    @DeleteMapping("/{id}")
    public CustomResponse<Employee> deleteEmployee(@PathVariable(value = "id", required = true) Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeRepository.deleteById(id);
        CustomResponse<Employee> response =  new CustomResponse<>();
        response.setData(employeeRepository.save(employee));
        response.setMessage("Employee deleted successfully");
        return response;
    }

    @PostMapping("/salary/{employeeId}")
    public CustomResponse<Employee> addSalary(@RequestBody Salary newSalary, @PathVariable(value = "employeeId", required = true) Long employeeId) {
        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
        Employee updatedEmp = new Employee();
        updatedEmp.setSalary(newSalary.getSalary());
        updatedEmp.setBonus(newSalary.getBonus());
        CustomResponse<Employee> response =  new CustomResponse<>();
        response.setData(employeeRepository.save(updatedEmp));
        response.setMessage("Salary added successfully");
        return response;
    }

    @GetMapping("/invoice/{id}")
    public CustomResponse<Invoice> generateInvoice(@PathVariable(value = "id", required = true) Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        Tax tax = taxRepository.findByProvinceAndSalary(employee.getProvince(), employee.getSalary());
        Long totalSalary = ( employee.getSalary() ) - (employee.getSalary() * tax.getFederalTax() / 100 ) +
                ( employee.getSalary() * tax.getProvinceTax() / 100 ) +
                ( employee.getBonus());
        Invoice invoice =  new Invoice();
        invoice.setSalaryAfterTax(totalSalary);
        invoice.setEmployee(employee);
        invoice.setTax(tax);
        CustomResponse<Invoice> response =  new CustomResponse<>();
        response.setData(invoiceRepository.save(invoice));
        response.setMessage("Invoice generated successfully");
        return response;
    }
}
