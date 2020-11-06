package br.com.itaumon.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.itaumon.beans.Alarme;

public interface AlarmeDAO extends CrudRepository <Alarme, Integer>{
	

}
