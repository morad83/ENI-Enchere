package fr.eni.enienchere.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enienchere.bll.BusinessException;
import fr.eni.enienchere.bll.UtilisateurManager;
import fr.eni.enienchere.bo.Utilisateur;

/**
 * Servlet implementation class ModifierProfilServlet
 */
@WebServlet("/modifierProfil")
public class ModifierProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierProfilServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();

		UtilisateurManager utilisateurManager = new UtilisateurManager();
			
		List<Integer> listeCodesErreur=new ArrayList<>();
		
		String pseudo=null;
		
		pseudo = (String) session.getAttribute("pseudoSession");
		

		if(pseudo==null || pseudo.trim().equals("")){ 
			  listeCodesErreur.add(30010); 
		}

	
		if(listeCodesErreur.size()>0) {
			request.setAttribute("listeCodesErreur",listeCodesErreur);
		}
		
	
		else {
			try {
				//insertion de l'objet
				Utilisateur utilisateur=  utilisateurManager.selectionnerUtilisateur(pseudo);
				request.setAttribute("utilisateur",utilisateur);
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modifierProfil.jsp");
		rd.forward(request, response);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
			
		List<Integer> listeCodesErreur=new ArrayList<>();		
		
		Utilisateur utilisateur;
		
		Utilisateur utilisateurMaj;
		
		String pseudo;
		
		String pseudoMaj;
		String nomMaj;
		String prenomMaj;
		String emailMaj;
		String telephoneMaj;
		String rueMaj;
		String codePostalMaj;
		String villeMaj;
		String motDePasseMaj;
		String nouveaumpMaj;
		String confirmationMaj;
				
		pseudo = (String) session.getAttribute("pseudoSession");
//////////////////////////////////////////////
System.out.println("serv start dopost modifierprofil");
//////////////////////////////////////////////
//////////////////////////////////////////////
System.out.println("serv controle session");
/////////////////////////////////////////////		
		//controle session//
		if(pseudo==null || pseudo.trim().equals("")
		){
			  listeCodesErreur.add(30010);
		}	
		if(listeCodesErreur.size()>0) {
			request.setAttribute("listeCodesErreur",listeCodesErreur);
		}
		else {
			//chemin/enregistrer//
			// construction utilsateur et utilisateurmaj//
			//construction utilisateur//
			try {				
				utilisateur=  utilisateurManager.selectionnerUtilisateur(pseudo);

				//construction utilisateurmaj//
				//recuperation des paramètres du formulaire//
				pseudoMaj = request.getParameter("pseudo");
				nomMaj = request.getParameter("nom");
				prenomMaj = request.getParameter("prenom"); 
				emailMaj = request.getParameter("email");
				telephoneMaj = request.getParameter("telephone");
				rueMaj = request.getParameter("rue");
				codePostalMaj = request.getParameter("codePostal");
				villeMaj = request.getParameter("ville");
				motDePasseMaj = request.getParameter("motDePasse");
				nouveaumpMaj = request.getParameter("nouveaump");
				confirmationMaj = request.getParameter("confirmation");
				
				utilisateurMaj= new Utilisateur(pseudoMaj, nomMaj, prenomMaj, emailMaj, telephoneMaj, rueMaj, codePostalMaj, villeMaj, motDePasseMaj);
////////////////////////////////////////////////
System.out.println("fin serv recup attributs utilisateur et maj");
////////////////////////////////////////////////
////////////////////////////////////////////////
System.out.println("serv controle");
////////////////////////////////////////////////
////////////////////////////////////////////////
System.out.println("serv controle serv");
////////////////////////////////////////////////
				if(
						utilisateur.getPseudo().trim().equalsIgnoreCase(utilisateurMaj.getPseudo().trim()) &
						utilisateur.getNom().trim().equalsIgnoreCase(utilisateurMaj.getNom().trim()) &
						utilisateur.getPrenom().trim().equalsIgnoreCase(utilisateurMaj.getPrenom().trim()) &
						utilisateur.getEmail().trim().equalsIgnoreCase(utilisateurMaj.getEmail().trim()) &
						utilisateur.getTelephone().trim().equalsIgnoreCase(utilisateurMaj.getTelephone().trim()) &
						utilisateur.getRue().trim().equalsIgnoreCase(utilisateurMaj.getRue().trim()) &
						utilisateur.getCodePostal().trim().equalsIgnoreCase(utilisateurMaj.getCodePostal().trim()) &
						utilisateur.getVille().trim().equalsIgnoreCase(utilisateurMaj.getVille().trim()) &
						utilisateur.getMotDePasse().trim().equalsIgnoreCase(utilisateurMaj.getMotDePasse().trim()) &
						nouveaumpMaj.trim() =="" & confirmationMaj.trim()==""
					){
					  	listeCodesErreur.add(10012); 
////////////////////////////////////////////////
System.out.println("serv erreur controle serv (AucunChangement)");
////////////////////////////////////////////////
					}
				//verif nvmp//
				if (!nouveaumpMaj.trim().equals(confirmationMaj)) {
					listeCodesErreur.add(10015);
///////////////////////////////////
System.out.println("serv erreur controle serv (serverreurmp)");
/////////////////////////////////
				}
////////////////////////////////////////////////
System.out.println("fin serv controle serv");
////////////////////////////////////////////////
				if(listeCodesErreur.size()>0) {
					request.setAttribute("listeCodesErreur",listeCodesErreur);
				}
				else {
					try {
////////////////////////////////////////
System.out.println("serv controle mng");
/////////////////////////////////////////////////////////
					utilisateurManager.MAJUtilisateur(utilisateur, utilisateurMaj);
//////////////////////////////////////////////
System.out.println("fin serv controle mng");
////////////////////////////////////////
					} catch (BusinessException e) {
////////////////////////////////////
System.out.println("catch controle mng");
//////////////////////////////////
					e.printStackTrace();
					request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
					utilisateur=  utilisateurManager.selectionnerUtilisateur(pseudo);
					request.setAttribute("utilisateur",utilisateur);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modifierProfil.jsp");
					rd.forward(request, response);
					}
				} 
			} catch (BusinessException e) {
///////////////////////////
System.out.println("catch");
/////////////////////////////
				e.printStackTrace();
				request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			}
		}
		if(listeCodesErreur.size()>0) {
			request.setAttribute("listeCodesErreur",listeCodesErreur);
			try {
				utilisateur=  utilisateurManager.selectionnerUtilisateur(pseudo);
				request.setAttribute("utilisateur",utilisateur);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
////////////////////////////////////////////
System.out.println("endservlet erreur ");
///////////////////////////////////////////////
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modifierProfil.jsp");
			rd.forward(request, response);
		}
		else {
////////////////////////////////////////////
System.out.println("endservlet success ");
/////////////////////////////////////////////
			RequestDispatcher rd = request.getRequestDispatcher("monProfil");
			rd.forward(request, response);
		}
	}
}
