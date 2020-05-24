<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Artists</title>
</head>
<body>
<h1>Music catalog</h1>
<h7>Add a new artist:</h7>
<form method="post">
    <input name="artist" required type="text" placeholder=" type artist name here..." autofocus />
    <input type="submit" value="Add to the data base" />
</form>
<br/>
<br/>
<ol>
    <c:forEach items = "${artists}" var = "s">
        <li>${s.getName()} <br/></li>
    </c:forEach>
</ol>
</body>
</html>