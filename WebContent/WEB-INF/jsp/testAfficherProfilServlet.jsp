<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<!-- lien mon profil -->
<a href="${pageContext.request.contextPath}/monProfil">Mon profil</a>


<!-- lien pseudo -->

<c:forEach var="u" items="${listeUtilisateurs}">
<a href="${pageContext.request.contextPath}/afficherProfil?pseudo=${u.pseudo}">${u.pseudo}</a><br>
</c:forEach>

</body>
</html>