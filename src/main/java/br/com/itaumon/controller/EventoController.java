package br.com.itaumon.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.itaumon.beans.Alarme;
import br.com.itaumon.beans.Evento;
import br.com.itaumon.dao.AlarmeDAO;
import br.com.itaumon.dao.EventoDAO;

@RestController // Indica que a classe irá responder protocolo HTTP (GET / POST)
@CrossOrigin("*") //Libera o acesso externo para o TomCat, permite acessos externos.

public class EventoController {
	
	@Autowired
	private EventoDAO dao;
	@Autowired
	private AlarmeDAO alarmedao;
	
	
	
	@GetMapping("/contagem/{i}/{f}")
	public ResponseEntity <List<String>> getContagem(@PathVariable String i, @PathVariable String f)throws Exception{
	
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date inicio = formato.parse(i);
		Date fim = formato.parse(f);
	
		List<String> resultado = new ArrayList<String>();
		
		for(Alarme a : alarmedao.findAll()) {
			long numero = dao.getByTotal(inicio, fim, a.getId());
			resultado.add(a.getNome());
			
			resultado.add(String.valueOf(numero));
			
		}
		if(resultado.size()==0) {
			return ResponseEntity.status(404).build();
			
		}
	
		return ResponseEntity.ok(resultado);
	
	
	}

	
	@PostMapping ("/novoevento")
	public ResponseEntity<Evento> add(@RequestBody Evento objeto){
	try {
		dao.save(objeto);
		return ResponseEntity.ok(objeto);
	} catch (Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(403).build();
	}
}
	
	@GetMapping ("/eventos")
	public ResponseEntity <List<Evento>> getAll() {
		List<Evento> lista = (List<Evento>) dao.findAll();
		if (lista.size() == 0) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(lista);
	}

	
	
	@GetMapping ("/data/{i}/{f}")
	public ResponseEntity <List<Evento>> getIntervalo (@PathVariable String i, @PathVariable String f) throws Exception {
		SimpleDateFormat formato = new SimpleDateFormat ("yyyy-MM-dd");
		
		
		Date inicio = formato.parse(i);
		System.out.println("data==>" + inicio);
		Date fim = formato.parse(f);
		List<Evento> lista = (List<Evento>) dao.findByDataBetween(inicio, fim);
		
		
		if (lista.size() == 0) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(lista);
	}
	
	
}