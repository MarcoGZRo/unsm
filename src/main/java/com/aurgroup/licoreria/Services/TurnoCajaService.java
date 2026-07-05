package com.aurgroup.licoreria.Services;

import com.aurgroup.licoreria.Models.TurnoCaja;
import java.util.List;
/**
 * AuthService
 * @author MarcoGZRo
 */
public interface TurnoCajaService {

    TurnoCaja createTurnoCaja(TurnoCaja turnoCaja);

    TurnoCaja getTurnoCajaById(Integer id);

    List<TurnoCaja> getAllTurnosCaja();

    TurnoCaja updateTurnoCaja(Integer id, TurnoCaja turnoCaja);

    void deleteTurnoCaja(Integer id);

    TurnoCaja cerrarTurnoCaja(Integer id);
}
