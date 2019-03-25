<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
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
            <input type="hidden" name="action" value="load_norm_values">
        </form>
    </a>
</div>
<div class="text-center">
    <h1>${language['propTitle']}</h1>
    <h3>${language['proteinsProp']} ${nutrients.proteins}</h3>
    <h3>${language['carbohydratesProp']} ${nutrients.carbohydrates}</h3>
    <h3>${language['fatsProp']} ${nutrients.fats}</h3>
    <h3>${language['calloriesProp']} ${callories}</h3>
    <h3>${language['amrProp']} ${amr}</h3>
    <h3>${language['bmrProp']} ${bmr}</h3>
</div>
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

</body>
</html>
