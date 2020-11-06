package br.com.itaumon.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name="TB_EVENTO")

public class Evento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="data")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@ManyToOne
	@JsonIgnoreProperties("eventos")
	private Alarme alarme;
	
	@ManyToOne
	@JsonIgnoreProperties("eventos")
	private Equipamento equipamento;
	
	
	public Evento() {
		super();
	}
	
	public Evento(int id, Date data, Alarme alarme, Equipamento equipamento) {
		super();
		this.id = id;
		this.data = data;
		this.alarme = alarme;
		this.equipamento = equipamento;
	}
	
	public Equipamento getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Alarme getAlarme() {
		return alarme;
	}
	public void setAlarme(Alarme alarme) {
		this.alarme = alarme;
	}
	
}
