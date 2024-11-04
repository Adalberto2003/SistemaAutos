package com.mx.sistemaAgenciaAutos.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*CREATE TABLE MARCAS_AGENCIA(
ID NUMBER PRIMARY KEY,
NOMBRE VARCHAR2(80) NOT NULL,
ORIGEN VARCHAR2(80) NOT NULL,
ESLOGAN VARCHAR2(100)
);*/

@Entity
@Table(name = "MARCAS_AGENCIA")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Marcas {

	@Id
	private Long id;
	private String nombre;
	private String origen;
	private String eslogan;
	
	//Cardinalidad 
	//1 a muchos
	
	@OneToMany(mappedBy ="marca", cascade = CascadeType.ALL )
	@JsonIgnore// Sirve para omitir una propiedad o lista de propiedades en mi json
	List<Modelos> lista = new ArrayList<Modelos>();//Esta variable mo se agrega en lo demas
}
