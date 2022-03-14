package com.telemedecineBE;


import com.telemedecineBE.dao.RoleRepository;
import com.telemedecineBE.dao.UserRepository;
import com.telemedecineBE.entities.Role;
import com.telemedecineBE.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    //Get all Roles
    public List<Role> findALL(){
        return roleRepository.findAll();
    }

    //Get Role BY ID
    public Optional<Role> findById(int id){
        return roleRepository.findById(id);
    }

    //Delete Role
    public void delete(int id){
        roleRepository.deleteById(id);
    }

    //Update Role
    public void save(Role role){
        roleRepository.save(role);
    }

    //Assign Role to User
   /* public void assignUserRole(Integer userId, Integer roleId){
        User user  = userRepository.findById(userId).orElse(null);
        Role role = roleRepository.findById(roleId).orElse(null);
        Set<Role> userRoles = user.getRoles();
        userRoles.add(role);
        user.setRoles(userRoles);
        userRepository.save(user);
    }

    //Unassign Role to User
    public void unassignUserRole(Integer userId, Integer roleId){
        User user  = userRepository.findById(userId).orElse(null);
        user.getRoles().removeIf(x -> x.getId()==roleId);
        userRepository.save(user);
    }

    public Set<Role> getUserRoles(User user){
        return user.getRoles();
    }



    */
}
