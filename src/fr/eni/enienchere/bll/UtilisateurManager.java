package fr.eni.enienchere.bll;

import fr.eni.enienchere.bo.Utilisateur;
import fr.eni.enienchere.dal.DAOFactory;
import fr.eni.enienchere.dal.UtilisateurDAO;

public class UtilisateurManager {
	
	private UtilisateurDAO enienchereDAO;
	
	public UtilisateurManager() {
		this.enienchereDAO=DAOFactory.getUtilisateurDAO();
	}
	

	//cette méthode ne recupere pas le noUtilisateur
	public Utilisateur selectionnerUtilisateur(String pseudo) throws BusinessException {
		return this.enienchereDAO.selectUtilisateurByPseudo(pseudo);
	}
	
}
