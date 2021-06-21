<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Suppression compte</title>
</head>
<body>

	<h1>Suppression du compte</h1>
			
			
		<p>Etes vous sur de vouloir supprimer votre compte?</p>
	
	  			<form action="${pageContext.request.contextPath}/supProfil" method="post">
					<input type="submit" value="Confirmer"/>
				</form>
				
	  			<form action="${pageContext.request.contextPath}/modifierProfil" method="get">
					<input type="submit" value="Annuler"/>
				</form>
</body>
</html>