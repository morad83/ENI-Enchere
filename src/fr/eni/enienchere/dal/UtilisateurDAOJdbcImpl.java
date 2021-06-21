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

    private static final String INSERT = "INSERT INTO UTILISATEURS VALUES (?,?,?,?,?,?,?,?,?,?,?);";
    
    private static final String GETALLPSEUDOS = "SELECT pseudo FROM UTILISATEURS;";
	
    private static final String SELECT_UTILISATEUR_BY_PSEUDO = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit FROM UTILISATEURS WHERE pseudo=?";
	
	private static final String SELECT_ALL_PSEUDO_UTILISATEUR ="select pseudo FROM UTILISATEURS";
	
	private static final String UPDATE_UTILISATEUR="update UTILISATEURS set pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?, credit=? where pseudo=?";
	
	private static final String SELECT_IDS_UTILISATEUR_BY_PSEUDO = "select pseudo, mot_de_passe from UTILISATEURS where pseudo=?";

	private static final String GETALLEMAIL = "SELECT email FROM UTILISATEURS;";
	
	private static final String DELETE = "DELETE UTILISATEURS WHERE pseudo=?;";

	
	 @Override
	    public Utilisateur insert(Utilisateur utilisateur) throws BusinessException, SQLException {
	            try (Connection cnx = ConnectionProvider.getConnection()) {
	            	
	                    PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
	                    pstmt.setString(1, utilisateur.getPseudo());
	                    pstmt.setString(2, utilisateur.getNom());
	                    pstmt.setString(3, utilisateur.getPrenom());
	                    pstmt.setString(4, utilisateur.getEmail());
	                    pstmt.setString(5, utilisateur.getTelephone());
	                    pstmt.setString(6, utilisateur.getRue());
	                    pstmt.setString(7, utilisateur.getCodePostal());
	                    pstmt.setString(8, utilisateur.getVille());
	                    pstmt.setString(9, utilisateur.getMotDePasse());
	                    pstmt.setInt(10, utilisateur.getCredit());
	                    pstmt.setBoolean(11, utilisateur.isAdministrateur());
	                    pstmt.executeUpdate();
	                    ResultSet rs = pstmt.getGeneratedKeys();
	                    
	                    if (rs.next()) {
	                        utilisateur.setNoUtilisateur(rs.getInt(1));
	                    }
	                    pstmt.close();
	                    cnx.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	            final BusinessException businessException = new BusinessException();
	            businessException.ajouterErreur(Integer.valueOf(10001));
	            throw businessException;
	        }
	        return utilisateur;
	    }
	    
	    public List<String> getAllPseudos() throws BusinessException {
	        List<String> listPseudos = new ArrayList<String>();
	            try (Connection cnx = ConnectionProvider.getConnection()) {
	            	PreparedStatement pstmt = cnx.prepareStatement(GETALLPSEUDOS);
	            	ResultSet rs = pstmt.executeQuery();
	                    while (rs.next()) {
	                        listPseudos.add(rs.getString("pseudo"));
	                    } 
	            } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return listPseudos;
	    }
	    
	    public List<String> getAllEmail() throws BusinessException {
	    	List<String> listEmail = new ArrayList<String>();
	    		try (Connection cnx = ConnectionProvider.getConnection()) {
	    			PreparedStatement pstmt = cnx.prepareStatement(GETALLEMAIL);
	    			ResultSet rs = pstmt.executeQuery();
	    				while (rs.next()) {
	    					listEmail.add(rs.getString("email"));
	    				}
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
	    		return listEmail;
	    }

	@Override
	public void delete (String pseudo) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setString(1, pseudo);
			pstmt.executeUpdate();
		} catch (Exception e) {
            e.printStackTrace();
            final BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(Integer.valueOf(10002));
            throw businessException;			
		}
	}
	
	
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
	
	@Override
    public Utilisateur selectIdsUtilisateurByPseudo(String pseudoCo) throws BusinessException {
		Utilisateur utilisateurCoBd= new Utilisateur();
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_IDS_UTILISATEUR_BY_PSEUDO);
			pstmt.setString(1, pseudoCo);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				utilisateurCoBd.setPseudo(rs.getString("pseudo"));
				utilisateurCoBd.setMotDePasse(rs.getString("mot_de_passe"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(10014);
			throw businessException;
		}
		return utilisateurCoBd;
	}


}