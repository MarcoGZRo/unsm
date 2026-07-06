package com.aurgroup.licoreria.Services.Impl;

import com.aurgroup.licoreria.Models.TurnoCaja;
import com.aurgroup.licoreria.Repositories.TurnoCajaRepository;
import com.aurgroup.licoreria.Services.TurnoCajaService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AuthService
 * @author MarcoGZRo
 */
@Service
public class TurnoCajaServiceImpl implements TurnoCajaService {

    private final TurnoCajaRepository turnoCajaRepository;

    public TurnoCajaServiceImpl(TurnoCajaRepository turnoCajaRepository) {
        this.turnoCajaRepository = turnoCajaRepository;
    }

    @Override
    @Transactional
    public TurnoCaja createTurnoCaja(TurnoCaja turnoCaja) {
        if (turnoCaja.getFechaApertura() == null) {
            turnoCaja.setFechaApertura(LocalDateTime.now());
        }

        if (turnoCaja.getEstado() == null || turnoCaja.getEstado().isBlank()) {
            turnoCaja.setEstado("aperturado");
        }

        return turnoCajaRepository.save(turnoCaja);
    }

    @Override
    @Transactional(readOnly = true)
    public TurnoCaja getTurnoCajaById(Integer id) {
        return turnoCajaRepository
            .findById(id)
            .orElseThrow(() ->
                new RuntimeException(
                    "Turno de caja no encontrado con ID: " + id
                )
            );
    }

    @Override
    @Transactional(readOnly = true)
    public List<TurnoCaja> getAllTurnosCaja() {
        return turnoCajaRepository.findAll();
    }

    @Override
    @Transactional
    public TurnoCaja updateTurnoCaja(Integer id, TurnoCaja turnoCaja) {
        TurnoCaja turnoExistente = getTurnoCajaById(id);

        turnoExistente.setCaja(turnoCaja.getCaja());
        turnoExistente.setVendedor(turnoCaja.getVendedor());
        turnoExistente.setMontoInicial(turnoCaja.getMontoInicial());
        turnoExistente.setMontoFinal(turnoCaja.getMontoFinal());
        turnoExistente.setFechaApertura(turnoCaja.getFechaApertura());
        turnoExistente.setFechaCierre(turnoCaja.getFechaCierre());
        turnoExistente.setEstado(turnoCaja.getEstado());

        return turnoCajaRepository.save(turnoExistente);
    }

    @Override
    @Transactional
    public void deleteTurnoCaja(Integer id) {
        if (!turnoCajaRepository.existsById(id)) {
            throw new RuntimeException(
                "Turno de caja no encontrado con ID: " + id
            );
        }

        turnoCajaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TurnoCaja cerrarTurnoCaja(Integer id) {
        TurnoCaja turnoCaja = getTurnoCajaById(id);

        turnoCaja.setFechaCierre(LocalDateTime.now());
        turnoCaja.setEstado("cerrado");

        return turnoCajaRepository.save(turnoCaja);
    }
}
