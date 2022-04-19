package com.telemedecineBE.dao;

import java.util.List;

import com.telemedecineBE.entities.Patient;
import com.telemedecineBE.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Optional;

@Transactional
public interface ReportRepository extends JpaRepository<Report, Serializable> {

    @Query("select r from Report r")
    public List<Report> findAllReports();
    public Optional<Report> findById(Integer id);
    public Boolean existsById(Integer id);
    public void deleteById(Integer id);
}
