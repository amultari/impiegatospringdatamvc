package it.prova.impiegatospringdatamvc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.prova.impiegatospringdatamvc.model.Impiegato;

public interface ImpiegatoRepository extends CrudRepository<Impiegato, Long> {
	List<Impiegato> findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByCognomeAsc(String nomeTerm,
			String cognomeTerm);
}
