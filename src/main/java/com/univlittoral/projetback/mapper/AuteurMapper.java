package com.univlittoral.projetback.mapper;

import java.util.ArrayList;
import java.util.List;

import com.univlittoral.projetback.dto.AuteurDTO;
import com.univlittoral.projetback.entity.AuteurEntity;

public class AuteurMapper {

	public static AuteurDTO map(AuteurEntity entity) {
		AuteurDTO auteur = new AuteurDTO();
		  
		  
		auteur.setId(entity.getId()); 
		auteur.setNom(entity.getNom());
		auteur.setPrenom(entity.getPrenom());
		auteur.setDateNaissance(entity.getDateNaissance());

		
		  return auteur;
		 
	}
	
	//Permet de récupérer les auteurs
	public static List<AuteurDTO> map(List<AuteurEntity> AuteursEntity){
		if(null == AuteursEntity) {
			return null;
		}
		List<AuteurDTO> result = new ArrayList<AuteurDTO>();
		for(final AuteurEntity auteur : AuteursEntity) {
			result.add(AuteurMapper.map(auteur));
		}
		
		return result;
	}
	

}
