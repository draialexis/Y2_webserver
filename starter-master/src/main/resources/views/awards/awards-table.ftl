<table>
    <#if awards?has_content>
        <tr>
            <th>ID</th>
            <th>date</th>
            <th>gommette</th>
            <th>&eacute;l&egrave;ve</th>
            <#if isAuthorized>
                <th>enseignant</th>
                <th>actions</th>
            </#if>
            <th>motif</th>
        </tr>
        <#list awards as award>
            <tr>
                <td><a href="/awards/id/${award.getId()}">${award.getId()}</a></td>
                <td>${award.getAttributionDate()}</td>
                <td><a href="/stickers/${award.getSticker().getId()}">${award.getSticker().getColor().name()}</a></td>
                <td>
                    <a href="/students/${award.getStudent().getId()}">
                        ${award.getStudent().getFirstName()} ${award.getStudent().getLastName()}
                    </a>
                    (<a href="/awards/student/${award.getStudent().getId()}">voir sa liste compl&egrave;te</a>)
                </td>
                <#if isAuthorized>
                    <td><a href="/teachers/${award.getTeacher().getId()}">${award.getTeacher().getLastName()}</a></td>
                    <td>
                        <form action="/awards/delete/${award.getId()}" method="POST">
                            <button type="submit">SUPPRIMER</button>
                        </form>
                    </td>
                </#if>
                <td>"${award.getMotive()}"</td>
            </tr>
        </#list>
    </#if>
</table>