package fr.eni.enienchere.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.enienchere.bll.BusinessException;
import fr.eni.enienchere.bo.Utilisateur;

public interface UtilisateurDAO {

	//NE retourne pas le noUtilisateur et l'admin
	public Utilisateur selectUtilisateurByPseudo(String pseudo) throws BusinessException;
	
	public List<String> selectAllPseudo() throws BusinessException;

	public void updateUtilisateur(Utilisateur utilisateur, String pseudo) throws BusinessException;
	
    public Utilisateur insert(final Utilisateur p0) throws BusinessException, SQLException;
    
    List<String> getAllPseudos() throws BusinessException;
	
}
