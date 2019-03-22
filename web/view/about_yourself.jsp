<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        .hidden_input{
            display: none;
        }

    </style>
</head>
<body>

<form method="post" action="/body">
    <h1>Id: ${id}</h1>
    <br>

    <h1>Name: ${name}</h1>
    <button onclick="change('name')" type="button"> Change </button>
    <input class="hidden_input inp" id="name_input" name="name" type="text">
    <br>

    <h1>Surname: ${surname}</h1>
    <button onclick="change('surname')" type="button"> Change </button>
    <input class="hidden_input" id="surname_input" name="surname" type="text">
    <br>

    <h1 id="username">Username: ${username}</h1>
    <button onclick="change('username')" type="button"> Change </button>
    <input class="hidden_input" id="username_input" name="username" type="text">
    <br>

    <h1 id="password">Password: ${password}</h1>
    <button onclick="change('password')" type="button"> Change </button>
    <input class="hidden_input" id="password_input" name="password" type="text">
    <br>

    <h1 id="height">Height: ${height}</h1>
    <button onclick="change('height')" type="button"> Change </button>
    <input class="hidden_input" id="height_input" name="height" type="text">
    <br>

    <h1 id="weight">Weight: ${weight} kg</h1>
    <button onclick="change('weight')" type="button"> Change </button>
    <input class="hidden_input" id="weight_input" name="weight" type="text">
    <br>

    <h1 id="age">Age: ${age}</h1>
    <button onclick="change('age')" type="button"> Change </button>
    <input class="hidden_input" id="age_input" name="age" type="text">
    <br>


    <h1 id="lifestyle">Lifestyle: ${lifestyle}</h1>
    <button onclick="change('lifestyle')" type="button"> Change </button>
    <select class="hidden_input" id="lifestyle_input" name="lifestyle">
        <option value="passive">Passive</option>
        <option value="moderate">Moderate</option>
        <option value="ordinary">Ordinary</option>
        <option value="active">Active</option>
        <option value="sporty">Sporty</option>
    </select>
    <br>

    <h1 id="email">Email: ${email}</h1>
    <button onclick="change('email')" type="button"> Change </button>
    <input class="hidden_input" id="email_input" name="email" type="text">
    <br>
    <h1>Sex: ${sex}</h1> <button onclick=""> Change </button>





    <input type="hidden" name="action" value="update_user_data">
    <input type="submit" value="Send data">
</form>

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



