<%-- 
    Document   : index
    Created on : Dec 18, 2018, 2:13:32 PM
    Author     : Harshada
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sanskrit Dictionary</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    </head>
    
    <body>
        
        <h4> Dictionary Pages</h4>
        <div class="container">
            
            <div class="row">
                <div class="col-md-6 col-sm-6 col-lg-6">
                    <div class="img-responsive" style="overflow-y: auto; height:600px;">
                        <img class="img-rounded" src="1519_1.jpg" alt="photo1" style="width:500px;height: 800px;" />
                    </div>
                </div>
                <div class="col-md-6 col-sm-6 col-lg-6">
                    <form>
                        <div class="form-group">
                            <label for="exampleFormControlSelect1">Select word ::</label>
                            <select class="form-control" id="selectWord">
                              <option>1</option>
                              <option>2</option>
                              <option>3</option>
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