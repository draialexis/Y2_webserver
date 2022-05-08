<#ftl encoding="utf-8">
<html lang="fr">

<body xmlns="http://www.w3.org/1999/html">

<a href="/students">&larr; El&egrave;ves</a>

<form method="POST" action="/students/modif/${pa}">
    <label for="lastname">nom d'El&egrave;ves</label>
    <br>
    <input type="text" id="lastname" name="lastname" required>
    <br>
    <label for="firstName">Prenom  d'El&egrave;ves</label>
    <br>
    <input type="text" id="firstName" name="firstName" required>
    <br>
    <button type="submit">Continuer </button>
</form>

</body>

</html>
