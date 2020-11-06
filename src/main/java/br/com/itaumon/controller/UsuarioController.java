package br.com.itaumon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.itaumon.beans.Usuario;
import br.com.itaumon.dao.UsuarioDAO;

@RestController // Indica que a classe irá responder protocolo HTTP (GET / POST)
@CrossOrigin("*") //Libera o acesso externo para o TomCat, permite acessos externos.

public class UsuarioController {
	
	@Autowired // Indica que o gerenciamento do atributo será feito pelo Spring Boot.
	private UsuarioDAO dao;
	
	@GetMapping("/todosusuarios")
	public ResponseEntity <List <Usuario>> getAll (){
		List <Usuario> lista = (List <Usuario>) dao.findAll();
		if (lista.size() == 0) {
			return ResponseEntity.status(408).build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/usuario/{cod}") // Variavel da URL indica por {}
	public ResponseEntity <Usuario> pesquisarUsuario (@PathVariable int cod) {
		Usuario objeto = dao.findById(cod).orElse(null);
		if (objeto == null) {
			return ResponseEntity.status(408).build();
		}
		return ResponseEntity.ok(objeto);
	}
	
	@PostMapping ("/login")
	public ResponseEntity <Usuario> logar(@RequestBody Usuario objeto) {
		Usuario resposta = dao.findByEmailAndSenha(objeto.getEmail(), objeto.getSenha() );
		if (resposta == null) {
			resposta = dao.findByRacfAndSenha(objeto.getEmail(), objeto.getSenha());
			if (resposta == null) {
			return ResponseEntity.status(404).build();
		}
		}
		return ResponseEntity.ok(resposta);
	
	}
	
	@PostMapping("/gravarusuario")
	public ResponseEntity <Usuario> gravarusuario (@RequestBody Usuario objeto) {
		try {
		Usuario gravacao = dao.save(objeto);
			return ResponseEntity.ok(gravacao);
		} catch (Exception e ) {
			return ResponseEntity.status(404).build();
		}
	}
	
	@GetMapping ("/pesqnome/{no}")
	public ResponseEntity <List<Usuario>> pesquisarNome (@PathVariable String no) {
		List <Usuario> pesquisa = (List <Usuario>) dao.findByNome(no);
		if (pesquisa.size() == 0) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(pesquisa);		
	}
	
	
}
