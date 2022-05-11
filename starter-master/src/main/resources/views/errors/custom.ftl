<#ftl encoding="utf-8">

<html lang="fr">
<head><title>Erreur - StickerMania</title></head>
<body xmlns="http://www.w3.org/1999/html">

<h1>Erreur</h1>
<#if code?has_content>
    <h2>${code}</h2>
</#if>
<#if msg?has_content>
    <h3>${msg}</h3>
</#if>

</body>
</html>
