<%-- 
    Document   : index
    Created on : Dec 17, 2018, 3:26:17 PM
    Author     : Harshada
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sanskrit Dictionary</title>
    </head>

    <body>
        <div class="container" style="margin-top: 70px;">
            <div class="row" style="padding-top: 10px;">
                <div class="col-md-6 col-sm-6 col-lg-6">
                    <div class="img-responsive" style="overflow-y: auto; height:600px;">
                        <%--<c:forEach items="${pageList}" var="page">--%>

                        <%--</c:forEach>--%>
                    </div>
                </div>
                <div class="col-md-6 col-sm-6 col-lg-6">
                    <form>
                        <div class="form-group">
                            <label for="exampleFormControlSelect1">Select word ::</label>
                            <select name='dropdown' id="wordList" class="form-control" id="selectWord">
                                <%--<c:forEach items="${dictionary}" var="dictionary">--%>
                                    <!--<option>${dictionary.word}</option>-->
                                <%--</c:forEach>--%>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="exampleFormControlInput1">Transliteration :: </label>
                            <input type="text" class="form-control" id="transliteration" placeholder="transliteration">
                        </div>
                        <div class="form-group">
                            <label for="exampleFormControlInput1">POS Tag ::</label>
                            <input type="text" class="form-control" id="posTag" placeholder="pos tags eg.(adj.,n.)">
                        </div>
                        <div class="form-group">
                            <label for="exampleFormControlTextarea1">Meaning and Citation ::</label>
                            <textarea class="form-control" id="meaning" rows="3"></textarea>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

<script>
    $(document).ready(function () {
        startup();
    });

    $("select[name='dropdown']").change(function () {
        if ($(this).val() == 2) {
            //do what u want here
        }//u can check your desired value here
    });

    <c:forEach items="${metadata}" var="record">
        <c:forEach items="${record.value}" var="data">
            <c:if test="${data ne null}">
    < tr >
            < td >${record.key} < /td>
            < td >${data} < /td>
            < /tr>
            </c:if>
        </c:forEach>
    </c:forEach>

    function startup() {
        $.ajax({
            url: context + "/dictionary/" + imagePath,
            dataType: 'json',
            type: 'GET',
            success: function (idAndWord) {
                $.each(idAndWord, function (index, value) {
                    console.log(index + " =====> " + value);
                    alert(index + ": " + value);
                    $('wordList').add("<option>${idAndWord.word}</option>")
                });

            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.error(textStatus, errorThrown, jqXHR);
            }
        });
    }
</script>
