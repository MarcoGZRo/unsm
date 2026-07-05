package com.aurgroup.licoreria.Services.Impl;

import com.aurgroup.licoreria.Models.Reporte;
import com.aurgroup.licoreria.Repositories.ReporteRepository;
import com.aurgroup.licoreria.Services.ReporteCajaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * AuthService
 * @author MarcoGZRo
 */
@Service
public class ReporteCajaServiceImpl implements ReporteCajaService {

    private final ReporteRepository reporteRepository;

    public ReporteCajaServiceImpl(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    @Override
    @Transactional
    public Reporte createReporte(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    @Override
    @Transactional(readOnly = true)
    public Reporte getReporteById(Long id) {
        return reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reporte> getAllReportes() {
        return reporteRepository.findAll();
    }

    @Override
    @Transactional
    public Reporte updateReporte(Long id, Reporte reporte) {
        Reporte reporteExistente = getReporteById(id);

        reporteExistente.setTipo(reporte.getTipo());
        reporteExistente.setFechaInicio(reporte.getFechaInicio());
        reporteExistente.setFechaFin(reporte.getFechaFin());
        reporteExistente.setFechaGenerado(reporte.getFechaGenerado());
        reporteExistente.setTotalVentas(reporte.getTotalVentas());
        reporteExistente.setTotalTransacciones(reporte.getTotalTransacciones());

        return reporteRepository.save(reporteExistente);
    }

    @Override
    @Transactional
    public void deleteReporte(Long id) {
        if (!reporteRepository.existsById(id)) {
            throw new RuntimeException("Reporte no encontrado con ID: " + id);
        }

        reporteRepository.deleteById(id);
    }
}