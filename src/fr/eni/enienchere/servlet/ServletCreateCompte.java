package fr.eni.enienchere.servlet;

import java.util.List;
import java.sql.SQLException;
import fr.eni.enienchere.BusinessException;
import fr.eni.enienchere.bo.Utilisateur;
import fr.eni.enienchere.bll.UtilisateurManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/createCompte" })
public class ServletCreateCompte extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    protected void doGet(final HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/createCompte.jsp");
        rd.forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/createCompte.jsp");
        Utilisateur user = null;
        try {
            String pseudo = request.getParameter("pseudo");
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String telephone = request.getParameter("tel");
            String rue = request.getParameter("rue");
            String codePostal = request.getParameter("cp");
            String ville = request.getParameter("ville");
            String motDePasse = request.getParameter("mdp");
            String confirmation = request.getParameter("mdpConf");
            
            List<String> allPseudos = (List<String>)UtilisateurManager.listAllPseudos();
            System.out.println(allPseudos);
            if (allPseudos.contains(pseudo)) {
                request.setAttribute("allPseudos", allPseudos);
                request.setAttribute("pseudo", pseudo);
                rd.forward(request, response);
            }
            else if (confirmation.equals(motDePasse)) {
                user = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
                user = UtilisateurManager.inscriptionUtilisateur(user);

            } 
            else if (!confirmation.equals(motDePasse) && (confirmation != null)){
            	System.out.println(motDePasse);
            	System.out.println(confirmation);
                request.setAttribute("confirmation", confirmation);
                request.setAttribute("motDePasse", motDePasse);
                rd.forward(request, response);
            }
        }
        catch (BusinessException | SQLException e) {
            e.printStackTrace();
        }
    }
}