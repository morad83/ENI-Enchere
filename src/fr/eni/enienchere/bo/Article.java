package fr.eni.enienchere.bo;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int noArticle;
	private String nomArticle;
	private String description;
	private Date dateDebutEncheres;
	private Date dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private String etatVente;
	private int noUtilisateur;
	private String noCategorie;

	public Article () {	
	}
	
	
	public Article (int noArticle, String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres, int miseAPrix, int prixVente, int noUtilisateur, String noCategorie) {
		this.noArticle=noArticle;
		this.nomArticle=nomArticle;
		this.description=description;
		this.dateDebutEncheres=dateDebutEncheres;
		this.dateFinEncheres=dateFinEncheres;
		this.miseAPrix=miseAPrix;
		this.prixVente=prixVente;
		this.noUtilisateur=noUtilisateur;
		this.noCategorie=noCategorie;
	}
}
