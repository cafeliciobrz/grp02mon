package br.com.itaumon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.itaumon.beans.Equipamento;
import br.com.itaumon.dao.EquipamentoDAO;

@RestController // Indica que a classe irá responder protocolo HTTP (GET / POST)
@CrossOrigin("*") //Libera o acesso externo para o TomCat, permite acessos externos.

public class EquipamentoController {
	
	@Autowired
	private EquipamentoDAO dao;
	
	@PostMapping ("/novoequipamento")
	public ResponseEntity<Equipamento> add(@RequestBody Equipamento objeto){
	try {
		dao.save(objeto);
		return ResponseEntity.ok(objeto);
	} catch (Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(403).build();
	}
}
	
	@GetMapping ("/equipamentos")
	public ResponseEntity <List<Equipamento>> getAll() {
		List<Equipamento> lista = (List<Equipamento>) dao.findAll();
		if (lista.size() == 0) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(lista);
	}

}
