<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="fr.eni.enienchere.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<!-- head -->
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>	

<body>
<section id = "index">
<!-- header -->
<jsp:include page="/WEB-INF/fragments/header.jsp"></jsp:include>	



	<h1>Liste des enchères</h1>

	<div class="formRecherche">
		<h2>Filtres :</h2>
		<form action="" method="post">
			<input type="search" id="site-search" name="q" aria-label="Search through site content">
		
			<label for="cars">Catégories:</label>

			<select name="cars" id="cars">
  				<option value="">cat1</option>
  				<option value="">cat2</option>
  				<option value="">cat3</option>
  				<option value="">cat4</option>
			</select>
			<button>Rechercher</button>
		</form>
	</div>
	<div class="ListeEncheresEnCours">
		<h2>Enchères en cours :</h2>
		<div>
			<article>
				<h3>article1</h3>
				<img>
				<ul>
					<li>Prix :</li>
					<li>Fin de l'enchère :</li>
					<li>vendeur : <a href="${pageContext.request.contextPath}/afficherProfil?pseudo=${u.pseudo}">${u.pseudo}</a>pseudo<br>
					</li>
				</ul>
			</article>
		
			<article>article2</article>
		</div>
	</div>


</section>
</body>
</html>