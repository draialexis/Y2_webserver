<h2>Ajouter un &eacute;l&egrave;ve</h2>
<fieldset>
    <legend>nouvel &eacute;l&egrave;ve</legend>
    <form method="POST" action="/hidden/students">
        <label for="lastname">nom</label>
        <br>
        <input type="text" id="lastname" name="lastname" maxlength="50" required>
        <br>
        <label for="firstname">pr&eacute;nom</label>
        <br>
        <input type="text" id="firstname" name="firstname" maxlength="50" required>
        <br>
        <button type="submit">Enregistrer</button>
    </form>
</fieldset>