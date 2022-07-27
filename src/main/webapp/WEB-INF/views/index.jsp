<%@ page import="com.example.bookstore.entities.Book" %>
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

    <title>Hello, world!</title>
  </head>
  <body>

<%--nav bar--%>
  <nav class="navbar navbar-expand-lg navbar-light bg-primary">
    <div class="container-fluid">
      <a class="navbar-brand text-white" href="/">Book Store</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        </ul>


          <%
                if(request.getUserPrincipal() == null){
          %>

          <button data-bs-toggle="modal" data-bs-target="#loginModal" class="btn-outline-info btn text-white mx-2"> Login</button>
        <button data-bs-toggle="modal" data-bs-target="#signupModal"  class="btn-outline-info btn text-white"> Sign up</button>
        <%
                } else{
        %>

          <button type="button" class="btn btn-warning position-relative mx-4">
              Cart Items
              <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
    <%=request.getAttribute("cartItemCount")%>
    <span class="visually-hidden">Cart Items</span>
  </span>
          </button>

          <p class="text-white mt-2 me-2"><%=request.getUserPrincipal().getName()%></p>
          <a class="btn btn-sm btn-outline-danger" href="/logout"> log out</a>
          <%
              }
          %>
      </div>
    </div>
  </nav>

<!-- signup Modal -->
<div class="modal fade" id="signupModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content container p-5">

        <div class="text-center ">
            <h4>Sign Up</h4>
        </div>
        <hr>
        <form action="/customer" method="post">
            <div class="mb-2">
                <label  class="form-label">Name</label>
                <input type="text" name="name" class="form-control"  aria-describedby="emailHelp">
            </div>
            <div class="mb-2">
                <label for="exampleInputEmail1" class="form-label">Email address</label>
                <input type="email" name="email" class="form-control"  aria-describedby="emailHelp">
                <div  class="form-text">We'll never share your email with anyone else.</div>
            </div>
            <div class="mb-2">
                <label for="exampleInputPassword1" class="form-label">Password</label>
                <input type="password" name="password" class="form-control" >
            </div>
            <button type="submit" class="btn btn-primary">Sign Up</button>
        </form>

    </div>
  </div>
</div>

<!-- Login Modal -->
<div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content container p-5">
        <div class="text-center ">
            <h4>Log in</h4>
        </div>
        <hr>
        <form action="/login" method="post">
            <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Email address</label>
                <input type="email" name="username" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
            </div>
            <div class="mb-3">
                <label for="exampleInputPassword1" class="form-label">Password</label>
                <input type="password" name="password" class="form-control" id="exampleInputPassword1">
            </div>
            <button type="submit" class="btn btn-primary">Log in</button>
        </form>

    </div>
  </div>
</div>


<%--category bar--%>
<div class="text-center mt-4">
    <h3>Book Categories</h3>
</div>
<nav class="navbar navbar-expand-lg navbar-light bg-dark  mt-2">
    <div class="container-fluid">
        <div class="text-center" >
            <ul class="navbar-nav">
                <%
                    List<Category> categories = (List<Category>) request.getAttribute("categories");
                    for (Category category : categories){
                %>
                <li class="nav-item">
                    <a class="nav-link text-white btn" aria-current="page" href="/book/category/<%=category.getId()%>"><%=category.getCategoryName()%></a>
                </li>
                <%  }  %>
            </ul>
        </div>
    </div>
</nav>

<%--book list view--%>
<div class="container">

    <div class="row">

        <%
            List<Book> books = (List<Book>) request.getAttribute("books");
            for (Book book : books){
        %>

        <div class="col-3 my-3">
            <div class="card" style="width: 16rem;">
                <img src="/resourceURL/image/book_img.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title"><%=book.getTitle()%></h5>
                    <p class="card-text"> By :  <%=book.getAuthor().getName()%> </p>
                    <p class="card-text"> BDT : <%=book.getPrice()%> </p>
                    <div class="text-center">
                        <form action="/cart" method="post">
                            <input type="hidden" name="book" value="<%=book.getId()%>">
                            <button type="submit" class="btn btn-info">Add to Cart</button>
                        </form>
                    </div>

                </div>
            </div>

        </div>

        <%}%>
    </div>
</div>


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