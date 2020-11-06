package br.com.itaumon.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.itaumon.beans.Evento;

public interface EventoDAO extends CrudRepository <Evento, Integer>{
	
	List<Evento> findByDataBetween(Date inicio, Date fim);
	
	@Query(value="SELECT count(id) as total FROM itaumon.tb_evento where"
			+ " data >= :inicio and data<= :fim AND alarme_id = :num", nativeQuery = true)
		long getByTotal(@Param("inicio") Date inicio, @Param("fim") Date fim, @Param("num") int num);

}
