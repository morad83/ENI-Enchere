package fr.eni.enienchere.dal;

import fr.eni.enienchere.bll.BusinessException;
import fr.eni.enienchere.bo.Utilisateur;

public interface UtilisateurDAO {

	public Utilisateur selectUtilisateurByPseudo(String pseudo) throws BusinessException;
	
}
