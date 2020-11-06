package br.com.itaumon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.itaumon.beans.Alarme;
import br.com.itaumon.dao.AlarmeDAO;

@RestController // Indica que a classe irá responder protocolo HTTP (GET / POST)
@CrossOrigin("*") //Libera o acesso externo para o TomCat, permite acessos externos.

public class AlarmeController {
	
	@Autowired // Indica que o gerenciamento do atributo será feito pelo Spring Boot.
	private AlarmeDAO dao;
	
	
	@PostMapping ("/novoalarme")
	public ResponseEntity<Alarme> add(@RequestBody Alarme objeto){
	try {
		dao.save(objeto);
		return ResponseEntity.ok(objeto);
	} catch (Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(403).build();
	}
}
	
	@GetMapping ("/alarmes")
	public ResponseEntity <List<Alarme>> getAll() {
		List<Alarme> lista = (List<Alarme>) dao.findAll();
		if (lista.size() == 0) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(lista);
	}
	
	
}