package fr.eni.enienchere.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enienchere.bo.Utilisateur;
import fr.eni.enienchere.dal.DAOFactory;
import fr.eni.enienchere.dal.UtilisateurDAO;

public class UtilisateurManager {
	
	private  UtilisateurDAO utilisateurDAO;
	
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
		List<String> listeEmails=new ArrayList<>();
		listePseudos = listerPseudos();
		listeEmails = listAllEMail();
////////////////////////////////////////////////
System.out.println("start mng MAJutilisateur");
////////////////////////////////////////////////	
////////////////////////////////////////////////
System.out.println("mng controle");
////////////////////////////////////////////////		
		//verif pseudo si le pseudo a été modifié
		if(!utilisateur.getPseudo().trim().equalsIgnoreCase(utilisateurMaj.getPseudo().trim())) {
			
			if (listePseudos.contains(utilisateurMaj.getPseudo())) {
			  	businessException.ajouterErreur(30014);
			}
			
			if(utilisateurMaj.getPseudo().trim().length()>50) {
			  	businessException.ajouterErreur(30015);
			}
		}
		if(!utilisateur.getEmail().trim().equalsIgnoreCase(utilisateurMaj.getEmail().trim())) {
			
			if (listeEmails.contains(utilisateurMaj.getEmail())) {
			  	businessException.ajouterErreur(30027);
			}
			
			if(utilisateurMaj.getEmail().trim().length()>20) {
			  	businessException.ajouterErreur(30018);
			}
		}	
		
		
		
		if(!utilisateur.getNom().trim().equalsIgnoreCase(utilisateurMaj.getNom().trim()) & utilisateurMaj.getNom().trim().length()>30) {
		  	businessException.ajouterErreur(30016);
		}
		
		if(!utilisateur.getPrenom().trim().equalsIgnoreCase(utilisateurMaj.getPrenom().trim()) & utilisateurMaj.getNom().trim().length()>30) {
		  	businessException.ajouterErreur(30017);
		}



		if(!utilisateur.getTelephone().trim().equalsIgnoreCase(utilisateurMaj.getTelephone().trim()) & utilisateurMaj.getNom().trim().length()>15) {
		  	businessException.ajouterErreur(30019);
		}

		if(!utilisateur.getRue().trim().equalsIgnoreCase(utilisateurMaj.getRue().trim()) & utilisateurMaj.getRue().trim().length()>30) {
		  	businessException.ajouterErreur(30020);
		}

		if(!utilisateur.getCodePostal().trim().equalsIgnoreCase(utilisateurMaj.getCodePostal().trim()) & utilisateurMaj.getCodePostal().trim().length()>10) {
		  	businessException.ajouterErreur(30021);
		}

		if(!utilisateur.getVille().trim().equalsIgnoreCase(utilisateurMaj.getVille().trim()) & utilisateurMaj.getVille().trim().length()>30) {
		  	businessException.ajouterErreur(30022);
		}

		if(!utilisateur.getMotDePasse().trim().equalsIgnoreCase(utilisateurMaj.getMotDePasse().trim()) & utilisateurMaj.getMotDePasse().trim().length()>30) {
		  	businessException.ajouterErreur(30023);
		}
////////////////////////////////////////////////
System.out.println("end mng controle");
////////////////////////////////////////////////
		if(!businessException.hasErreurs()) {
////////////////////////////////////////////////
System.out.println("mng controle success");
////////////////////////////////////////////////
		this.utilisateurDAO.updateUtilisateur(utilisateurMaj, utilisateur.getPseudo());
		}
		else{
////////////////////////////////////////////////
System.out.println("mng controle echec");
////////////////////////////////////////////////
			throw businessException;
		}
////////////////////////////////////////////////
System.out.println("end mng MAJutilisateur");
////////////////////////////////////////////////
	}
		
	public List<String> listerPseudos() throws BusinessException {
		return this.utilisateurDAO.selectAllPseudo();
	}
	
	public Boolean connexion(Utilisateur utilisateurCo) throws BusinessException {
		BusinessException businessException = new BusinessException();

		Utilisateur utilisateurCoBd = recupererIds(utilisateurCo.getPseudo());

		if(utilisateurCoBd.getPseudo()!=null && utilisateurCoBd.getMotDePasse()!=null) {
			
			if(utilisateurCoBd.getMotDePasse().equals(utilisateurCo.getMotDePasse())){
				return true;
			}
			else {
				businessException.ajouterErreur(30024);
				throw businessException;
			}
		}
		
		else {
		  	businessException.ajouterErreur(30024);
			throw businessException;
		}
	}
	
	public Utilisateur recupererIds(String pseudoCo) throws BusinessException {
		return this.utilisateurDAO.selectIdsUtilisateurByPseudo(pseudoCo);
	}

	
	public void supUtilisateur (String pseudoASup) throws BusinessException {
		this.utilisateurDAO.delete(pseudoASup);
	}
	
	public Utilisateur selectAdresseByPseudo(String pseudo) throws BusinessException {
		return this.utilisateurDAO.selectAdresseByPseudo(pseudo);
	}
	
	public int selectNoByPseudo(String pseudo) throws BusinessException {
		return this.utilisateurDAO.selectNoUtilisateurByPseudo(pseudo);
	}
	
}
