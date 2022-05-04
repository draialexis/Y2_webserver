<#ftl encoding="utf-8">
<html lang="fr">

<body xmlns="http://www.w3.org/1999/html">

<a href="/">&larr; Index</a>

<p>
    <#if status?has_content>
        ${status} :
        <#if user?has_content>
            ${user.firstName} ${user.lastName} (${user.userName})
        </#if>
    </#if>
</p>

<form method="POST" action="/signup">
    <label for="firstname">pr&eacute;nom</label>
    <br>
    <input type="text" id="firstname" name="firstname" required>
    <br>
    <label for="lastname">nom</label>
    <br>
    <input type="text" id="lastname" name="lastname" required>
    <br>
    <label for="username">nom d'utilisateur</label>
    <br>
    <input type="text" id="username" name="username" required>
    <br>
    <label for="userpwd">mot de passe</label>
    <br>
    <input type="password" id="userpwd" name="userpwd" pattern="[a-zA-Z0-9!@#$%^&*_=+-]{4, 16}"
           title="entre 4 et 16 caract&egrave;res quelconques" required>
    <br>

    <button type="submit">Enregistrer</button>
</form>

</body>

</html>