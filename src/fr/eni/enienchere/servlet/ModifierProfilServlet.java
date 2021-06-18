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
	
		
		
		//definition noUtilisateurSession test
		HttpSession session = request.getSession();
		session.setAttribute("pseudoSession", "momo");
		session.setMaxInactiveInterval(50);
		//
		
		
		
		
		
		
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
			
		List<Integer> listeCodesErreur=new ArrayList<>();
		
		String pseudo=null;
		
		
		
		pseudo = (String) session.getAttribute("pseudoSession");
		
		
		//////////////////////////////////////////////
			System.out.println("start pseudo="+pseudo);
			///////////////////////////////////////
			
		if(pseudo==null || pseudo.trim().equals("")){ 
			  listeCodesErreur.add(30010); 
				
				////////////////////////////////////////////
			for (int z: listeCodesErreur) {
				System.out.println("controle !session"+z);
				}
				/////////////////////////////////////////////
		} 

	

		
		if(listeCodesErreur.size()>0) {
			request.setAttribute("listeCodesErreur",listeCodesErreur);
		}
		
	
		else {
			try {
				//insertion de l'objet
				Utilisateur utilisateur=  utilisateurManager.selectionnerUtilisateur(pseudo);
				request.setAttribute("utilisateur",utilisateur);
				
				
				////////////////////////////////////////////////
				System.out.println("insertion"+utilisateur.getPseudo());
				////////////////////////////////////////////////
				
								
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			
			}
		}
		
		
		////////////////////////////////////////////////
		for (int z: listeCodesErreur) { 
			System.out.println("last servlet insertion"+z); 
			}
		////////////////////////////////////////////////


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

		String nom;
		String prenom;
		String email;
		String telephone;
		String rue;
		String codePostal;
		String ville;
		String motDePasse;
		String nouveaump;
		String confirmation;
		
				
		//controle session
		pseudo = (String) session.getAttribute("pseudoSession");
			//////////////////////////////////////////////
			System.out.println("start pseudo="+pseudo);
			///////////////////////////////////////
		if(pseudo==null || pseudo.trim().equals("")){ 
			  listeCodesErreur.add(30010); 
			////////////////////////////////////////////
			for (int z: listeCodesErreur) {
				System.out.println("controle !session"+z);
			}
			/////////////////////////////////////////////
		}
		
		
		if(listeCodesErreur.size()>0) {
			request.setAttribute("listeCodesErreur",listeCodesErreur);
		}
	
		else {
			///////////////////////
			System.out.println("mode enregistrer");

			//chemin/enregistrer
			// construction utilsateur et utilisateurmaj
			//construction utilisateur
			try {
				utilisateur=  utilisateurManager.selectionnerUtilisateur(pseudo);
				////////////////////////////////////////////////
				System.out.println("recup attributs utilisateur"+utilisateur.getPseudo());
				////////////////////////////////////////////////
	
				//construction utilisateurmaj
				//recuperation des paramètres du formulaire
				pseudo = request.getParameter("pseudo");
				nom = request.getParameter("nom");
				prenom = request.getParameter("prenom"); 
				email = request.getParameter("email");
				telephone = request.getParameter("telephone");
				rue = request.getParameter("rue");
				codePostal = request.getParameter("codePostal");
				ville = request.getParameter("ville");
				motDePasse = request.getParameter("motDePasse");
				nouveaump = request.getParameter("nouveaump");
				confirmation = request.getParameter("confirmation");
				
				utilisateurMaj= new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);

			
				
				/////////////////////////////////////////////
				System.out.println(request.getParameter("pseudo"));
				System.out.println(utilisateur.getPseudo());
				if(utilisateur.getPseudo().trim().equalsIgnoreCase(utilisateurMaj.getPseudo().trim())) {
				System.out.println("=");
				}
				else {
					System.out.println("!=");
				}
				System.out.println(telephone);
				///////////////////////////////////////
				
			
				
				
				
				
				
				if(
						utilisateur.getPseudo().trim().equalsIgnoreCase(utilisateurMaj.getPseudo().trim()) &
						utilisateur.getNom().trim().equalsIgnoreCase(utilisateurMaj.getNom().trim()) &
						utilisateur.getPrenom().trim().equalsIgnoreCase(utilisateurMaj.getPrenom().trim()) &
						utilisateur.getEmail().trim().equalsIgnoreCase(utilisateurMaj.getEmail().trim()) &
						utilisateur.getTelephone().trim().equalsIgnoreCase(utilisateurMaj.getTelephone().trim()) &
						utilisateur.getRue().trim().equalsIgnoreCase(utilisateurMaj.getRue().trim()) &
						utilisateur.getCodePostal().trim().equalsIgnoreCase(utilisateurMaj.getCodePostal().trim()) &
						utilisateur.getVille().trim().equalsIgnoreCase(utilisateurMaj.getVille().trim()) &
						utilisateur.getMotDePasse().trim().equalsIgnoreCase(utilisateurMaj.getMotDePasse().trim()) 
					){
					  	listeCodesErreur.add(10012); 
						////////////////////////////////////////////////
						System.out.println("servErreurAucunChangement");
						////////////////////////////////////////////////
					}
				
				
				//verif mp //verif mp 
				if (!nouveaump.trim().equals(confirmation)) {
					listeCodesErreur.add(10015);
					////////////////
					System.out.println("erreurmp");
					//////////////
				}
			
				if(listeCodesErreur.size()>0) {
					request.setAttribute("listeCodesErreur",listeCodesErreur);
				}
			
				else {
					System.out.println("servletmngMajUtil");
					utilisateurManager.MAJUtilisateur(utilisateur, utilisateurMaj);
					
					/////////////////////////////////////////////
					session.setAttribute("pseudoSession", utilisateurMaj.getPseudo());
					//////////////////////////////////////////
				
				} 
			} catch (BusinessException e) {
				///////////////////////////
				System.out.println("catch");

				e.printStackTrace();
				request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			}
		}
		


		////////////////////////////////////////////
		System.out.println("endservlet ");
		for (int z: listeCodesErreur) {
			System.out.println("e "+z);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modifierProfil.jsp");
		rd.forward(request, response);
	}
}
