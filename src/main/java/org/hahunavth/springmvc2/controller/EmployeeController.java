package org.hahunavth.springmvc2.controller;

import org.hahunavth.springmvc2.entity.Employee;
import org.hahunavth.springmvc2.entity.Skill;
import org.hahunavth.springmvc2.repository.EmployeeRepository;
import org.hahunavth.springmvc2.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("/employees/{id}")
    public String employee(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeRepository.getEmployeeById(id));
        model.addAttribute("skills", skillRepository.findAll());
        return "employees";
    }

    @RequestMapping(value = "/employees", method= RequestMethod.GET)
    public String employeeList(Model model) {
        model.addAttribute("employees", employeeRepository.listEmployees());
        return "employees";
    }

    @RequestMapping(value="/employees", method=RequestMethod.POST)
    public String employeeAdd(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            Model model
    ) {
        Employee employee = new Employee();
        employee.setEmail(email);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employeeRepository.addEmployee(employee);
        return "redirect:/employees";
    }

    @RequestMapping(value="/employees/{id}/skills", method=RequestMethod.POST)
    public String employeeAddSkill(
            @PathVariable Long id,
            @RequestParam Long skillId,
            Model model
    ) {
        Skill skill = skillRepository.findOne(skillId);
        Employee employee = employeeRepository.getEmployeeById(id);
        if (employee != null) {
            if (!employee.hasSkill(skill)) {
                employee.getSkills().add(skill);
            }
            employeeRepository.updateEmployee(employee);
            model.addAttribute("employee", employeeRepository.getEmployeeById(id));
            model.addAttribute("skills", skillRepository.findAll());
            return "redirect:/employees/" + employee.getId();
        }
        model.addAttribute("employees", employeeRepository.listEmployees());
        return "redirect:/employees";
    }
}
