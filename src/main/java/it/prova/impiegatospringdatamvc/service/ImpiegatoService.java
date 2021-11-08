package it.prova.impiegatospringdatamvc.service;

import java.util.List;

import it.prova.impiegatospringdatamvc.model.Impiegato;

public interface ImpiegatoService {
	public List<Impiegato> listAllElements();

	public Impiegato caricaSingoloElemento(Long id);
	
	public void aggiorna(Impiegato impiegatoInstance);

	public void inserisciNuovo(Impiegato impiegatoInstance);

	public void rimuovi(Impiegato impiegatoInstance);
	
	public List<Impiegato> findByExample(Impiegato example);
	
	public List<Impiegato> cercaByCognomeENomeILike(String cognomeTerm, String nomeTerm);
}
