<#ftl encoding="utf-8">
<html lang="fr">

<body xmlns="http://www.w3.org/1999/html">

<a href="/">&larr; Index</a>

<#if status?has_content>
    <p>
        ${status}
    </p>
</#if>

<form method="POST" action="/login">
    <label for="username">nom d'utilisateur</label>
    <br>
    <input type="text" id="username" name="username" required>
    <br>
    <label for="userpwd">mot de passe</label>
    <br>
    <input type="password" id="userpwd" name="userpwd" required>
    <br>

    <button type="submit">S'identifier</button>
</form>

</body>

</html>
