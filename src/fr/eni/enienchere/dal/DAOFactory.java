package fr.eni.enienchere.dal;

public abstract class DAOFactory {
	
	public static ENIEnchereDAO getENIEnchereDAO()
	{
		return new ENIEnchereDAOJdbcImpl();
	}
}
	