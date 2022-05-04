<#ftl encoding="utf-8">

<body xmlns="http://www.w3.org/1999/html">
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
    <input type="password" id="userpwd" name="userpwd" required>
    <br>

    <button type="submit">Enregistrer</button>
</form>

</body>

</html>
