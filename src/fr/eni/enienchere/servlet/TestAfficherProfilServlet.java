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
 * Servlet implementation class TestListes
 */
@WebServlet("/TestAfficherProfilServlet")
public class TestAfficherProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestAfficherProfilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//definition pseudoSession test
		HttpSession session = request.getSession();
		session.setAttribute("pseudoSession", "mo");
		session.setMaxInactiveInterval(50);
		//
		
		List<Utilisateur> listeUtilisateurs= new ArrayList<>();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		try {
			// definition de lien pseudo test
			Utilisateur utilisateur1 = utilisateurManager.selectionnerUtilisateur("jo");
			Utilisateur utilisateur2 = utilisateurManager.selectionnerUtilisateur("mo");
			listeUtilisateurs.add(utilisateur1);
			listeUtilisateurs.add(utilisateur2);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		request.setAttribute("listeUtilisateurs", listeUtilisateurs);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/testAfficherProfilServlet.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
