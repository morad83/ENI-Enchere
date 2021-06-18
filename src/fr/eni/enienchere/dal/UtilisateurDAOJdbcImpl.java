package fr.eni.enienchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enienchere.bll.BusinessException;
import fr.eni.enienchere.bo.Utilisateur;


public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String SELECT_UTILISATEUR_BY_PSEUDO = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit FROM UTILISATEURS WHERE pseudo=?";
	
	private static final String SELECT_ALL_PSEUDO_UTILISATEUR ="select pseudo FROM UTILISATEURS";
	
	private static final String UPDATE_UTILISATEUR="update UTILISATEURS set pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?, credit=? where pseudo=?";
	
	
	@Override
	//NE retourne pas le noUtilisateur et l'admin
	public Utilisateur selectUtilisateurByPseudo(String pseudo) throws BusinessException {
		Utilisateur utilisateur = new Utilisateur();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_UTILISATEUR_BY_PSEUDO);
			pstmt.setString(1, pseudo);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
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
	
	
	@Override
	public List<String> selectAllPseudo() throws BusinessException{
		List<String> listePseudos= new ArrayList<String>();
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_PSEUDO_UTILISATEUR);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				listePseudos.add(rs.getString("pseudo"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(10012);
			throw businessException;
		}
		return listePseudos;
	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateurMaj, String pseudo) throws BusinessException{
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_UTILISATEUR);

			pstmt.setString(1, utilisateurMaj.getPseudo());
			pstmt.setString(2, utilisateurMaj.getNom());
			pstmt.setString(3, utilisateurMaj.getPrenom());
			pstmt.setString(4, utilisateurMaj.getEmail());
			pstmt.setString(5, utilisateurMaj.getTelephone());
			pstmt.setString(6, utilisateurMaj.getRue());
			pstmt.setString(7, utilisateurMaj.getCodePostal());
			pstmt.setString(8, utilisateurMaj.getVille());
			pstmt.setString(9, utilisateurMaj.getMotDePasse());
			pstmt.setInt(10, utilisateurMaj.getCredit());
			pstmt.setString(11, pseudo);


			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(10013);
			throw businessException;
		}
	}

}