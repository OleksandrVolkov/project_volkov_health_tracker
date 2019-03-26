<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/static/bootstrap.css">
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">--%>
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>--%>
    <%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>--%>
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
<div class="jumbotron text-center">
        <a class="btn btn-primary btn-lg active" href="/account?action=logout">${language['exitAccount']}</a>
        <h1>${language['welcomeTitle']}</h1>
    </div>
</div>
<%--<div class="col-sm-6">--%>
    <%--<div class="dropdown">--%>
        <%--<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">${language['chooseLanguage']}--%>
            <%--<span class="caret"></span></button>--%>
        <%--<ul class="dropdown-menu">--%>
            <%--<li><a href="#">${language['en']}</a></li>--%>
            <%--<li><a href="#">${language['ru']}</a></li>--%>
            <%--<li><a href="#">${language['ua']}</a></li>--%>
        <%--</ul>--%>
    <%--</div>--%>
<%--</div>--%>
<div class="lang_select">
    <a class="langLink">
        <form method="get" action="/load_data">
            <select onchange="this.form.submit();" name="lang">
                <option hidden disabled selected>${language['chooseLanguage']}</option>
                <option value="en">${language['en']}</option>
                <option value="ru">${language['ru']}</option>
                <option value="ua">${language['ua']}</option>
            </select>
            <input type="hidden" name="action" value="load_cabinet">
        </form>
    </a>
</div>
<br>
<br>

<div class="container">
    <br>
        <div class="row">
                    <div class="card custom_card" style="width: 100%; height: 20rem">
                        <%--<img class="card-img-top" src="" alt="Card image cap">--%>
                        <div class="card-body">
                            <h2 class="card-title">${language['checkDietTitle']}</h2>
                            <p class="card-text">${language['checkDietDescription']}</p>
                            <a href="/load_data?action=loadToCheckDiet" class="btn btn-primary">${language['followLink']}</a>
                        </div>
                    </div>
        </div>





        <div class="row">
                <div class="card custom_card" style="width: 100%; height: 20rem">
                    <%--<img class="card-img-top" src="" alt="Card image cap">--%>
                    <div class="card-body">
                        <h2 class="card-title">${language['addDishTitle']}</h2>
                        <p class="card-text">${language['addDishDescription']}</p>
                        <a href="/load_data?action=load_to_add" class="btn btn-primary">${language['followLink']}</a>
                    </div>
                </div>
        </div>

        <div class="row">
                <div class="card custom_card" style="width: 100%; height: 20rem">
                    <%--<img class="card-img-top" src="" alt="Card image cap">--%>
                    <div class="card-body">
                        <h2 class="card-title">${language['normValuesTitle']}</h2>
                        <p class="card-text">${language['normValuesDescription']}</p>
                        <a href="/load_data?action=load_norm_values" class="btn btn-primary">${language['followLink']}</a>
                    </div>
                </div>
        </div>

        <div class="row">
                <div class="card custom_card" style="width: 100%;height: 20rem;">
                    <%--<img class="card-img-top" src="" alt="Card image cap">--%>
                    <div class="card-body">
                        <h2 class="card-title">${language['checkInfoTitle']}</h2>
                        <p class="card-text">${language['checkInfoDescription']}</p>
                        <a href="/body?action=update_user_data" class="btn btn-primary">${language['followLink']}</a>
                    </div>
                </div>
        </div>
</div>
    <%--</div>--%>


    <br>
    <br>
    <br>

</div>


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

