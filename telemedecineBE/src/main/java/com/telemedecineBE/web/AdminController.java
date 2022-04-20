package com.telemedecineBE.web;

import com.telemedecineBE.TelemedecineBeApplication;
import com.telemedecineBE.dao.AdminRepository;
import com.telemedecineBE.dao.UserDao;
import com.telemedecineBE.entities.Admin;
import com.telemedecineBE.entities.Appointment;
import com.telemedecineBE.entities.Doctor;
import com.telemedecineBE.entities.Requests;
import com.telemedecineBE.enumeration.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    private final AdminRepository adminRepository;
    private final UserDao userDao;
    @Autowired
    public AdminController(AdminRepository adminRepository, UserDao userDao){
        this.adminRepository = adminRepository;
        this.userDao = userDao;
    }

    @GetMapping("/admins")
    List<Admin> getAllAdmins() {
        System.out.println("getAllAdmins");
        return adminRepository.findAll();
    }

    @GetMapping("admin/id={id}/requests")
    List<Requests> getAppointmentRequests(@PathVariable(value = "id")Integer id){
        Boolean exists = adminRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Admin with id " + id + " does not exist.");
        }
        Admin admin = adminRepository.findById(id);
        return admin.getRequestedAppointments();
    }

    @GetMapping("/admin/id={id}")
    Admin getAdminById(@PathVariable(value = "id")Integer id){
        Boolean exists = adminRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Admin with id " + id + " does not exist.");
        }
        System.out.println("getAdminById");
        return adminRepository.findById(id);
    }

    @GetMapping("/admin/email={email}")
    Admin getAdminByEmail(@PathVariable(value = "email")String email){
        Boolean exists = adminRepository.existsByEmail(email);
        if(!exists){
            throw new IllegalStateException("Admin with email " + email + " does not exist.");
        }
        System.out.println("getAdminByEmail");
        return adminRepository.findByEmail(email);
    }

    @GetMapping("/admin/id={id}/reports")
    List<Integer> getReportIds(@PathVariable Integer id){
        System.out.println("getAdminReportIds");
        Boolean exists = adminRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Admin with id " + id + " does not exist.");
        }
        Admin admin = adminRepository.findById(id);
        return admin.getReportIds();
    }

    @PostMapping("/admin")
    Admin newAdmin(@RequestBody Admin admin){
        Boolean exists = adminRepository.existsByEmail(admin.getEmail());
        Boolean exists2 = adminRepository.existsByCellphone(admin.getCellphone());
        if(exists){
            throw new IllegalStateException("Admin with email " + admin.getEmail() + " already exists.");
        } else if(exists2){
            throw new IllegalStateException("Admin with phone " + admin.getCellphone() + " already exists.");
        }
        admin.setUserName(admin.getEmail());
        admin.setUserType(UserType.ADMIN);
        String encodedPassword = BCrypt.hashpw(admin.getUserpassword(), BCrypt.gensalt(TelemedecineBeApplication.strength));
        admin.setUserpassword(encodedPassword);
        adminRepository.save(admin);
        System.out.println("newAdmin");
        return admin;
    }

    @DeleteMapping("/admin/id={id}")
    void deleteAdminById(@PathVariable(value = "id")Integer id){
        Boolean exists = adminRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Admin with id " + id + " does not exist.");
        }
        System.out.println("deleteAdminById");
        adminRepository.deleteById(id);
    }

    @DeleteMapping("/admin/email={email}")
    void deleteAdminByEmail(@PathVariable(value = "email")String email){
        Boolean exists = adminRepository.existsByEmail(email);
        if(!exists){
            throw new IllegalStateException("Admin with email " + email + " does not exist.");
        }
        System.out.println("deleteAdminByEmail");
        adminRepository.deleteByEmail(email);
    }

    @PutMapping("/admin/id={id}")
    Admin updateAdminById(@PathVariable(value = "id") Integer id,
                                             @RequestBody Admin admin) {
        Boolean exists = adminRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException(
                    "Admin with id " + id + " does not exist."
            );
        }

        Admin currentAdmin = adminRepository.findById(id);

        if(admin.getFname() != null && admin.getFname().length() > 0 && currentAdmin.getFname() != admin.getFname()){
            currentAdmin.setFname(admin.getFname());
        }

        if(admin.getLname() != null && admin.getLname().length() > 0 && currentAdmin.getLname() != admin.getLname()){
            currentAdmin.setLname(admin.getLname());
        }

        if(admin.getEmail() != null && admin.getEmail().length() > 0 && admin.getEmail() != currentAdmin.getEmail() && !userDao.existsByEmail(admin.getEmail())){
            currentAdmin.setEmail(admin.getEmail());
        }

        if(admin.getCellphone() != null && admin.getCellphone().length() > 0 && admin.getCellphone() != currentAdmin.getCellphone() && !userDao.existsByCellphone(admin.getCellphone())){
            currentAdmin.setCellphone(admin.getCellphone());
        }

        if(admin.getUsername() != null && admin.getUsername() .length() > 0 && admin.getUsername()  != currentAdmin.getUsername() && !userDao.existsByUserName(admin.getUsername())){
            currentAdmin.setUserName(admin.getUsername() );
        }

        if(admin.getUserpassword() != null && admin.getUserpassword().length() > 0 && admin.getUserpassword() != currentAdmin.getUserpassword()){
            currentAdmin.setUserpassword(admin.getUserpassword());
        }

        if(admin.getState() != null && admin.getState() > 0 && admin.getState() != currentAdmin.getState()){
            currentAdmin.setState(admin.getState());
        }

        System.out.println(currentAdmin);
        adminRepository.save(currentAdmin);
        return currentAdmin;
    }
}
