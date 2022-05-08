<#--TODO reuse that in other contexts
I can't believe I made this for nothing :( -->

<#if isAuthorized && award?has_content>
    <h2>Modifier une attribution</h2>
    <fieldset>
        <legend>attribution</legend>
        <form method="POST" action="/awards/${award.getId()}">
            <label for="student-id">&eacute;l&egrave;ve</label>
            <br>
            <select name="student-id" id="student-id" required>
                <option value="${award.getStudent().getId()}">
                    ACTUEL : ${award.getStudent().getId()}
                    - ${award.getStudent().getFirstName()} ${award.getStudent().getLastName()}
                </option>
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
                <option value="${award.getSticker().getId()}">
                    ACTUEL : ${award.getSticker().getId()}
                    - ${award.getSticker().getColor().name()} ${award.getDescription().name()}
                </option>
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
            <textarea name="motive" id="motive" cols="30" rows="10">award.getMotive()</textarea>
            <br>
            <button type="submit">Enregistrer</button>
        </form>
    </fieldset>
</#if>