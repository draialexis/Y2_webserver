<#ftl encoding="utf-8">
<html lang="fr">

<body xmlns="http://www.w3.org/1999/html">

    <a href="/stickers">&larr; El&egrave;ves</a>

    <form method="POST" action="/stickers/modif/${path_id}">
        <select name="color">
            <#list color as color>
                <option value = ${color} > ${color} </option>
            </#list>
        </select>
        <select name="description">
            <#list description as description>
                <option value = ${description} > ${description} </option>
            </#list>
        </select>
        <p><input type="submit" value="Submit"></p>
    </form>

</body>

</html>