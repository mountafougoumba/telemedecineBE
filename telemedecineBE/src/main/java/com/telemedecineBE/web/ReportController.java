package com.telemedecineBE.web;

import com.telemedecineBE.dao.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.telemedecineBE.entities.Report;

import java.util.Optional;

@RestController
public class ReportController {

    private ReportRepository reportRep;

    @Autowired
    ReportController(ReportRepository rp) {
        this.reportRep = rp;
    }

    @GetMapping("/report/id={id}")
    Optional<Report> get(@PathVariable Integer id) {
        return reportRep.findById(id);
    }

    @DeleteMapping("/report/id={id}")
    void delete(@PathVariable Integer id) {
        Optional<Report> report = reportRep.findById(id);
        report.ifPresentOrElse(
                rep -> reportRep.delete(rep),
                () -> {
                    throw new IllegalStateException("Report with id, " + id + " does not exist.");
                }
        );
    }

    @PutMapping("/report/id={id}")
    Report put(
            @PathVariable Integer id,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String upload
    ) {
        Optional<Report> report = reportRep.findById(id);
        if(report.isPresent()) {
            Report updatedReport = report.get();

            if(description != null) {
                updatedReport.setDescription(description);
            }
            if(upload != null) {
                updatedReport.setDoctor_upload(upload);
            }

            reportRep.save(updatedReport);
            return updatedReport;
        } else {
            throw new IllegalStateException("Report with id, " + id + " does not exist.");
        }
    }

    @PostMapping("/report")
    Report post(
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String upload
    ) {
        Report report = new Report();

        if(description != null) {
            report.setDescription(description);
        }
        if(upload != null) {
            report.setDoctor_upload(upload);
        }

        reportRep.save(report);
        return report;
    }
}
