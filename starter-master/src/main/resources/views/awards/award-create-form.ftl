<#if isAuthorized>
    <h2>Attribuer une gommette</h2>
    <fieldset>
        <legend>nouvelle attribution</legend>
        <form method="POST" action="/hidden/awards">
            <label for="student-id">&eacute;l&egrave;ve</label>
            <br>
            <select name="student-id" id="student-id" required>
                <option value="">--</option>
                <#if students?has_content>
                    <#list students as student>
                        <option value="${student.getId()}">
                            ${student.getId()} - ${student.getFirstName()} ${student.getLastName()}
                        </option>
                    </#list>
                </#if>
            </select>
            <br>
            <label for="sticker-id">gommette</label>
            <br>
            <select name="sticker-id" id="sticker-id" required>
                <option value="">--</option>
                <#if stickers?has_content>
                    <#list stickers as sticker>
                        <option value="${sticker.getId()}">
                            ${sticker.getId()} - ${sticker.getColor().name()}
                            (${sticker.getDescription().name()})
                        </option>
                    </#list>
                </#if>
            </select>
            <br>
            <label for="motive">motif</label>
            <br>
            <textarea name="motive" id="motive" cols="30" rows="10"></textarea>
            <br>
            <button type="submit">Enregistrer</button>
        </form>
    </fieldset>
</#if>