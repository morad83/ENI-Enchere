<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="fr.eni.enienchere.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<!-- head -->
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>	

<body>
<section id ="seConnecter">

<!-- header -->
<jsp:include page="/WEB-INF/fragments/header.jsp"></jsp:include>	
	<h1>Se Connecter</h1>
	
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
			</div>
		</c:if>

	<div class="form">
		<form action="${pageContext.request.contextPath}/seConnecter" method="post">
			<label for="pseudoCo">Pseudo :</label>
  			<input type="text" id="pseudoCo" name="pseudoCo" value="<c:if test="${!empty cookie.cookieCoPseudoENITrocenchere.value}">${cookie.cookieCoPseudoENITrocenchere.value}</c:if>" maxlength="30" required><br>
			<label for="mpCo">Mot de passe :</label>
  			<input type="password" id="mpCo" name="mpCo" value="<c:if test="${!empty cookie.cookieCoMPENITrocenchere.value}">${cookie.cookieCoMPENITrocenchere.value}</c:if>" maxlength="30" required><br>
  					
  			<input type="submit" value="Connexion"/>
  					
  			<label for="souvenir">Se souvenir de moi :</label>
  			<input type="checkbox" id="souvenir" name="souvenir" value="souvenir"/><br>
  					
  			<a href="">Mot de passe oublié</a><br>
  		</form>
  	
  		<button><a href="${pageContext.request.contextPath}/createCompte">Créer un compte</a></button>
		<button><a href="${pageContext.request.contextPath}/index">Annuler</a></button>
	</div>
</section>
</body>
</html>