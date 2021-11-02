package it.prova.impiegatospringdatamvc.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import it.prova.impiegatospringdatamvc.model.Impiegato;

public class UtilityForm {

	public static boolean validateBean(Impiegato impiegatoToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(impiegatoToBeValidated.getNome())
				|| StringUtils.isBlank(impiegatoToBeValidated.getCognome())
				|| StringUtils.isBlank(impiegatoToBeValidated.getMatricola()) 
				|| impiegatoToBeValidated.getStato() == null
				|| impiegatoToBeValidated.getDataDiNascita() == null) {
			return false;
		}
		return true;
	}
	

	public static Date parseDateArrivoFromString(String dataDiNascitaStringParam) {
		if (StringUtils.isBlank(dataDiNascitaStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataDiNascitaStringParam);
		} catch (ParseException e) {
			return null;
		}
	}
}
