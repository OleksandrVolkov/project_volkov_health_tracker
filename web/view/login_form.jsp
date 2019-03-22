<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    </style>
</head>
<body>

<div id="id01" class="modal mod3">

    <form action="/account" class="modal-content" method="post">
            <h1>Log in</h1>
            <p>Please fill in this form to log in (?) an account.</p>
            <hr>
            <label for="username"><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name="username" required>

            <label for="psw"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="psw" required>

            <label for="psw-repeat"><b>Repeat Password</b></label>
            <input type="password" placeholder="Repeat Password" name="psw-repeat" required>

            <c:if test = "${notValid}">
                <span style="color:red;">No such user</span>
            </c:if>

            <hr>
            <button type="submit" class="registerbtn">Login</button>
            <button id = "cancel" type="button" class="registerbtn">Cancel</button>

            <input type="hidden" name="action" value="login">
        <div class="container signin">
            <p>Don't have an account? <a href="registration_form.jsp">Register</a>.</p>
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

