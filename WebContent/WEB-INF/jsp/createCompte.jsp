<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="fr.eni.enienchere.messages.LecteurMessage" %>
<%@ page import="fr.eni.enienchere.servlets.ServletCreateCompte" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Inscription</title>
	</head>
	<body>
		
		<!-- navbar -->
		<nav class="navbar">
			<div>
				<a href="<%=request.getContextPath() %>/accueil"> ENI-Enchères</a>
			</div>
		
		<!-- head -->
		<div class="head">
			<h1>Mon profil</h1>
		</div>
		
		<!-- Formulaire inscription -->
		<section>
			<form action="<%=request.getContextPath()%>/createCompte" method="post">
				<div class="input-field">
					<label for="pseudo">Pseudo</label>
					<input class="input" name="pseudo" id="pseudo" required>
					<c:if test="${ allPseudos.contains(pseudo) }">
						<p>pseudo déjà utilisé, merci dans saisir un autre</p>
					</c:if>
				</div>
				<div class="input-field">
					<label for="nom">Nom</label>
					<input class="input" name="nom" id="nom" required>
				</div>
				<div class="input-field">
					<label for="prenom">Prénom</label>
					<input class="input" name="prenom" id="prenom" required>
				</div>
				<div class="input-field">
					<label for="email">Email</label>
					<input class="input" name="email" id="email" type="email" required>
				</div>
				<div class="input-field">
					<label for="tel">Téléphone</label>
					<input class="input" name="tel" id="tel" type="tel" maxlength= "10"  minlength= "10">
				</div>
				<div class="input-field">
					<label for="rue">Rue</label>
					<input class="input" name="rue" id="rue" type="text" required>
				</div>
				<div class="input-field">
					<label for="cp">Code Postal</label>
					<input class="input" name="cp" id="cp" type="text" maxlength= "5"  minlength= "5" required>
				</div>
				<div class="input-field">
					<label for="ville">Ville</label>
					<input class="input" name="ville" id="ville" type="text" required>
				</div>
				<div class="input-field">
         			<label for="mdp">Mot de passe :</label>
          			<input class="input" name="mdp" id="mdp" type="password" required>
        		</div>
        		<div class="input-field">
          			<label for="mdpConf">Confirmation :</label>
          			<input class="input" name="mdpConf" id="mdpConf" type="password" required>
          			<c:if test="${!confirmation.equals(motDePasse) && (confirmation != null) }">
          				<p>Mot De Passe différent de la confirmation</p>
          			</c:if>
        		</div>
        		
        		<!-- btn validation ou annulation -->
        		<div class="btn-exit">
        			<div>
        				<button class="btn" type="submit">Créer</button>

        			</div>
        			<div>
        				<a href="<%=request.getContextPath() %>/accueil"><button class="btn" type="button">Annuler</button></a>
        			</div>
        		</div>
			</form>
		</section>
		</nav>
	</body>
</html>