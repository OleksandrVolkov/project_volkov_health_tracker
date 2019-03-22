<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <title>Title</title>
    <script src="jquery-3.3.1.min.js"></script>
</head>
<body>
    <h1>Add a dish that yo ate</h1>
    <button onclick="myFunction()">Add a dish</button><br>

    <form action="/body" id="mainForm">
        <%--<select name="cars" id="selectCars" form="mainForm">--%>
            <%--<option value="volvo">Volvo</option>--%>
            <%--<option value="saab">Saab</option>--%>
            <%--<option value="fiat">Fiat</option>--%>
            <%--<option value="audi">Audi</option>--%>
        <%--</select>--%>
        <br><br>
        <input type="submit">
        <input type="hidden" name="action" value="checkDiet">
    </form>
<script>
    var selectCounter = 2;
    function myFunction() {
        form = document.getElementById("mainForm");
        var select = document.createElement("SELECT");
        var cur_it = "food_item_".concat(selectCounter);
        alert(cur_it);
        select.setAttribute("name", cur_it);
        select.setAttribute("form", "mainForm");
        selectCounter++;

        <%--<c:set var = "curString" scope = "session" value = "${dishes.get(0).key}"/>--%>
        <%--var curString = "${dishes.get(0).key}";--%>

            <c:forEach items="${dishes}" var="dish">
                <c:if test = "${dish.key.getClass().simpleName eq 'Dish'}">
                    option = document.createElement("OPTION");
                    option.setAttribute("value", "dis_${dish.key.id}");
                    option.innerHTML = "${dish.key.name}";
                    select.appendChild(option);
                </c:if>
                <c:if test = "${dish.key.getClass().simpleName eq 'CustomDish'}">
                    option = document.createElement("OPTION");
                    option.setAttribute("value", "cus_${dish.key.id}");
                    option.innerHTML = "${dish.key.name}";
                    select.appendChild(option);
                </c:if>
            </c:forEach>

        form.appendChild(select);
        document.body.appendChild(select);
        var br = document.createElement("BR");
        document.body.appendChild(br);
        var br = document.createElement("BR");
        document.body.appendChild(br);
    }

</script>
</body>
</html>




        <%--form = document.getElementById("mainForm");--%>
        <%--var select = document.createElement("SELECT");--%>
        <%--var option1 = document.createElement("OPTION");--%>
        <%--option1.setAttribute("value", "val1");--%>
        <%--option1.innerHTML = "VAL1";--%>
        <%--var option2 = document.createElement("OPTION");--%>
        <%--option2.setAttribute("value", "val2");--%>
        <%--option2.innerHTML = "VAL2";--%>
        <%--select.appendChild(option1);--%>
        <%--select.appendChild(option2);--%>
        <%--document.body.appendChild(select);--%>
        <%--var br = document.createElement("BR");--%>
        <%--document.body.appendChild(br);--%>