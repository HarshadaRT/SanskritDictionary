<%-- 
    Document   : index
    Created on : Dec 17, 2018, 3:26:17 PM
    Author     : Harshada, Mithilesh
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
    <meta charset="utf-8">
    <title>Sanskrit Dictionary</title>
    <link rel="stylesheet" href="fonts.css"/>
    <link rel="stylesheet" href="main.css"/>
    <link rel="stylesheet" href="hocr-proofreader.css"/>
    <script src="hocr-proofreader.js"></script>
	<script>
	 var hocrBase= 'http://localhost:9090/sansdictionary/';
	 var hOCRName= '1519_1.hocr';
	 var ImgName='1519_1.jpg';
	</script>
    <script src="main.js"></script>
</head>
<body>
<div class="viewport">
    <div class="toolbar">
        <div class="logo">Sanskrit Dictionary</div>
        <div class="separator"></div>
        <span>Zoom:</span>
        <button id="zoom-page-full">Full Page</button>
        <button id="zoom-page-width">Page Width</button>
        <button id="zoom-original">Original</button>
        <div class="separator"></div>

        <button id="button-save">Save</button>
    </div>

    <div id="layout-container" class="container"></div>
    <div id="editor-container" class="econtainer">
        <div >
            <form>
                <div class="form-group">
                    <label for="exampleFormControlSelect1">Select word ::</label>
                    <select name='dropdown' id="wordList" class="form-control" id="selectWord">
                        <option value="select">----Select----</option>
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
        if ($('#wordList').val() === "select") {
            $('#meaning').text("");
            $('#transliteration').val("");
            $('#posTag').val("");
        }
        else {
            $.ajax({
                url: "${context}" + "/dictionary/fetchMeaning/" + $('#wordList').val(),
                dataType: 'json',
                type: 'GET',
                success: function (meaning) {
                    $.each(meaning, function (index, v) {
                        var obj = $.parseJSON(v)
                        $.each(obj, function (key, val) {
                            if (key === 'meaning') {
                                $('#meaning').text(val);
                            }
                            if (key === 'transliteration') {
                                $('#transliteration').val(val);
                            }
                            if (key === 'posTag') {
                                $('#posTag').val(val);
                            }
                        });
                    });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.error(textStatus, errorThrown, jqXHR);
                }
            });
        }
    });

    function startup() {
        $.ajax({
            url: "${context}" + "/dictionary/populate",
            dataType: 'json',
            type: 'GET',
            success: function (idAndWord) {
                $.each(idAndWord, function (index, v) {
                    var obj = $.parseJSON(v)
                    $.each(obj, function (key, val) {
                        $('#wordList').append("<option value = "+key+">" + val + "</option>");
                    });
                });
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.error(textStatus, errorThrown, jqXHR);
            }
        });
    }
</script>
