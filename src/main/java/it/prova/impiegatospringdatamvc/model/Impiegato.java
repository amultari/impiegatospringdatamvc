package it.prova.impiegatospringdatamvc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "impiegato")
public class Impiegato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank(message = "{nome.notblank}")
	@Column(name = "nome")
	private String nome;

	@NotBlank(message = "{cognome.notblank}")
	@Column(name = "cognome")
	private String cognome;

	@NotBlank(message = "{matricola.notblank}")
	@Column(name = "matricola")
	private String matricola;

	@NotNull(message = "{dataDiNascita.notnull}")
	@Column(name = "datadinascita")
	private Date dataDiNascita;

	@NotNull(message = "{stato.notblank}")
	@Column(name = "stato")
	@Enumerated(EnumType.STRING)
	private StatoImpiegato stato;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public StatoImpiegato getStato() {
		return stato;
	}

	public void setStato(StatoImpiegato stato) {
		this.stato = stato;
	}

}
