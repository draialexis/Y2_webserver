<#ftl encoding="utf-8">

<body xmlns="http://www.w3.org/1999/html">

<a href="/">&larr; Index</a>

<ul>
    <#list stickers as sticker>
        <li><a href="/stickers/${sticker.id}">${sticker.id} - ${sticker.color}: ${sticker.description}</a></li>
    </#list>
</ul>

</body>

</html>
