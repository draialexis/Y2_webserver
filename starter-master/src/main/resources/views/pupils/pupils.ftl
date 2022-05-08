<#ftl encoding="utf-8">

<html lang="fr">

<body xmlns="http://www.w3.org/1999/html">



        <a href="/">&larr; Index</a>
        <form method="POST" action="/students">
            <button type="submit"> create </button>
        </form>
        <ul>
            <#list students as student>
                <li><a href="/students/${student.id}">${student.id} - ${student.firstName} ${student.lastName}</a></li>
                    <form method="POST" action="/students/${student.id}">
                            <button type="submit"> modifie </button>
                    </form>
                    <form method="POST" action="/students/delete/${student.id}">
                            <button type="submit"> delete </button>
                    </form>
            </#list>

        </ul>

</body>

</html>
