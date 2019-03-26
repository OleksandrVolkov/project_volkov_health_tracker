<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
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

        /* Add a blue text color to links */
        a {
            color: black;
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
        span {
            color: red;
        }
    </style>
</head>
<body>

<div id="id01" class="modal mod3">
    <div class="lang_select">
        <a class="langLink">
            <form method="get" action="/load_data">
                <select onchange="this.form.submit();" name="lang">
                    <option hidden disabled selected>${language['chooseLanguage']}</option>
                    <option value="en">${language['en']}</option>
                    <option value="ru">${language['ru']}</option>
                    <option value="ua">${language['ua']}</option>
                </select>
                <input type="hidden" name="action" value="load_registration">
            </form>
        </a>
    </div>
    <form action="/account" class="modal-content" method="post" id="regform">
            <h1>${language['formName']}</h1>
            <p>${language['fillNeededData']}</p>
            <hr>
            <label for="name"><b>${language['nameField']}</b></label>
            <input type="text" placeholder="${language['enterName']}" name="name" required>
            <c:if test = "${!isValidName}">
                <span>${language['wrongName']}</span> <br><br>
            </c:if>

            <label for="surname"><b>${language['surnameField']}</b></label>
            <input type="text" placeholder="${language['enterSurname']}" name="surname" required>
            <c:if test = "${!isValidSurname}">
                <span>${language['wrongSurname']}</span> <br><br>
            </c:if>

            <label for="age"><b>${language['ageField']}</b></label>
            <input type="text" placeholder="${language['enterAge']}" name="age" required>
            <c:if test = "${!isValidAge}">
                <span>${language['wrongAge']}</span> <br><br>
            </c:if>

            <label for="email"><b>${language['emailField']}</b></label>
            <input type="text" placeholder="${language['enterEmail']}" name="email" required>
            <c:if test = "${!isValidEmail}">
                <span>${language['wrongEmail']}</span> <br><br>
            </c:if>
            <c:if test = "${!isNotTakenEmail}">
                <span>${language['busyEmail']}</span> <br><br>
            </c:if>

            <label for="username"><b>${language['usernameField']}</b></label>
            <input type="text" placeholder="${language['enterUsername']}" name="username" required>
            <c:if test = "${!isValidUsername}">
                <span>${language['wrongUsername']}</span> <br><br>
            </c:if>
            <c:if test = "${!isNotTakenUsername}">
                <span>${language['busyUsername']}</span> <br><br>
            </c:if>

            <label for="height"><b>${language['heightField']}</b></label>
            <input type="text" placeholder="${language['enterHeight']}" name="height" required>
            <c:if test = "${!isValidHeight}">
                <span>${language['wrongHeight']}</span> <br><br>
            </c:if>

            <label for="weight"><b>${language['weightField']}</b></label>
            <input type="text" placeholder="${language['enterWeight']}" name="weight" required>
            <c:if test = "${!isValidWeight}">
                <span>${language['wrongWeight']}</span> <br><br>
            </c:if>


                  <%--<input type="text" placeholder="Enter Your Weight" name="sex" required>--%>

        <label for="sex"><b>${language['sexField']}</b></label>
        <select name="sex" required>
            <option value="male">Male</option>
            <option value="female">Female</option>
        </select>
        <br><br>

        <label for="lifestyle"><b>${language['lifestyleField']}</b></label>
        <select name="lifestyle" required>
            <option value="passive">Passive</option>
            <option value="moderate">Moderate</option>
            <option value="ordinary">Ordinary</option>
            <option value="active">Active</option>
            <option value="sporty">Sporty</option>
        </select>
        <br><br>

            <label for="psw"><b>${language['passwordField']}</b></label>
            <input type="password" placeholder="${language['enterPassword']}" name="psw" required>
            <%--<label for="psw-repeat"><b>${language['repeatPasswordField']}</b></label>--%>
            <%--<input type="password" placeholder="${language['repeatPasswordField']}" name="psw-repeat" required>--%>
            <c:if test = "${!isValidPassword}">
                <span>${language['wrongPassword']}</span> <br><br>
            </c:if>
            <hr>
            <p>${language['termsPrivacy']}.</p>

            <button type="submit" class="registerbtn">${language['registerButton']}</button>
            <button id = "cancel" type="button" class="registerbtn">${language['cancelButton']}</button>

            <input type="hidden" name="action" value="validate">

        <div class="container signin">
            <p>Already have an account? <a href="/load_data?action=load_login">Sign in</a>.</p>
        </div>
    </form>
</div>


<script type="text/javascript">
    document.getElementById("cancel").onclick = function () {
        location.href = "/view/login_form.jsp";
    };
</script>

</body>
</html>
