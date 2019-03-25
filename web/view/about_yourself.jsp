<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <style>
        .hidden_input{
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
        <form method="get" action="/body">
            <select onchange="this.form.submit();" name="lang">
                <option hidden disabled selected>${language['chooseLanguage']}</option>
                <option value="en">${language['en']}</option>
                <option value="ru">${language['ru']}</option>
                <option value="ua">${language['ua']}</option>
            </select>
            <input type="hidden" name="action" value="aboutYourself">
        </form>
    </a>
</div>


    <form method="post" action="/body">
            <%--<h1>Id: ${id}</h1>--%>
            <%--<br>--%>

            <h1>${language['nameField']} ${name}</h1>
            <button onclick="change('name')" type="button"> ${language['changeButton']} </button>
            <input class="hidden_input inp" id="name_input" name="name" type="text">
            <br>


            <h1>${language['surnameField']} ${surname}</h1>
            <button onclick="change('surname')" type="button"> ${language['changeButton']} </button>
            <input class="hidden_input" id="surname_input" name="surname" type="text">
            <br>



            <h1 id="username">${language['usernameField']} ${username}</h1>
            <button onclick="change('username')" type="button"> ${language['changeButton']} </button>
            <input class="hidden_input" id="username_input" name="username" type="text">
            <br>


        <h1 id="password">${language['passwordField']} ${password}</h1>
        <button onclick="change('password')" type="button"> ${language['changeButton']} </button>
        <input class="hidden_input" id="password_input" name="password" type="text">
        <br>

        <h1 id="height">${language['heightField']} ${height}</h1>
        <button onclick="change('height')" type="button"> ${language['changeButton']} </button>
        <input class="hidden_input" id="height_input" name="height" type="text">
        <br>

        <h1 id="weight">${language['weightField']} ${weight} kg</h1>
        <button onclick="change('weight')" type="button"> ${language['changeButton']} </button>
        <input class="hidden_input" id="weight_input" name="weight" type="text">
        <br>

        <h1 id="age">${language['ageField']} ${age}</h1>
        <button onclick="change('age')" type="button"> ${language['changeButton']} </button>
        <input class="hidden_input" id="age_input" name="age" type="text">
        <br>


        <h1 id="lifestyle">${language['lifestyleField']} ${lifestyle}</h1>
        <button onclick="change('lifestyle')" type="button"> ${language['lifestyleField']} </button>
        <select class="hidden_input" id="lifestyle_input" name="lifestyle">
            <option value="passive">${language['passiveOption']}</option>
            <option value="moderate">${language['moderateOption']}</option>
            <option value="ordinary">${language['ordinaryOption']}</option>
            <option value="active">${language['activeOption']}</option>
            <option value="sporty">${language['sportyOption']}</option>
        </select>
        <br>

        <h1 id="email">${language['email']} ${email}</h1>
        <button onclick="change('email')" type="button"> ${language['changeButton']} </button>
        <input class="hidden_input" id="email_input" name="email" type="text">
        <br>
        <h1>${language['sexField']} ${sex}</h1>
        <button onclick="change('sex')" type="button"> ${language['changeButton']} </button>
        <select class="hidden_input" id="sex_input" name="sex">
            <option value="male">${language['maleOption']}</option>
            <option value="female">${language['femaleOption']}</option>
        </select>
        <br>
        <br>





        <input type="hidden" name="action" value="update_user_data">
        <input type="submit"class="btn btn-primary" value="${language['sendData']}">

    </form>



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
   function change(id) {
       input = document.getElementById(id + '_input').style.display = "inline";
   }
</script>


</body>
</html>















<%--//       if(document.getElementById('mainForm') == null) {--%>
<%--//           var form = document.createElement("FORM");--%>
<%--//           form.setAttribute('method', 'POST');--%>
<%--//           form.setAttribute('action', '/body');--%>
<%--//           var submit = document.createElement("INPUT");--%>
<%--//           var hidden = document.createElement("INPUT");--%>
<%--//           submit.setAttribute('type', 'submit');--%>
<%--//           submit.setAttribute('value', 'Update');--%>
<%--//           hidden.setAttribute('type','hidden');--%>
<%--//           hidden.setAttribute('name', 'action');--%>
<%--//           hidden.setAttribute('value', 'update_user_data');--%>
<%--//           form.setAttribute('id', 'mainForm');--%>
<%--//           form.setAttribute('name', 'mainForm');--%>
<%--//           form.appendChild(submit);--%>
<%--//           form.appendChild(hidden);--%>
<%--//           document.body.appendChild(form);--%>
<%--//       }--%>
<%--//--%>
<%--//       if(document.getElementById(id + '_id') == null){--%>
<%--//           input = document.createElement("INPUT");--%>
<%--//           input.setAttribute('type', 'text');--%>
<%--////           input.setAttribute("value", id);--%>
<%--//--%>
<%--//           temp_val = id.concat('_input');--%>
<%--////           alert(temp_val)--%>
<%--//           input.setAttribute('name', temp_val);--%>
<%--//           alert(id + '_input');--%>
<%--//           input.setAttribute('id', id + '_id');--%>
<%--//           document.getElementById('mainForm').appendChild(input);--%>
<%--//           div = document.getElementById(id);--%>
<%--//           div.appendChild(input);--%>
<%--//--%>
<%--//           var br = document.createElement("BR");--%>
<%--//           document.body.appendChild(br);--%>
<%--//           var br = document.createElement("BR");--%>
<%--//           document.body.appendChild(br);--%>
<%--//       }--%>



