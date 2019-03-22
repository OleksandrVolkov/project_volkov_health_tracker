<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        #main{
            display: none;
        }
    </style>
</head>
<body>


<h1>Add a new custom dish!</h1>
<%--<form method="get" action="/body">--%>
    <button id="initial_but"onclick="change()" type="button"> Add a dish </button>

    <div id="main">
            <form method="get" action="/body">
                <h3>Write a name of a dish</h3>
                <input type="text" name="add_dish_input" required>

                <h3>Choose a dish type</h3>
                <select name="dish_type" required>
                    <c:forEach items="${dishTypes}" var="dishType">
                        <option value="${dishType.id}">${dishType.name}</option>
                    </c:forEach>
                </select>

                <input type="submit" value="Send">
                <input type="hidden" name="action" value="addNewDish">
            </form>
    </div>

<script>
    function change() {
        input = document.getElementById('main').style.display = "inline";
        document.getElementById('initial_but').style.display = "none";
    }
</script>
</body>
</html>
