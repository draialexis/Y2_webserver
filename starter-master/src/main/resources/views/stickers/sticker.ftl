<#ftl encoding="utf-8">

<html lang="fr">
<#include "../bits/head.html">
<body xmlns="http://www.w3.org/1999/html">
<#include "../bits/navbar.ftl">

<#include "sticker-update-form.ftl">

<h2>Gommette num&eacute;ro : ${sticker.getId()}</h2>
<p>${sticker.getColor().toString()} ; ${sticker.getDescription().toString()}</p>

</body>

</html>
