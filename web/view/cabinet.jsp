<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <style>

        <%----%>
        /*.btn-group a{*/
            /*background-color: red;*/
        /*}*/
        /*.btn-group a:hover{*/
            /*background-color: red;*/
        /*}*/
        .custom_card{
            border: 3px solid black;
            text-align: center;
        }
        h5, p, a{
            text-align: center;
        }

        body{
            font-size: 20pt;
        }
    </style>
</head>
<body>
<%--<div class="dropdown">--%>
    <%--<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Dropdown Example--%>
        <%--<span class="caret"></span></button>--%>
    <%--<ul class="dropdown-menu">--%>
        <%--<li><a href="#">HTML</a></li>--%>
        <%--<li><a href="#">CSS</a></li>--%>
        <%--<li><a href="#">JavaScript</a></li>--%>
    <%--</ul>--%>
<%--</div>--%>

<div class="container">
    <div class="row">
        <div class="col-sm-11">

        </div>
        <div class="col-sm-1">
            <div class="dropdown">
                <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Change language
                    <span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <li><a href="#">English</a></li>
                    <li><a href="#">Russian</a></li>
                    <li><a href="#">Ukrainian</a></li>
                </ul>
            </div>
        </div>
    </div>
    <br>

        <div class="row">
                    <div class="card custom_card" style="width: 100%; height: 20rem">
                        <%--<img class="card-img-top" src="" alt="Card image cap">--%>
                        <div class="card-body">
                            <h2 class="card-title">Дневной рацион</h2>
                            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>

                            <a href="/load_data?action=loadToCheckDiet" class="btn btn-primary">Go somewhere</a>
                        </div>
                    </div>
        </div>

        <div class="row">
                <div class="card custom_card" style="width: 100%; height: 20rem">
                    <%--<img class="card-img-top" src="" alt="Card image cap">--%>
                    <div class="card-body">
                        <h2 class="card-title">Добавить тип еды</h2>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        <a href="/load_data?action=load_to_add" class="btn btn-primary">Go somewhere</a>
                    </div>
                </div>
        </div>

        <div class="row">
                <div class="card custom_card" style="width: 100%; height: 20rem">
                    <%--<img class="card-img-top" src="" alt="Card image cap">--%>
                    <div class="card-body">
                        <h2 class="card-title">Посмотреть норму</h2>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        <a href="/load_data?action=load_norm_values" class="btn btn-primary">Go somewhere</a>
                    </div>
                </div>
        </div>

        <div class="row">
                <div class="card custom_card" style="width: 100%;height: 20rem;">
                    <%--<img class="card-img-top" src="" alt="Card image cap">--%>
                    <div class="card-body">
                        <h2 class="card-title">О себе</h2>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        <a href="/body?action=aboutYourself" class="btn btn-primary">Go somewhere</a>
                    </div>
                </div>
        </div>
</div>
    <%--</div>--%>


    <br>
    <br>
    <br>

</div>
</body>
</html>















<%--<div class="row">--%>
<%--<div class="btn-group btn-group-justified">--%>
<%--<a href="#" class="btn btn-primary">Проверить дневной рацион</a>--%>
<%--<a href="#" class="btn btn-primary">Добавить тип еды</a>--%>
<%--<a href="#" class="btn btn-primary">Посмотреть норму для вас</a>--%>
<%--<form id="aboutYourself">--%>
<%--<a href="#" class="btn btn-primary">О себе</a>--%>
<%--</form>--%>
<%--</div>--%>

