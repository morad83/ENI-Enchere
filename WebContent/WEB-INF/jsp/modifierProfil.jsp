<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enienchere.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mon profil</title>
</head>
<body>


<h1>Mon profil</h1>

	
			<c:if test="${!empty listeCodesErreur}">
		
			<div class="erreur">
				<div>
		  			<strong>Erreur!</strong>
		  			
		  			<ul>
		  				<c:forEach var="message" items="${listeCodesErreur}">
		  				<li>${LecteurMessage.getMessageErreur(message)}</li>
		  				</c:forEach>
		  			</ul>
				</div>
			
				<a href="${pageContext.request.contextPath}/TestAfficherProfilServlet">Retour à l'accueil</a>
		
			</div>
			</c:if>
	

			<div class=profil>
		
				
				<form action="${pageContext.request.contextPath}/modifierProfil" method="post">
					<label for="pseudo">Pseudo :</label>
  					<input type="text" id="pseudo" name="pseudo" value="${utilisateur.pseudo}" maxlength="30"><br>
  					
  					<label for="nom">Nom :</label>
  					<input type="text" id="nom" name="nom" value="${utilisateur.nom}" maxlength="30"><br>
  					
  					<label for="prenom">Prénom :</label>
  					<input type="text" id="prenom" name="prenom" value="${utilisateur.prenom}" maxlength="30"><br>
  					
  					<label for="email">Email :</label>
  					<input type="email" id="email" name="email" value="${utilisateur.email}" maxlength="20"><br>
  					
  					<label for="telephone">Téléphone :</label>
  					<input type="tel" id="telephone" name="telephone" value="${utilisateur.telephone}" minlength="10" maxlength="15"><br>
  					
  					<label for="rue">Rue :</label>
  					<input type="text" id="rue" name="rue" value="${utilisateur.rue}" maxlength="30"><br>
  					
  					<label for="codePostal">Code postal :</label>
  					<input type="text" id="codePostal" name="codePostal" value="${utilisateur.codePostal}" minlength="5" maxlength="10"><br>
  					
  					<label for="ville">Ville :</label>
  					<input type="text" id="ville" name="ville" value="${utilisateur.ville}" maxlength="30"><br>
  					
  					<label for="motDePasse">Mot de passe actuel :</label>
  					<input type="password" id="motDePasse" name="motDePasse" value="${utilisateur.motDePasse}" maxlength="30" readonly><br>
  					
  					<label for="nouveaump">Nouveau mot de passe :</label>
  					<input type="password" id="nouveaump" name="nouveaump" value=""><br>
  					
  					<label for="confirmation">Confirmation :</label>
  					<input type="password" id="confirmation" name="confirmation" value=""><br>
  					
  					<label for="credit">Crédit :</label>
  					<input type="text" id="credit" name="credit" value="${utilisateur.credit}" readonly><br>
  					
  					<input type="submit" value="Enregistrer">
  					
  					<input type="button" value="Supprimer mon compte">
				</form>
					<a href="${pageContext.request.contextPath}/TestAfficherProfilServlet">Retour à l'accueil</a>
			</div>
			
</body>
</html>