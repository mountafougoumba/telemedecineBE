package com.telemedecineBE.web;

import com.telemedecineBE.RoleService;
import com.telemedecineBE.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class RoleController {
        @Autowired
        private RoleService roleService;

        //Get all roles
        @GetMapping("/roles")
        public String findAll(Model model){
            model.addAttribute("roles", roleService.findALL());
            return "role";
        }

        //find role by id
        @RequestMapping("/roles/findById")
        @ResponseBody
        public Optional<Role> findById(Integer id){
            return roleService.findById(id);
        }

        //Add Role
        @GetMapping(value="/roles/addNew")
        @ResponseBody
        public String addNew(Role role){
            roleService.save(role);
            return "redirect:/roles";
        }

        //update role
        @GetMapping(value="/roles/update" )
        public String update(Role role){
            roleService.save(role);
            return "redirect:/roles";
        }

        // Delete Role
        @RequestMapping(value="/roles/delete")
        public String delete(Integer id){
            roleService.delete(id);
            return "redirect:/roles";
        }
}
