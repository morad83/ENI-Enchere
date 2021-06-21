<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="fr.eni.enienchere.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${empty sessionScope.pseudoSession}">
	<a href="${pageContext.request.contextPath}/seConnecter">S'inscrire - Se connecter</a><br>
	</c:if>
	<c:if test="${!empty sessionScope.pseudoSession}">
	<a href="${pageContext.request.contextPath}/encheres">Enchères</a>
	<a href="${pageContext.request.contextPath}/vendre">Vendre un article</a>
	<a href="${pageContext.request.contextPath}/monProfil">Mon profil</a>
	<a href="${pageContext.request.contextPath}/deconnexion">Déconnexion</a>
	
	</c:if>
	
<!-- lien pseudo -->

<c:forEach var="u" items="${listeUtilisateurs}">
<a href="${pageContext.request.contextPath}/afficherProfil?pseudo=${u.pseudo}">${u.pseudo}</a><br>
</c:forEach>

</body>
</html>