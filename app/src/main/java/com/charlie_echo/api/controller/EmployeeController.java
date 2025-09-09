package com.charlie_echo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.charlie_echo.api.model.Employee;
import com.charlie_echo.api.service.EmployeeService;

import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /** GET - tous les employés */
    @GetMapping
    public Iterable<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    /** GET - employé par ID */
    @GetMapping("/{id}")
    public Optional<Employee> getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    /** POST - créer un nouvel employé */
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    /** PUT - mise à jour complète */
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        return employeeService.getEmployee(id)
                .map(employee -> {
                    // On remplace complètement l’objet existant avec le nouvel objet
                    updatedEmployee.setId(id); // Lombok doit générer setId ici
                    return employeeService.saveEmployee(updatedEmployee);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    /** PATCH - mise à jour partielle */
    @PatchMapping("/{id}")
    public Employee patchEmployee(@PathVariable Long id, @RequestBody Employee updatedFields) {
        return employeeService.getEmployee(id)
                .map(employee -> {
                    // On ne modifie que les champs non nuls
                    if (updatedFields.getFirstName() != null)
                        employee.setFirstName(updatedFields.getFirstName());
                    if (updatedFields.getLastName() != null)
                        employee.setLastName(updatedFields.getLastName());
                    if (updatedFields.getMail() != null)
                        employee.setMail(updatedFields.getMail());
                    if (updatedFields.getPassword() != null)
                        employee.setPassword(updatedFields.getPassword());
                    return employeeService.saveEmployee(employee);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    /** DELETE - supprimer un employé */
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
