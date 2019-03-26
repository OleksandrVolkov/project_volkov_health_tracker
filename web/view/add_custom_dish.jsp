<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/bootstrap.css">
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">--%>
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>--%>
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>--%>
    <%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>--%>

    <style>
        #main{
            display: none;
        }
    </style>
</head>
<body>
<div class="jumbotron text-center">
    <a class="btn btn-primary btn-lg active" href="/load_data?action=load_cabinet">${language['backToCabinet']}</a>
    <h1>${language['title']}</h1>
    <p>${language['description']}</p>
</div>
<div class="lang_select">
    <a class="langLink">
        <form method="get" action="/load_data">
            <select onchange="this.form.submit();" name="lang">
                <option hidden disabled selected>${language['chooseLanguage']}</option>
                <option value="en">${language['en']}</option>
                <option value="ru">${language['ru']}</option>
                <option value="ua">${language['ua']}</option>
            </select>
            <input type="hidden" name="action" value="load_to_add">
        </form>
    </a>
</div>

<h1>${language['addDishTitle']}</h1>
<%--<form method="get" action="/body">--%>
    <button id="initial_but"onclick="change()" type="button">${language['addDishButton']}</button>

    <div id="main">
            <form method="get" action="/body">
                <h3>${language['dishName']}</h3>
                <input type="text" name="add_dish_input" required>

                <h3>${language['chooseDishType']}</h3>
                <select name="dish_type" required>
                    <c:forEach items="${dishTypes}" var="dishType">
                        <option value="${dishType.id}">${dishType.name}</option>
                    </c:forEach>
                </select>
                <br>
                <input type="text" name="proteins" placeholder="Proteins" required><br>
                <input type="text" name="carbohydrates" placeholder="Carbohydrates" required><br>
                <input type="text" name="fats" placeholder="Fats" required><br>
                <br>
                <input type="submit" value="${language['sendData']}">
                <input type="hidden" name="action" value="addNewDish">
            </form>
    </div>
<br>
<br>
<br>
<br>
<br>
<br>

<div class="jumbotron text-center" style="margin-bottom:0">
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-4">
                ${language['footerDescription']}
            </div>
            <div class="col-sm-4">

            </div>
            <div class="col-sm-4">
                <p>
                    ${language['footerContactUs']}<br>
                    ${language['footerAddress']}<br>
                    ${language['footerPhoneNumber']}
                </p>
            </div>
        </div>
    </div>
</div>
<script>
    function change() {
        input = document.getElementById('main').style.display = "inline";
        document.getElementById('initial_but').style.display = "none";
    }
</script>
</body>
</html>
