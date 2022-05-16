<#ftl encoding="utf-8">
<html lang="fr">
<#include "../bits/head.ftl">
<body xmlns="http://www.w3.org/1999/html">
<#include "../bits/navbar.ftl">
<#include "../bits/status.ftl">

<form method="POST" action="/hidden/teachers">
    <label for="firstname">pr&eacute;nom</label>
    <br>
    <input type="text" id="firstname" name="firstname" maxlength="50" required>
    <br>

    <label for="lastname">nom</label>
    <br>
    <input type="text" id="lastname" name="lastname" maxlength="50" required>
    <br>

    <label for="username">nom d'utilisateur</label>
    <br>
    <input type="text" id="username" name="username" maxlength="50" required>
    <br>

    <label for="userpwd">mot de passe</label>
    <br>
    <input type="password" id="userpwd" name="userpwd" pattern="[a-zA-Z0-9!@#$%^&*_=+-]{4, 16}"
           title="entre 4 et 16 caract&egrave;res quelconques" minlength="4" maxlength="16" required>
    <br>

    <label for="userpwd-validation">mot de passe (confirmation)</label>
    <br>
    <input type="password" id="userpwd-validation" name="userpwd-validation" pattern="[a-zA-Z0-9!@#$%^&*_=+-]{4, 16}"
           title="entre 4 et 16 caract&egrave;res quelconques" minlength="4" maxlength="16" required>
    <br>

    <button type="submit">Enregistrer</button>
</form>

</body>

</html>
