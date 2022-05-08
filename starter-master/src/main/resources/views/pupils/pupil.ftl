<#ftl encoding="utf-8">

<html lang="fr">

<body xmlns="http://www.w3.org/1999/html">

     <a href="/students">&larr; El&egrave;ves</a>

     <p>
          El&egrave;ves num&eacute;ro : ${student.id} <br>
         ${student.lastName} ${student.firstName}
     </p>
     <form method="POST" action="/students/delete/${student.id}">
          <button type="submit">supprimer </button>
     </form>
     <form method="POST" action="/students/${student.id}">
          <button type="submit">modifer </button>
     </form>
</body>

</html>