package com.example.laboratorio3.controller;


import com.example.laboratorio3.entity.Employees;
import com.example.laboratorio3.repository.EmployeesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HistoryController {

    final
    EmployeesRepository employeesRepository;

    public HistoryController(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }
    @GetMapping("/historial")
    public String historialEmpleados(){

        return "history/historial";
    }

    @PostMapping("/BuscarHistorial")
    public String buscarHistorial(@RequestParam("searchField") String searchField,
                                      Model model){
        List<Employees> employeesList = employeesRepository.findByFirst_nameOrLast_name(searchField);
        model.addAttribute("listaEmployees",employeesList);
        return "history/historial";
    }

}
