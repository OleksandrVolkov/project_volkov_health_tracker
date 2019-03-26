<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
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
    </style>
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">--%>
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>--%>
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>--%>
    <%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>--%>


    <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">--%>
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>--%>
    <%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>--%>
</head>
<body>
<div id="id01" class="modal mod3">
    <div class="modal-content">
<c:if test = "${isWellProteins && isWellCarbohydrates && isWellFats}">
    <h1> Your results are GOOD!</h1>
</c:if>
<c:if test = "${!isWellProteins}">
    <h1> You consumed ${exceededProteins} proteins more than needed</h1>
    <br>
</c:if>
<c:if test = "${!isWellCarbohydrates}">
    <h1> You consumed ${exceededCarbohydrates} carbohydrates more than needed</h1>
    <br>
</c:if>
<c:if test = "${!isWellFats}">
    <h1> You consumed ${exceededFats} fats more than needed</h1>
</c:if>

        <a href="/load_data?action=load_cabinet"><h1>Get it!</h1></a>

    </div>
</div>

</body>
</html>
