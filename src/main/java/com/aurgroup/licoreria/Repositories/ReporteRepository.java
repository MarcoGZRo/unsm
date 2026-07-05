package com.aurgroup.licoreria.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurgroup.licoreria.Models.Reporte;

/**
 * ReporteRepository
 */
@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {


}
