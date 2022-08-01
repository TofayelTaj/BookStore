<%@ page import="com.example.bookstore.entities.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.bookstore.enums.OrderStatus" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Hello, world!</title>
</head>
<body>

<%--nav bar--%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/admin/?status=all">Book Store Admin</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link " aria-current="page" href="/book">Add Book</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/author">Add Author</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/category">Add Category</a>
                </li>


            </ul>

            <a href="/logout" class="btn btn-danger">Log Out</a>
        </div>
    </div>
</nav>


<div class="container">

    <div class="my-3">
        <form action="/admin/">
            <select name="status" id="">
                <option value="all">All Order</option>
                <%
                    for (OrderStatus orderStatus : OrderStatus.values()) {
                %>

                <option value="<%=orderStatus.name()%>"><%=orderStatus.name()%>
                </option>

                <% } %>
            </select>
            <input type="submit" value="Filter">
        </form>
    </div>
    <div class="row">

        <div class="col">

            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Customer Name</th>
                    <th scope="col">Address</th>
                    <th scope="col">Status</th>
                </tr>
                </thead>
                <tbody>

                <%
                    List<Order> orderList = (List<Order>) request.getAttribute("orders");
                    for (Order order : orderList) {
                %>

                <tr>

                    <td><%=order.getCustomer().getName()%>
                    </td>
                    <td><%=order.getShippingAddress().getAddressLine()%>
                    </td>

                    <td>
                        <form action="/admin/change-status" method="post">
                            <input type="hidden" name="orderId" value="<%=order.getId()%>">
                            <select name="status" >
<%--                                <option value=""><%=order.getStatus()%></option>--%>
                                <%
                                    for (OrderStatus orderStatus : OrderStatus.values()) {
                                %>
                                <option
                                            <%=orderStatus.name().equals(order.getStatus()) ? "selected" : ""%>
                                        value="<%=orderStatus.name()%>"><%=orderStatus.name()%>
                                </option>

                                <% } %>
                            </select>
                            <input type="submit" value="Changes">
                        </form>

                    </td>
                </tr>

                <% } %>
                </tbody>
            </table>
        </div>
    </div>
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