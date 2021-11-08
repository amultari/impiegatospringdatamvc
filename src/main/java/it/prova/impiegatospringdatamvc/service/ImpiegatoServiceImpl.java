package it.prova.impiegatospringdatamvc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.impiegatospringdatamvc.model.Impiegato;
import it.prova.impiegatospringdatamvc.repository.ImpiegatoRepository;

@Service
public class ImpiegatoServiceImpl implements ImpiegatoService {

	@Autowired
	private ImpiegatoRepository repository;

	// questo mi serve per il findByExample2 che risulta 'a mano'
	// o comunque in tutti quei casi in cui ho bisogno di costruire custom query nel
	// service
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public List<Impiegato> listAllElements() {
		return (List<Impiegato>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Impiegato caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Impiegato impiegatoInstance) {
		repository.save(impiegatoInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Impiegato impiegatoInstance) {
		repository.save(impiegatoInstance);

	}

	@Override
	@Transactional
	public void rimuovi(Impiegato impiegatoInstance) {
		repository.delete(impiegatoInstance);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Impiegato> findByExample(Impiegato example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select i from Impiegato i where i.id = i.id ");

		if (StringUtils.isNotEmpty(example.getNome())) {
			whereClauses.add(" i.nome  like :nome ");
			paramaterMap.put("nome", "%" + example.getNome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getCognome())) {
			whereClauses.add(" i.cognome like :cognome ");
			paramaterMap.put("cognome", "%" + example.getCognome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getMatricola())) {
			whereClauses.add(" i.matricola like :matricola ");
			paramaterMap.put("matricola", "%" + example.getMatricola() + "%");
		}
		if (example.getStato() != null) {
			whereClauses.add(" i.stato =:stato ");
			paramaterMap.put("stato", example.getStato());
		}
		if (example.getDataDiNascita() != null) {
			whereClauses.add("i.dataDiNascita >= :dataDiNascita ");
			paramaterMap.put("dataDiNascita", example.getDataDiNascita());
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Impiegato> typedQuery = entityManager.createQuery(queryBuilder.toString(), Impiegato.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Impiegato> cercaByCognomeENomeILike(String cognomeTerm, String nomeTerm) {
		return repository.findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByCognomeAsc(nomeTerm,
				cognomeTerm);
	}

}
