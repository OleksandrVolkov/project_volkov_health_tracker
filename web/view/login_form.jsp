<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">--%>
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>--%>
    <%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>--%>

    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            /*background-color: whi;*/
        }

        * {
            box-sizing: border-box;
        }

        /* Add padding to containers */
        .container {
            padding: 16px;
            background-color: white;
            border: 3px solid black;
            border-radius: 5px;
        }

        /* Full-width input fields */
        input[type=text], input[type=password] {
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: none;
            background: #f1f1f1;
        }

        input[type=text]:focus, input[type=password]:focus {
            background-color: #ddd;
            outline: none;
        }

        /* Overwrite default styles of hr */
        hr {
            border: 1px solid #f1f1f1;
            margin-bottom: 25px;
        }

        /* Set a style for the submit button */
        .registerbtn {
            background-color: #4CAF50;
            color: white;
            padding: 16px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
            opacity: 0.9;
        }

        .registerbtn:hover {
            opacity: 1;
        }


        a {
            color: black;
        }
        .link_val{
            position: absolute;
            margin-left: 96%;
            margin-top: 1%;
        }

        /* Set a grey background color and center the text of the "sign in" section */
        .signin {
            background-color: #f1f1f1;
            text-align: center;
        }




        .modal {
            /*display: none; !* Hidden by default *!*/
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: #474e5d;
            padding-top: 50px;
        }

        /* Modal Content/Box */
        .modal-content {
            background-color: #fefefe;
            margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
            border: 1px solid #888;
            width: 80%; /* Could be more or less, depending on screen size */
            border-radius: 5px;
        }

        .mod3{
            padding-top:0px;
        }

        form{
            padding: 16px;
            background-color: white;
            border: 3px solid black;
            border-radius: 5px;
        }
        .lang_select{
            background-color: #474e5d;
        }

    </style>
</head>
<body>



<div id="id01" class="modal mod3">
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
    <script>
    </script>

    <form action="/account" class="modal-content" method="post">
            <h1>${language['formName']}</h1>
            <p>${language['fillNeededData']}</p>
            <hr>
            <label for="username"><b>${language['usernameField']}</b></label>
            <input type="text" placeholder="${language['enterUsername']}" name="username" required>

            <label for="psw"><b>${language['passwordField']}</b></label>
            <input type="password" placeholder="${language['enterPassword']}" name="psw" required>

            <%--<label for="psw-repeat"><b>${language['repeatPasswordField']}</b></label>--%>
            <%--<input type="password" placeholder="${language['repeatPasswordField']}" name="psw-repeat" required>--%>

            <c:if test = "${notValid}">
                <span style="color:red;">No such user</span>
            </c:if>

            <hr>
            <button type="submit" class="registerbtn">${language['registerButton']}</button>
            <button id = "cancel" type="button" class="registerbtn">${language['cancelButton']}</button>

            <input type="hidden" name="action" value="login">
        <div class="container signin">
            <a href="/load_data?action=load_registration">${language['noAccount']}</a>
        </div>
    </form>
</div>

<script type="text/javascript">
    document.getElementById("cancel").onclick = function () {
        location.href = "/view/registration_form.jsp";
    };
</script>

</body>
</html>

