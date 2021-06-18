<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enienchere.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil</title>
</head>
<body>
	<div>
	
		<h1>ENI-Enchères</h1>
	
	

			<c:choose>
			<c:when test="${!empty listeCodesErreur}">
		
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
			</c:when>
			<c:otherwise>
			
			<div class=profil>
		
				<ul>
					<li><span>Pseudo :</span><span>${utilisateur.pseudo}</span></li>
					<li><span>Nom :</span><span>${utilisateur.nom}</span></li>
					<li><span>Prénom :</span><span>${utilisateur.prenom}</span></li>
					<li><span>Email :</span><span>${utilisateur.email}</span></li>
					<li><span>Teléphone :</span><span>${utilisateur.telephone}</span></li>
					<li><span>Rue :</span><span>${utilisateur.rue}</span></li>
					<li><span>Code Postale :</span><span>${utilisateur.codePostal}</span></li>
					<li><span>Ville :</span><span></span>${utilisateur.ville}</li>
				</ul>
		
				<c:if test="${utilisateur.pseudo == sessionScope.pseudoSession}">
				<a href="${pageContext.request.contextPath}/modifierProfil">modifier</a>
				</c:if>
				<a href="${pageContext.request.contextPath}/TestAfficherProfilServlet">Retour à l'accueil</a>
				
		
			</div>
			</c:otherwise>
			</c:choose>
	</div>
</body>
</html>