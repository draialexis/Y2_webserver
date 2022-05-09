<#ftl encoding="utf-8">

<html lang="fr">

<body xmlns="http://www.w3.org/1999/html">

    <a href="/">&larr; Index</a>

    <form method="POST" action="/stickers">
        <button type="submit"> create </button>
    </form>
    <ul>
        <#list stickers as sticker>
            <li><a href="/stickers/${sticker.id}">${sticker.id} - ${sticker.color} ; ${sticker.description}</a></li>
                <form method="POST" action="/stickers/${sticker.id}">
                        <button type="submit"> modifie </button>
                </form>
                <form method="POST" action="/stickers/delete/${sticker.id}">
                        <button type="submit"> delete </button>
                </form>
        </#list>
    </ul>

</body>

</html>
