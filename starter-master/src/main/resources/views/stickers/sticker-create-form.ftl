<#if colors?has_content && descriptions?has_content>
    <h2>Ajouter une gommette</h2>
    <fieldset>
        <legend>nouvelle gommette</legend>
        <form method="POST" action="/hidden/stickers">
            <label for="color">couleur</label>
            <br>
            <select name="color" id="color" required>
                <#list colors as color>
                    <option value="${color}">
                        ${color}
                    </option>
                </#list>
            </select>
            <label for="description">description</label>
            <br>
            <select name="description" id="description" required>
                <#list descriptions as description>
                    <option value="${description}">
                        ${description}
                    </option>
                </#list>
            </select>
            <br>
            <button type="submit">Enregistrer</button>
        </form>
    </fieldset>
<#else>
    <p class="info-msg">Absence de COLOR et/ou de DESCRIPTION dans le mod&egrave;le</p>
</#if>
