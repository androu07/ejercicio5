package com.example.laboratorio3.controller;

import com.example.laboratorio3.entity.Employees;
import com.example.laboratorio3.repository.EmployeesRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public class EmployeeController {

    final
    EmployeesRepository employeesRepository;

    public EmployeeController(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @GetMapping("/empleados")
    public String listaEmployee(Model model){
        List<Employees> listaempleados = employeesRepository.findAll();
        model.addAttribute("listaEmpleados", listaempleados);
        return "employee/lista";
    }

    @GetMapping("/empleados/new")
    public String nuevoEmployeeForm( ) {
        return "employee/newFrm";
    }

    @GetMapping("/guardar")
    public String guardarEmployee(Employees employees) {
        employeesRepository.save(employees);
        return "redirect:/employee/lista";
    }

    @GetMapping("/editar")
    public String editarEmployee(Model model,
                                @RequestParam("id") int id) {
        Optional<Employees> optionalEmployees = employeesRepository.findById(id);
        if (optionalEmployees.isPresent()){
            Employees employees = optionalEmployees.get();
            model.addAttribute("empleado", employees);
        }
        return "employee/editFrm";
    }

    @GetMapping("/borrar")
    public String borrarEmpleado(Model model,
                                 @RequestParam("id") int id) {
        Optional<Employees> optionalEmployees = employeesRepository.findById(id);
        if (optionalEmployees.isPresent()){
            employeesRepository.deleteById(id);
        }
        return "redirect:/employee/lista";
    }


}
