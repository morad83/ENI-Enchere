package fr.eni.enienchere.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enienchere.bo.Utilisateur;
import fr.eni.enienchere.dal.DAOFactory;
import fr.eni.enienchere.dal.UtilisateurDAO;
import fr.eni.enienchere.dal.UtilisateurDAOJdbcImpl;

public class UtilisateurManager {
	
	private static UtilisateurDAO utilisateurDAO;
	private static BusinessException businessException;

    static {
        UtilisateurManager.utilisateurDAO = new UtilisateurDAOJdbcImpl();
        UtilisateurManager.businessException = new BusinessException();
    }
    
    public static Utilisateur inscriptionUtilisateur(final Utilisateur utilisateur) throws BusinessException, SQLException {
        if (!UtilisateurManager.businessException.hasErreurs()) {
            UtilisateurManager.utilisateurDAO.insert(utilisateur);
            return utilisateur;
        }
        throw UtilisateurManager.businessException;
    }
    
    public static List<String> listAllPseudos() throws BusinessException {
        return UtilisateurManager.utilisateurDAO.getAllPseudos();
    }

	
	public UtilisateurManager() {
		UtilisateurManager.utilisateurDAO=DAOFactory.getUtilisateurDAO();
	}
	

	public Utilisateur selectionnerUtilisateur(String pseudo) throws BusinessException {
		return UtilisateurManager.utilisateurDAO.selectUtilisateurByPseudo(pseudo);
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
	
	
}
