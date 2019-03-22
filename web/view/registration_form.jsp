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
    <form action="/account" class="modal-content" method="post" id="regform">
            <h1>Register</h1>
            <p>Please fill in this form to create an account.</p>
            <hr>
            <label for="name"><b>Name</b></label>
            <input type="text" placeholder="Enter Your Name" name="name" required>
            <c:if test = "${!isValidName}">
                <span>Not valid name</span> <br><br>
            </c:if>

            <label for="surname"><b>Surname</b></label>
            <input type="text" placeholder="Enter Surname" name="surname" required>
            <c:if test = "${!isValidSurname}">
                <span>Not valid surname</span> <br><br>
            </c:if>

            <label for="age"><b>Age</b></label>
            <input type="text" placeholder="Enter Your Age" name="age" required>
            <c:if test = "${!isValidAge}">
                <span>Not valid age</span> <br><br>
            </c:if>

            <label for="email"><b>Email</b></label>
            <input type="text" placeholder="Enter Email" name="email" required>
            <c:if test = "${!isValidEmail}">
                <span>Not valid email</span> <br><br>
            </c:if>
            <c:if test = "${!isNotTakenEmail}">
                <span>Email is already taken</span> <br><br>
            </c:if>

            <label for="username"><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name="username" required>
            <c:if test = "${!isValidUsername}">
                <span>Not valid username</span> <br><br>
            </c:if>
            <c:if test = "${!isNotTakenUsername}">
                <span>Username is already taken</span> <br><br>
            </c:if>

            <label for="height"><b>Height</b></label>
            <input type="text" placeholder="Enter Height" name="height" required>
            <c:if test = "${!isValidHeight}">
                <span>Not valid height</span> <br><br>
            </c:if>

            <label for="weight"><b>Weight</b></label>
            <input type="text" placeholder="Enter Your Weight" name="weight" required>
            <c:if test = "${!isValidWeight}">
                <span>Not valid weight</span> <br><br>
            </c:if>


                  <%--<input type="text" placeholder="Enter Your Weight" name="sex" required>--%>

        <label for="sex"><b>Sex</b></label>
        <select name="sex" required>
            <option value="male">Male</option>
            <option value="female">Female</option>
        </select>
        <br><br>

        <label for="lifestyle"><b>Lifestyle</b></label>
        <select name="lifestyle" required>
            <option value="passive">Passive</option>
            <option value="moderate">Moderate</option>
            <option value="ordinary">Ordinary</option>
            <option value="active">Active</option>
            <option value="sporty">Sporty</option>
        </select>
        <br><br>

            <label for="psw"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="psw" required>

            <label for="psw-repeat"><b>Repeat Password</b></label>
            <input type="password" placeholder="Repeat Password" name="psw-repeat" required>
            <c:if test = "${!isValidPassword}">
                <span>Not valid password</span> <br><br>
            </c:if>
            <hr>
            <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>

            <button type="submit" class="registerbtn">Register</button>
            <button id = "cancel" type="button" class="registerbtn">Cancel</button>

            <input type="hidden" name="action" value="register">

        <div class="container signin">
            <p>Already have an account? <a href="#">Sign in</a>.</p>
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
