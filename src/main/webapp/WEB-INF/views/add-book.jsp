<%@ page import="com.example.bookstore.entities.Author" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.bookstore.entities.Category" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Add New Book</title>
</head>
<body>

<%
    List<Author> authors = (List<Author>) request.getAttribute("allAuthor");
    List<Category> categories = (List<Category>) request.getAttribute("allCategory");

%>

<form action="/book" method="post">
    <label >Book title</label>
    <input type="text" name="title"> <br>

    <label > Book Price</label>
    <input type="number" name="price"> <br>


    <label > select author</label>
    <select name="author" id="">
        <% for (Author author: authors) { %>
        <option value="<%=author.getId()%>"> <%=author.getName()%></option>
        <%
                }
        %>
    </select>
    <br>

    <label > select category</label>
    <select name="category" >
        <% for (Category category: categories) { %>
        <option value="<%=category.getId()%>"> <%=category.getCategoryName()%></option>
        <%
                }
        %>
    </select>
    <br>
    <input type="submit">

</form>



<!-- Optional JavaScript; choose one of the two! -->

<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<!-- Option 2: Separate Popper and Bootstrap JS -->
<!--
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
-->
</body>
</html>