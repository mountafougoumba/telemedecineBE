package com.telemedecineBE.web;

import com.telemedecineBE.Security.AES;
import com.telemedecineBE.dao.*;
import com.telemedecineBE.entities.*;
import com.telemedecineBE.enumeration.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@Transactional
public class ReportController {

    final String secretKey = "We need to finish this! Also ssshhhhhhhhhhh!!!!";

    private ReportRepository reportRep;
    private UserDao userDao;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private AdminRepository adminRepository;

    @Autowired
    ReportController(ReportRepository rp, UserDao userDao,
                     DoctorRepository doctorRepository, AdminRepository adminRepository,
                     PatientRepository patientRepository) {
        this.reportRep = rp;
        this.userDao = userDao;
        this.doctorRepository = doctorRepository;
        this.adminRepository = adminRepository;
        this.patientRepository = patientRepository;
    }

    @GetMapping("/report/id={id}")
    Report getReport(@PathVariable Integer id) {
        Optional<Report> test = reportRep.findById(id);
        if(test.isPresent()){
            Report report = test.get();
            report.setUrl(AES.decrypt(report.getUrl(), this.secretKey));
            return report;
        } else {
            throw new IllegalStateException("Report with id " + id + " does not exist!");
        }
    }


    @GetMapping("/report/download/id={id}")
    public ResponseEntity<ByteArrayResource> downloadReport(@PathVariable Integer id){
        Boolean exists = reportRep.existsById(id);
        if(!exists){
            throw new IllegalStateException("Report with id " + id + " does not exist!");
        }
        Report report = reportRep.findById(id).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(report.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + report.getName() + "\"")
                .body(new ByteArrayResource(report.getData()));
    }

    @GetMapping("/reports")
    List<Report> getReports (){
        List<Report> reports = reportRep.findAll();
        for(Report rep: reports){
            rep.setUrl(AES.decrypt(rep.getUrl(), this.secretKey));
        }
        return reports;
    }

    @PutMapping("/report/id={id}/remove-report")
    void delete(@PathVariable Integer id, @RequestBody Integer userId) {
        Boolean exists = reportRep.existsById(id);
        if(!exists){
            throw new IllegalStateException("Report with id " + id + " does not exist!");
        }
        Report report = reportRep.findById(id).get();
        List<Integer> userIds = report.getUserIds();
        List<Integer> idsToRemove = new ArrayList<>();
        exists = userDao.existsById(userId);
        if(!exists){
            throw new IllegalStateException("User with id " + userId + " does not exist.");
        }
        //Delete is this way so if a patient deletes a report, the doctor will still have access to it. Etc..
        User user = userDao.findById(userId);
        if(user.getUserType() == UserType.PATIENT) {
            Patient patient = patientRepository.findById(userId);
            List<Integer> reps = patient.getReportIds();
            reps.remove(id);
            patient.setReportIds(reps);
            patientRepository.save(patient);
            idsToRemove.add(patient.getId());
        }
        if(user.getUserType() == UserType.DOCTOR){
            Doctor doctor = doctorRepository.findById(userId).get();
            List<Integer> reps = doctor.getReportIds();
            reps.remove(id);
            doctor.setReportIds(reps);
            doctorRepository.save(doctor);
            idsToRemove.add(doctor.getId());
        }
        if(user.getUserType() == UserType.ADMIN) {
            Admin admin = adminRepository.findById(userId);
            List<Integer> reps = admin.getReportIds();
            reps.remove(id);
            admin.setReportIds(reps);
            adminRepository.save(admin);
            idsToRemove.add(admin.getId());
        }

        for(Integer remove: idsToRemove){
            userIds.remove(remove);
        }
        if(userIds.size() == 0){
            reportRep.deleteById(id);
        } else {
            report.setUserIds(userIds);
            reportRep.save(report);
        }
    }

    @PutMapping("/report/id={id}")
    Report put(
            @PathVariable Integer id,
            @RequestBody Report report
    ) {
        Optional<Report> currReport = reportRep.findById(id);
        if(currReport.isPresent()) {
            Report updatedReport = currReport.get();

            if(report.getName() != null && report.getName()!= updatedReport.getName()) {
                updatedReport.setName(report.getName());
            }

            reportRep.save(updatedReport);
            return updatedReport;
        } else {
            throw new IllegalStateException("Report with id, " + id + " does not exist.");
        }
    }

    @PutMapping("/report/userId={userId}")
    Report sendReport(@PathVariable Integer userId, @RequestBody Report report){
        Boolean exists = userDao.existsById(userId);
        if(!exists){
            throw new IllegalStateException("User with id " + userId + " does not exist.");
        }
        User user = userDao.findById(userId);
        if(user.getUserType() == UserType.PATIENT) {
            Patient patient = patientRepository.findById(userId);
            List<Integer> reps = patient.getReportIds();
            if(!reps.contains(report.getId())) {
                reps.add(report.getId());
            }
            patient.setReportIds(reps);
            patientRepository.save(patient);
        }
        if(user.getUserType() == UserType.DOCTOR){
            Doctor doctor = doctorRepository.findById(userId).get();
            List<Integer> reps = doctor.getReportIds();
            if(!reps.contains(report.getId())) {
                reps.add(report.getId());
            }
            doctor.setReportIds(reps);
            doctorRepository.save(doctor);
        }
        if(user.getUserType() == UserType.ADMIN){
            Admin admin = adminRepository.findById(userId);
            List<Integer> reps = admin.getReportIds();
            if(!reps.contains(report.getId())) {
                reps.add(report.getId());
            }
            admin.setReportIds(reps);
            adminRepository.save(admin);
        }
        List<Integer> userIds = report.getUserIds();
        Boolean alreadyAssigned = false;
        for(Integer id: userIds){
            if(id == userId){
                alreadyAssigned = true;
            }
        }
        if(!alreadyAssigned){
            userIds.add(userId);
        }
        report.setUserIds(userIds);
        return reportRep.save(report);
    }

    @PostMapping("/report/userId={userId}")
    Report post(
            @RequestParam(value="file") MultipartFile file,
            @PathVariable(value="userId")Integer userId
            ) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Report rep = new Report(fileName, file.getContentType(), file.getInputStream().readAllBytes());
        rep = reportRep.save(rep);
        rep.setUrl(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/report/download/id=")
                        .path(rep.getId().toString())
                .toUriString());
        rep = this.sendReport(userId, rep);
        rep.setSize(rep.getData().length);
        System.out.println(rep.getData());
        rep.setUrl(AES.encrypt(rep.getUrl(), this.secretKey));
        reportRep.save(rep);
        return rep;
    }
}
