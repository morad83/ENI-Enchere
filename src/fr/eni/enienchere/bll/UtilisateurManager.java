package fr.eni.enienchere.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enienchere.bo.Utilisateur;
import fr.eni.enienchere.dal.DAOFactory;
import fr.eni.enienchere.dal.UtilisateurDAO;

public class UtilisateurManager {
	
	private  UtilisateurDAO utilisateurDAO;
	
	private static UtilisateurDAO utilisateurDAOStatic;
	
	public UtilisateurManager(){
        this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
    }
    
    public Utilisateur inscriptionUtilisateur(final Utilisateur utilisateur) throws BusinessException, SQLException {
		BusinessException businessException = new BusinessException();
    	if (!businessException.hasErreurs()) {
            this.utilisateurDAO.insert(utilisateur);
            return utilisateur;
        }
        throw businessException;
    }
    
    public List<String> listAllPseudos() throws BusinessException {
        return this.utilisateurDAO.getAllPseudos();
    }
    
    public List<String> listAllEMail() throws BusinessException {
    	return this.utilisateurDAO.getAllEmail();
    }

	

	public Utilisateur selectionnerUtilisateur(String pseudo) throws BusinessException {
		return this.utilisateurDAO.selectUtilisateurByPseudo(pseudo);
	}
	
	

	
	public void MAJUtilisateur(Utilisateur utilisateur, Utilisateur utilisateurMaj) throws BusinessException {
		BusinessException businessException = new BusinessException();
		List<String> listePseudos=new ArrayList<>();
		listePseudos = listerPseudos();
		
		
		
		
		//verif pseudo si le pseudo a été modifié
		if(!utilisateur.getPseudo().trim().equalsIgnoreCase(utilisateurMaj.getPseudo().trim())) {
			////////////////////////////////////////////////
			System.out.println(utilisateur.getPseudo()+"!="+utilisateurMaj.getPseudo());
			////////////////////////////////////////////////
			if (listePseudos.contains(utilisateurMaj.getPseudo())) {
			  	businessException.ajouterErreur(30014);

				////////////////////////////////////////////////
				System.out.println("ErreurPseudo");
				////////////////////////////////////////////////
			}
			
			if(utilisateurMaj.getPseudo().trim().length()>50) {
			  	businessException.ajouterErreur(30015);
			}
		}	
		
		if(!utilisateur.getNom().trim().equalsIgnoreCase(utilisateurMaj.getNom().trim()) && utilisateurMaj.getNom().trim().length()>30) {
		  	businessException.ajouterErreur(30016);
		}
		
		if(!utilisateur.getPrenom().trim().equalsIgnoreCase(utilisateurMaj.getPrenom().trim()) && utilisateurMaj.getNom().trim().length()>30) {
		  	businessException.ajouterErreur(30017);
		}

		if(!utilisateur.getEmail().trim().equalsIgnoreCase(utilisateurMaj.getEmail().trim()) && utilisateurMaj.getEmail().trim().length()>20) {
		  	businessException.ajouterErreur(30018);
		}

		if(!utilisateur.getTelephone().trim().equalsIgnoreCase(utilisateurMaj.getTelephone().trim()) && utilisateurMaj.getNom().trim().length()>15) {
		  	businessException.ajouterErreur(30016);
		}

		if(!utilisateur.getRue().trim().equalsIgnoreCase(utilisateurMaj.getRue().trim()) && utilisateurMaj.getRue().trim().length()>30) {
		  	businessException.ajouterErreur(30016);
		}

		if(!utilisateur.getCodePostal().trim().equalsIgnoreCase(utilisateurMaj.getCodePostal().trim()) && utilisateurMaj.getCodePostal().trim().length()>10) {
		  	businessException.ajouterErreur(30016);
		}

		if(!utilisateur.getVille().trim().equalsIgnoreCase(utilisateurMaj.getVille().trim()) && utilisateurMaj.getVille().trim().length()>30) {
		  	businessException.ajouterErreur(30016);
		}

		if(!utilisateur.getMotDePasse().trim().equalsIgnoreCase(utilisateurMaj.getMotDePasse().trim()) && utilisateurMaj.getMotDePasse().trim().length()>30) {
		  	businessException.ajouterErreur(30016);
		}

		
		if(!businessException.hasErreurs()) {
		this.utilisateurDAO.updateUtilisateur(utilisateurMaj, utilisateur.getPseudo());
		}
		else
		{
			throw businessException;
		}
	}
		
	public List<String> listerPseudos() throws BusinessException {
		return this.utilisateurDAO.selectAllPseudo();
	}
	
	public Boolean connexion(Utilisateur utilisateurCo) throws BusinessException {
		BusinessException businessException = new BusinessException();

		Utilisateur utilisateurCoBd = recupererIds(utilisateurCo.getPseudo());
		//////////////////
		System.out.println(utilisateurCo.getPseudo());
		System.out.println(utilisateurCo.getMotDePasse());
		System.out.println(utilisateurCo.getPseudo());
		System.out.println(utilisateurCo.getMotDePasse());
	
		if(utilisateurCoBd.getPseudo().equals(utilisateurCo.getPseudo())
			&& utilisateurCoBd.getMotDePasse().equals(utilisateurCo.getMotDePasse()) 
			) {
			return true;
		}
		else {
		  	businessException.ajouterErreur(30024);
			throw businessException;
		}
	}
	
	public Utilisateur recupererIds(String pseudoCo) throws BusinessException {
		return this.utilisateurDAO.selectIdsUtilisateurByPseudo(pseudoCo);
	}
	
	public static void supUtilisateur (int id) throws BusinessException {
		utilisateurDAOStatic.delete(id);
	}
	
}
