<%@ page import="java.util.List" %>
<%@ page import="org.springframework.validation.FieldError" %>
<%@ page import="com.example.bookstore.enums.UserType" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Book Store || Home</title>


</head>
<body>


<%--nav bar--%>
<nav class="navbar navbar-expand-lg navbar-light bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand text-white" href="/">Book Store</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            </ul>


            <button data-bs-toggle="modal" data-bs-target="#loginModal" class="btn-outline-info btn text-white mx-2">
                Login
            </button>
            <button data-bs-toggle="modal" data-bs-target="#signupModal" class="btn-outline-info btn text-white"> Sign
                up
            </button>

        </div>
    </div>
</nav>


<div class="text-center mt-4">
    <h4>Sign Up</h4>
</div>
<hr>

<div class="container">

    <form class="p-4" action="/user/sign-up" method="post">


        <input type="hidden" name="role" value="<%=UserType.CUSTOMER%>">
        <%
            if (request.getAttribute("error") != null) {
        %>
        <div class="alert alert-danger" role="alert">
            ${error}
        </div>

        <% }%>

        <%
            if (request.getAttribute("success") != null) {
        %>
        <div class="alert alert-success" role="alert">
            ${success}
        </div>

        <% } %>

        <div class="mb-2">
            <label class="form-label">Name</label>
            <input type="text" name="name" class="form-control">

            <%
                if (request.getAttribute("nameError") != null) {
                    List<FieldError> nameError = (List<FieldError>) request.getAttribute("nameError");
                    for (FieldError error : nameError) {
            %>
            <div class="alert alert-danger" role="alert">
                <%=error.getDefaultMessage()%>
            </div>
            <% }
            }%>
        </div>
        <div class="mb-2">
            <label class="form-label">Email address</label>
            <input type="email" name="email" class="form-control" aria-describedby="emailHelp">
            <div class="form-text">We'll never share your email with anyone else.</div>

            <%
                if (request.getAttribute("emailError") != null) {
                    List<FieldError> emailError = (List<FieldError>) request.getAttribute("emailError");
                    for (FieldError error : emailError) {
            %>
            <div class="alert alert-danger" role="alert">
                <%=error.getDefaultMessage()%>
            </div>
            <% }
            }%>
        </div>
        <div class="mb-2">

            <label class="form-label">Password</label>
            <input type="password" name="password" class="form-control">
            <%
                if (request.getAttribute("passwordError") != null) {
                    List<FieldError> passwordError = (List<FieldError>) request.getAttribute("passwordError");
                    for (FieldError error : passwordError) {
            %>
            <div class="alert alert-danger" role="alert">
                <%=error.getDefaultMessage()%>
            </div>
            <% }
            }%>
        </div>
        <button type="submit" class="btn btn-primary">Sign Up</button>
    </form>

    ${requestScope.hasError}


</div>

<!-- Optional JavaScript; choose one of the two! -->

<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

<!-- Option 2: Separate Popper and Bootstrap JS -->
<!--
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
-->
</body>
</html>