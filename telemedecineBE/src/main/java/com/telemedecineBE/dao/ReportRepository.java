package com.telemedecineBE.dao;

import java.util.List;
import com.telemedecineBE.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

public interface ReportRepository extends JpaRepository<Report, Serializable> {

    @Query("select r from Report r")
    public List<Report> findAllReports();
}
