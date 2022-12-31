package com.univlittoral.projetback.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity // => Pour que votre application Spring boot sache que cette classe est une entité
@Table(name="auteurs") //=> le nom de la table de votre base de données associée à cet objet Entité.
public class AuteurEntity {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	private String prenom;
	private Date dateNaissance;
	
	@OneToMany(targetEntity=LivreEntity.class, mappedBy="auteur",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LivreEntity> livres;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public List<LivreEntity> getLivres() {
		return livres;
	}

	public void setLivres(List<LivreEntity> livres) {
		this.livres = livres;
	}
	
	

}
