package fr.eni.enienchere.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import fr.eni.enienchere.bll.BusinessException;
import fr.eni.enienchere.bll.UtilisateurManager;
import fr.eni.enienchere.bo.Utilisateur;

////////////////////////////////
/*Revoir l'exception lorsque la session a exiré et qu'on clik sur "mon profil"

lien pseudo
<a href="${pageContext.request.contextPath}/afficherProfil?pseudo=${u.pseudo}">${u.pseudo}</a><br>


<!-- lien mon profil -->
<a href="${pageContext.request.contextPath}/monProfil">Mon profil</a>
*/
//////////////////////////////////////////

/**
 * Servlet implementation class AfficherProfil
 */
@WebServlet(urlPatterns= {
		"/monProfil",
		"/afficherProfil"})
public class AfficherProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	HttpSession session = request.getSession();
		
	UtilisateurManager utilisateurManager = new UtilisateurManager();
		
	List<Integer> listeCodesErreur=new ArrayList<>();
	
	String pseudo=null;
	

		////////////////////////////////////////////
	System.out.println("start");
	for (int z: listeCodesErreur) {
		System.out.println("start"+z);
		}
		/////////////////////////////////////////////
	
	
		if(request.getServletPath().equals("/monProfil")) {
			
			
			//////////////////////////////////////////////
				System.out.println("monprofil");
				///////////////////////////////////////
				
			pseudo = (String) session.getAttribute("pseudoSession");
			
			
			//////////////////////////////////////////////
				System.out.println("pseudo="+pseudo);
				///////////////////////////////////////
				
			if(pseudo==null || pseudo.trim().equals("")){ 
				  listeCodesErreur.add(30010); 
			} 
		}
	
	

		////////////////////////////////////////////////
		for (int e: listeCodesErreur) {
		System.out.println("monprofil"+e);
		}
		////////////////////////////////////////////////
		
		
		
		if(request.getServletPath().equals("/afficherProfil")) {
			//recuperation du pseudo associé au lien
			pseudo = request.getParameter("pseudo");

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
				System.out.println("insertion");
				System.out.println(utilisateur.getPseudo());
				
				////////////////////////////////////////////////
				
				
				
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			
			}
		}
	
		
		
		////////////////////////////////////////////////
		
		  for (int z: listeCodesErreur) { System.out.println("last servlet insertion"+z); }
		 
		////////////////////////////////////////////////
	
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AfficherProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
