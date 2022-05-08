<h2>Ajouter un &eacute;l&egrave;ve</h2>
<fieldset>
    <legend>nouvel &eacute;l&egrave;ve</legend>
    <form method="POST" action="/students">
        <label for="lastname">nom</label>
        <br>
        <input type="text" id="lastname" name="lastname" required>
        <br>
        <label for="firstname">pr&eacute;nom</label>
        <br>
        <input type="text" id="firstname" name="firstname" required>
        <br>
        <button type="submit">Enregistrer</button>
    </form>
</fieldset>