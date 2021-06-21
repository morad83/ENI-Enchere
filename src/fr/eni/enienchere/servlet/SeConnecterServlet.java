package fr.eni.enienchere.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enienchere.bll.BusinessException;
import fr.eni.enienchere.bll.UtilisateurManager;
import fr.eni.enienchere.bo.Utilisateur;


@WebServlet("/seConnecter")
public class SeConnecterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeConnecterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Cookie[] cookies = request.getCookies();
    	
    	
    	//Controle cookie//
    	if (cookies != null) {
    		for (Cookie cookie : cookies) {
    			if (cookie.getName().equals("cookieCoPseudoENITrocenchere")) {
    				request.setAttribute("pseudo", cookie.getValue());
    			}
    			if (cookie.getName().equals("cookieCoMPENITrocenchere")) {
    				request.setAttribute("mp", cookie.getValue());
    			}
    		}
    	}
    	
    	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/seConnecter.jsp");
		rd.forward(request, response);
    }
	
 
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
			
		Utilisateur utilisateurCo;
		
		String pseudoCo;
		String mpCo;
		String souvenir;
	
		//recup para form
		pseudoCo = request.getParameter("pseudoCo").trim();
		mpCo = request.getParameter("mpCo").trim();
		souvenir= request.getParameter("souvenir");
		
		//creation objet utilisateurCo
		utilisateurCo=new Utilisateur(pseudoCo, mpCo);
		
		//mng connexion
		try {
			if(utilisateurManager.connexion(utilisateurCo)) {
				//set session
				session.setAttribute("pseudoSession", utilisateurCo.getPseudo());

				//cookies?
				if(souvenir!=null) {
					Cookie cookiePseudo = new Cookie("cookieCoPseudoENITrocenchere",utilisateurCo.getPseudo()); 
					Cookie cookieMp = new Cookie("cookieCoMPENITrocenchere",utilisateurCo.getMotDePasse());
					cookiePseudo.setMaxAge(60*60*24*7);
					cookieMp.setMaxAge(60*60*24*7);
					response.addCookie(cookiePseudo);
					response.addCookie(cookieMp);
				}
		    	RequestDispatcher rd = request.getRequestDispatcher("/index");
				rd.forward(request, response);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		   	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/seConnecter.jsp");
			rd.forward(request, response);
		}	
	}
}
