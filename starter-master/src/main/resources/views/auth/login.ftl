<#ftl encoding="utf-8">
<html lang="fr">
<#include "../bits/head.ftl">
<body xmlns="http://www.w3.org/1999/html">
<#include "../bits/navbar.ftl">
<#include "../bits/status.ftl">

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
    <p>
        En vous identifiant, ce programme va cr&eacute;er des cookies de session sur votre ordinateur. Vous pouvez les
        effacer en vous <a href="/logout">d&eacute;connectant</a>, ou les laisser expirer en une heure (apr&egrave;s
        quoi votre navigateur devrait les purger automatiquement).
    </p>
</form>

</body>

</html>
