package com.aurgroup.licoreria.Repositories;

import com.aurgroup.licoreria.Models.Producto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ProductoRepository
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Optional<Producto> findFirstByNombreIgnoreCaseAndMarca_IdMarcaAndCategoria_IdCategoria(
        String nombre,
        Integer idMarca,
        Integer idCategoria
    );
}
