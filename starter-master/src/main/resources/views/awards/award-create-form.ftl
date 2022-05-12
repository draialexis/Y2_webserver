<#if students?has_content && stickers?has_content>
    <h2>Attribuer une gommette</h2>
    <fieldset>
        <legend>nouvelle attribution</legend>
        <form method="POST" action="/hidden/awards">
            <label for="student-id">&eacute;l&egrave;ve</label>
            <br>
            <select name="student-id" id="student-id" required>
                <option value="">--</option>
                <#list students as student>
                    <option value="${student.getId()}">
                        ${student.getId()} - ${student.getFirstName()} ${student.getLastName()}
                    </option>
                </#list>
            </select>
            <br>

            <label for="sticker-id">gommette</label>
            <br>
            <select name="sticker-id" id="sticker-id" required>
                <option value="">--</option>
                <#list stickers as sticker>
                    <option value="${sticker.getId()}">
                        ${sticker.getId()} - ${sticker.getColor().name()}
                        (${sticker.getDescription().name()})
                    </option>
                </#list>
            </select>
            <br>

            <label for="motive">motif</label>
            <br>
            <textarea name="motive" id="motive" cols="30" rows="10" required></textarea>
            <br>

            <button type="submit">Enregistrer</button>
        </form>
    </fieldset>
<#else>
    <p class="info-msg">Absence d'&eacute;l&egrave;ves et/ou de gommettes dans le mod&egrave;le</p>
</#if>