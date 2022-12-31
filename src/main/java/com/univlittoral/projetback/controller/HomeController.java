package com.univlittoral.projetback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.univlittoral.projetback.dto.AuteurDTO;
import com.univlittoral.projetback.dto.HomeDTO;
import com.univlittoral.projetback.dto.IndicateurDTO;
import com.univlittoral.projetback.dto.LivreDTO;
import com.univlittoral.projetback.entity.LivreEntity;
import com.univlittoral.projetback.mapper.AuteurMapper;
import com.univlittoral.projetback.mapper.LivreMapper;
import com.univlittoral.projetback.service.AuteurService;
import com.univlittoral.projetback.service.LivreService;

import io.swagger.v3.oas.annotations.Operation;


@RestController //-> est utilisé pour marquer les classes en tant que contrôleur Spring.
@RequestMapping(value = "rest/bd") //=> l’URL d’accès à votre controller.
public class HomeController {

	@Autowired
	private LivreService service;
	@Autowired
	private AuteurService auteurService;
	
	@GetMapping(value = "home") 
	public HomeDTO findAll() { 
		  
		  HomeDTO myHome = new HomeDTO();
		  myHome.setLivres(LivreMapper.map(service.findAll()));
		  
		  IndicateurDTO indic = new IndicateurDTO();
		 
		  indic.setNbLivres(LivreMapper.map(service.findAll()).size());
		  indic.setNbAuteurs(0);
		  indic.setNbGenres(0);
		  
		  myHome.setIndicateurs(indic);
		  
		  return myHome;
	   
	  }
	
	@GetMapping(value="livres/{id}")
	public LivreDTO findOne(@PathVariable Long id)
	{
		return LivreMapper.map(service.findById(id)); 
    }
	
	@DeleteMapping(value="livres/{id}")
	public void deleteLivre(@PathVariable Long id) {
		service.deleteById(id);
	}
	
	

	@PostMapping(value = "livres")
	public void addLivre(@RequestBody LivreDTO livre) {
		
		LivreEntity livreEntity = new LivreEntity();
		service.save(LivreMapper.mapDTOtoEntity(livre));
	}
	
	   @PutMapping(value="livres/{id}") 
	    public void modifyLivre(@RequestBody LivreDTO livreDTO, @PathVariable Long id) { 
	        LivreEntity livreUpdate = service.findById(id);
	        service.save(LivreMapper.mapEntityToEntity(livreUpdate, livreDTO));
	    }
	   
	   //Retrouver un auteur avec son id
	    @GetMapping("auteurs/{id}")
	    @Operation(summary ="Récupère l’auteur ayant comme clé primaire {id}")
	    public AuteurDTO findAuteurById(@PathVariable Long id) {
	        return AuteurMapper.map(auteurService.findAuteurById(id));
	    }
	    
		//Obtenir les auteurs
		@GetMapping("auteurs")
		@Operation(summary ="Affiche une liste d’auteurs")
		public List<AuteurDTO> findAllAuteurs(){
			return AuteurMapper.map(auteurService.findAuteurs());
		}
}
