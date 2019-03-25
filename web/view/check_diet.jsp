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
    <style>
        .main{

        }
    </style>
</head>
<body>
<div class="jumbotron text-center">
    <a class="btn btn-primary btn-lg active" href="/view/cabinet.jsp">Back to cabinet</a>
    <h1>Check your diet</h1>
    <p>You can verify whether your diet is healthy choosing among added and pre-set dishes</p>
</div>
<div class="lang_select">
    <a class="langLink">
        <form method="get" action="/load_data">
            <select onchange="this.form.submit();" name="lang">
                <option hidden disabled selected>Choose language:</option>
                <option value="en">English</option>
                <option value="ru">Russian</option>
                <option value="ua">Ukrainian</option>
            </select>
            <input type="hidden" name="action" value="load_login">
        </form>
    </a>
</div>
<div class="text-center">
    <h1>Add a dish to your daily diet</h1>
    <div id="main">
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
    </div>
</div>

<div class="jumbotron text-center" style="margin-bottom:0">
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-4">
                This application provides you an opportunity to check whether your daily diet is healthy via Harris-Benedict equation.
                You can also see your norm daily values of the nutrients and calories
            </div>
            <div class="col-sm-4">

            </div>
            <div class="col-sm-4">
                <p>
                    Contact us<br>
                    Address: Povitryanoflotsky avenue, 43<br>
                    Phone number: +38(050)7864324
                </p>
            </div>
        </div>
    </div>
</div>

<script>
    var selectCounter = 2;
    function myFunction() {
        form = document.getElementById("mainForm");
        var select = document.createElement("SELECT");
        var cur_it = "food_item_".concat(selectCounter);

        alert(cur_it);
        select.setAttribute("name", cur_it);
        select.setAttribute("form", "mainForm");
        select.setAttribute("class", "browser-default custom-select custom-select-lg mb-3");
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