<#if colors?has_content && descriptions?has_content && stickers?has_content && sticker?has_content>
    <h2>Modifier une gommette</h2>
    <fieldset>
        <legend>gommette</legend>
        <form method="POST" action="/hidden/stickers/${sticker.getId()}">
            <label for="color">couleur</label>
            <br>
            <select name="color" id="color" required>
                <option value="${sticker.getColor().name()}">
                    ACTUEL : ${sticker.getColor().name()}
                </option>
                <#list colors as color>
                    <#if color != sticker.getColor().name()>
                        <option value="${color}">
                            ${color}
                        </option>
                    </#if>
                </#list>
            </select>
            <br>
            <label for="description">description</label>
            <br>
            <select name="description" id="description" required>
                <option value="${sticker.getDescription().name()}">
                    ACTUEL : ${sticker.getDescription().name()}
                </option>
                <#list descriptions as description>
                    <#if description != sticker.getDescription().name()>
                        <option value="${description}">
                            ${description}
                        </option>
                    </#if>
                </#list>
            </select>
            <br>
            <button type="submit">Modifier</button>
        </form>
    </fieldset>
<#else>
    <p class="info-msg">
        ID de gommette mal lu, ou absence de COLOR et/ou de DESCRIPTION et/ou de gommettes dans le mod&egrave;le
    </p>
</#if>
