package com.mx.sistemaAgenciaAutos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.sistemaAgenciaAutos.model.Modelos;
import com.mx.sistemaAgenciaAutos.service.ModelosImp;

@RestController
@RequestMapping(path = "ModelosWs")
@CrossOrigin
public class ModelosWs {

	@Autowired
	ModelosImp modeloImp;

	// http://localhost:9000/ModelosWs/listar
	@GetMapping(path = "listar")
	public List<Modelos> listar() {
		return modeloImp.listar();
	}

	// http://localhost:9000/ModelosWs/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Modelos modelo) {
		String response = modeloImp.guardar(modelo);

		if (response.equals("idModeloExiste"))
			return new ResponseEntity<>("No se guardo, ese idModelo ya existe", HttpStatus.OK);
		else if (response.equals("nombreModExiste"))
			return new ResponseEntity<>("No se guardo, ese nombreModelo ya existe", HttpStatus.OK);
		else if (response.equals("idMarNoExiste"))
			return new ResponseEntity<>("No se guardo, ese idMarca no existe", HttpStatus.OK);
		else
			return new ResponseEntity<>(modelo, HttpStatus.CREATED);
	}

	// http://localhost:9000/ModelosWs/buscar
	@PostMapping(path = "buscar")
	public ResponseEntity<?> buscar(@RequestBody Long id) {
		Modelos modBuscar = modeloImp.buscar(id);

		if (modBuscar == null) {
			return new ResponseEntity<>("No se encontró un modelo con ese ID", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(modBuscar, HttpStatus.OK);
		}
	}

	// http://localhost:9000/ModelosWs/editar
	@PostMapping(path = "editar")
	public ResponseEntity<?> editar(@RequestBody Modelos modelo) {
	    String response = modeloImp.editar(modelo);

	    if (response.equals("idMarcaNoExiste")) {
	        return new ResponseEntity<>("No se pudo editar, el idMarca no existe", HttpStatus.OK);
	    } else if (response.equals("idModeloNoExiste")) {
	        return new ResponseEntity<>("No se pudo editar, el idModelo no existe", HttpStatus.OK);
	    
	    } else {
	        return new ResponseEntity<>(modelo, HttpStatus.OK);
	    }
	}
	
	// http://localhost:9000/ModelosWs/eliminar
	@PostMapping(path = "eliminar")
	public ResponseEntity<?> eliminar(@RequestBody Modelos modelo){
		boolean response =modeloImp.eliminar(modelo.getId());
		if (response == false)
			return new ResponseEntity<>("Ese registro no existe", HttpStatus.OK);
		else
			return new ResponseEntity<>("se elimino", HttpStatus.OK);
	}
}
