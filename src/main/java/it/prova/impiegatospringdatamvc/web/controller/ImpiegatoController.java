package it.prova.impiegatospringdatamvc.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.impiegatospringdatamvc.model.Impiegato;
import it.prova.impiegatospringdatamvc.service.ImpiegatoService;
import it.prova.impiegatospringdatamvc.utility.UtilityForm;

@Controller
@RequestMapping(value = "/impiegato")
public class ImpiegatoController {

	@Autowired
	private ImpiegatoService impiegatoService;

	@GetMapping
	public ModelAndView listAll() {
		ModelAndView mv = new ModelAndView();
		List<Impiegato> results = impiegatoService.listAllElements();
		mv.addObject("impiegato_list_attribute", results);
		mv.setViewName("impiegato/list");
		return mv;
	}

	@GetMapping("/search")
	public String search() {
		return "impiegato/search";
	}

	@PostMapping("/list")
	public String listByExample(Impiegato example, ModelMap model) {
		List<Impiegato> results = impiegatoService.findByExample(example);
		model.addAttribute("impiegato_list_attribute", results);
		return "impiegato/list";
	}

	@GetMapping("/insert")
	public String create(Model model) {
		model.addAttribute("insert_impiegato_attr", new Impiegato());
		return "impiegato/insert";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("insert_impiegato_attr") Impiegato impiegato, ModelMap model,
			RedirectAttributes redirectAttrs) {

		// ATTENZIONE!!!! la validazione con spring viene fatta in un modo più elegante
		// questa è fatta a mano e per ora prendiamola per buona
		if (!UtilityForm.validateBean(impiegato)) {
			model.addAttribute("errorMessage", "Attenzione! Sono presenti errori di validazione");
			model.addAttribute("insert_impiegato_attr", impiegato);
			return "impiegato/insert";
		}
		// ======================================================================

		impiegatoService.inserisciNuovo(impiegato);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/impiegato";
	}

	@GetMapping("/show/{idImpiegato}")
	public String show(@PathVariable(required = true) Long idImpiegato, Model model) {
		model.addAttribute("show_impiegato_attr", impiegatoService.caricaSingoloElemento(idImpiegato));
		return "impiegato/show";
	}

}
