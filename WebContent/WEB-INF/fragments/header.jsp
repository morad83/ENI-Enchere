<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
	<header>
	<h1>ENI-Enchère</h1>
	
	<div class="nav">
	<c:if test="${empty sessionScope.pseudoSession}">
	<a href="${pageContext.request.contextPath}/seConnecter">S'inscrire - Se connecter</a><br>
	</c:if>
	<c:if test="${!empty sessionScope.pseudoSession}">
	<a href="${pageContext.request.contextPath}/encheres">Enchères</a>
	<a href="${pageContext.request.contextPath}/vendre">Vendre un article</a>
	<a href="${pageContext.request.contextPath}/monProfil">Mon profil</a>
	<a href="${pageContext.request.contextPath}/deconnexion">Déconnexion</a>
	</c:if>
	</div>
	</header>