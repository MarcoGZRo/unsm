package com.aurgroup.licoreria.Repositories;

import com.aurgroup.licoreria.Models.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * MarcaRepository
 */
@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {}
