package com.aurgroup.licoreria.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurgroup.licoreria.Models.Categoria;

/**
 * CategoriaRepository
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

	
}
