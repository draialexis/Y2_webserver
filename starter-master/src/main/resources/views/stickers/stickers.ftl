<#ftl encoding="utf-8">

<html lang="fr">
<#include "../bits/head.html">
<body xmlns="http://www.w3.org/1999/html">
<#include "../bits/navbar.ftl">

<ul>
    <#list stickers as sticker>
        <li>
                <a href="/stickers/${sticker.getId()}">
                        ${sticker.getId()} - ${sticker.getColor().toString()} ; ${sticker.getDescription().toString()}
                </a>
        </li>
    </#list>
</ul>

</body>

</html>
