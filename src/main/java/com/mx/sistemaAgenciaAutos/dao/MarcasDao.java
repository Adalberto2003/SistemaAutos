package com.mx.sistemaAgenciaAutos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.sistemaAgenciaAutos.model.Marcas;

public interface MarcasDao extends JpaRepository<Marcas, Long>{

	List<Marcas> findByIdOrNombre(Long id, String nombre);

}
