<#if colors?has_content && descriptions?has_content && stickers?has_content && sticker?has_content>
    <h2>Modifier une gommette</h2>
    <fieldset>
        <legend>gommette</legend>
        <form method="POST" action="/stickers/${sticker.getId()}">
            <label for="sticker-id">gommette</label>
            <br>
            <select name="sticker-id" id="sticker-id" required>
                <option value="${sticker.getId()}">
                    ACTUEL : ${sticker.getId()}
                    (${sticker.getColor().name()} : ${sticker.getDescription().name()})
                </option>
                <#list stickers as otherSticker>
                    <option value="${otherSticker.getId()}">
                        ${otherSticker.getId()}
                        (${otherSticker.getColor().name()} : ${otherSticker.getDescription().name()})
                    </option>
                </#list>
            </select>
            <label for="color">couleur</label>
            <br>
            <select name="color" id="color" required>
                <option value="${sticker.getColor().name()}">
                    ACTUEL : ${sticker.getColor().name()}
                </option>
                <#list colors as color>
                    <option value="${color}">
                        ${color}
                    </option>
                </#list>
            </select>
            <label for="description">description</label>
            <br>
            <select name="description" id="description" required>
                <option value="${sticker.getDescription().name()}">
                    ACTUEL : ${sticker.getDescription().name()}
                </option>
                <#list descriptions as description>
                    <option value="${description}">
                        ${description}
                    </option>
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
