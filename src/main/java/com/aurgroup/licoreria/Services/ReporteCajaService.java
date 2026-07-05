package com.aurgroup.licoreria.Services;

import com.aurgroup.licoreria.Models.Reporte;
import java.util.List;
/**
 * AuthService
 * @author MarcoGZRo
 */
public interface ReporteCajaService {

    Reporte createReporte(Reporte reporte);

    Reporte getReporteById(Long id);

    List<Reporte> getAllReportes();

    Reporte updateReporte(Long id, Reporte reporte);

    void deleteReporte(Long id);
}
