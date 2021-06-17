package fr.eni.enienchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.enienchere.bll.BusinessException;
import fr.eni.enienchere.bo.Utilisateur;


public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String SELECT_UTILISATEUR_BY_PSEUDO = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE pseudo=?;";
	
	
	@Override
	public Utilisateur selectUtilisateurByPseudo(String pseudo) throws BusinessException {
		Utilisateur utilisateur = new Utilisateur();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_UTILISATEUR_BY_PSEUDO);
			pstmt.setString(1, pseudo);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo (rs.getString("pseudo"));
				utilisateur.setNom (rs.getString("nom"));
				utilisateur.setPrenom (rs.getString("prenom"));
				utilisateur.setEmail (rs.getString("email"));
				utilisateur.setTelephone (rs.getString("telephone"));
				utilisateur.setRue (rs.getString("rue"));
				utilisateur.setCodePostal (rs.getString("code_postal"));
				utilisateur.setVille (rs.getString("ville"));
				utilisateur.setMotDePasse (rs.getString("mot_de_passe"));
				utilisateur.setCredit (rs.getInt("credit"));
				utilisateur.setAdministrateur (rs.getBoolean("administrateur"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(10010);
			throw businessException;
		}
		if(utilisateur.getPseudo()==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(10011);
			throw businessException;
		}

		return utilisateur;
	}
}