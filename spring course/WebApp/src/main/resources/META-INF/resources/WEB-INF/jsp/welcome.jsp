<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<html>
<head>
    <title>Welcome Page</title>
</head>
<body>
    <h1>Welcome  ${name} </h1>

    <div> Your passowrd: ${password} </div>
     <div><a href="list-todos">Manage</a> your todos</div>
    <%@ include file="common/footer.jspf" %>
</body>
</html>