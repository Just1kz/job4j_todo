<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">

    <title>Hello, Task-table!</title>
</head>
<body>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.js"
        integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<div><label></label></div>
<h3>
    _Hello job4j, welcome to task-table
</h3>
<div><label></label></div>

<div>
    <form class="form-horizontal" >
<%--        action="<c:url value='/add'/>" method='POST'--%>
        <div class="form-group">
            <label class="control-label col-sm-2" for="description"><strong>Add Task</strong></label>
            <div class="col-sm-5">
                <textarea class="form-control" rows="1" id="description" placeholder="Enter description new Task..."></textarea>
            </div>
            <div><label></label></div>
            <div class="form-group row">
                <label class="control-label col-sm-2" for="idCategories"><strong>__Select Category</strong></label>
            </div>
            <div class="col-sm-5">
<%--                <select class="form-control" name="idCategories" id="idCategories" multiple>--%>
<%--                    <c:forEach items="${allCategory}" var="category">--%>
<%--                        <option value='<c:out value="${category.id}"/>'>${category.name}</option>--%>
<%--                    </c:forEach>--%>
<%--                </select>--%>
                <select class="form-group custom-select" name="idCategories" id="idCategories" multiple>
                    <option value="" selected></option>
                </select>
            </div>
            <div><label></label></div>
            <button type="button" class="btn btn-primary" style="background-color: rgb(100,100,250)" onclick=addTask() >Add Task</button>
        </div>
</form>
<%--        onclick="addTask()"--%>
<form>
        <div class="form-group" >
            <label class="control-label col-sm-2" for="description"><strong>Enter Description Task for done</strong></label>
            <div class="col-sm-5">
                <textarea class="form-control" rows="1" id="descriptionDone" placeholder="Enter description Task in table for done"></textarea>
            </div>
            <div><label></label></div>
            <button type="button" class="btn btn-primary" style="background-color: rgb(100,100,250)" onclick="doneTask()">Done Task</button>
        </div>
</form>

        <div><label></label></div>
        <div class="form-group">
            <label class="btn btn-primary" for="filter" style="background-color: rgb(100,100,250)">Show all tasks</label>
            <input type="checkbox" id="filter" name="filter" value="filter" checked onchange="filterTask()">
        </div>


</div>
<div class="col-sm-offset-2">
    <table class="table table-bordered table-hover table-sm" id="table">
        <thead class="thead-inverse">
        <tr>
            <th>ID</th>
            <th>Description</th>
            <th>Created</th>
            <th>Author</th>
            <th>Category</th>
            <th>Done</th>
        </tr>
        </thead>

    </table>
</div>
</body>

<script>
    function addTask() {
        if (validate()) {
            $.ajax({
                    method: "POST",
                    url: "http://localhost:8080/todo/add",
                    data: {description : $("#description").val(), idCategories : $("#idCategories").val()},
                    success: function ($data) {
                        console.log($data);
                    }
                }
            );
        }
        location.reload();
    }

    function validate() {
        let result = true;
        let answer = '';
        let elements = [$("#description"), $("#idCategories")];
        for (let i = 0; i < elements.length; i++) {
            if (elements[i].val() === '') {
                answer += elements[i].attr("placeholder") + "\n";
                result = false;
            }
        }
        if (!result) {
            alert(answer);
        }
        return result;
    }
</script>

<script>
    function doneTask() {
        if (validateDone()) {
            $.ajax({
                    method: "POST",
                    url: "http://localhost:8080/todo/update",
                    data: {descriptionDone : $("#descriptionDone").val()},
                    success: function ($data) {
                        console.log($data);
                    }
                }
            );
        }
        location.reload();
    }
    function validateDone() {
        let result = true;
        let answer = '';
        let elements = [$("#descriptionDone")];
        for (let i = 0; i < elements.length; i++) {
            if (elements[i].val() === '') {
                answer += elements[i].attr("placeholder") + "\n";
                result = false;
            }
        }
        if (!result) {
            alert(answer);
        }
        return result;
    }
</script>

<script>
    $(document).ready(function () {
        showAll();
    })
</script>

<script>
    function filterTask() {
        let check = $("#filter").prop("checked");
        if (check) {
            showAll();
        } else {
            showFilterItems();
        }
    }
    function showAll() {
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/todo/showAll",
            dataType: 'json',
            success: function ($data) {
                deleteRows();
                addRowsWithTasksInTable($data);
            }
        });
    }

    function showFilterItems() {
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/todo/filter",
            dataType: 'json',
            success: function ($data) {
                deleteRows();
                addRowsWithTasksInTable($data);
            }
        });
    }

    function addRowsWithTasksInTable(data) {
        let items = data;
        console.log(items);
        for (let i = 0; i < items.length; i++) {
            let id = items[i]['id'];
            let description = items[i]['description'];
            let created = items[i]['created'];
            let author = items[i]['user']['name'];
            let category = items[i]['categoryList'][0]['name'];
            let done = items[i]['done'];
            $("#table tr:last").after(
                '<tr><td id="id">' + id + '</td>'
                + '<td>' + description + '</td>'
                + '<td>' + created + '</td>'
                +  '<td>' + author + '</td>'
                +  '<td>' + category + '</td>'
                + '<td>' + done + '</td></tr>'
            );
        }
    }
</script>
<script>
    function deleteRows() {
        let all_td = document.getElementsByTagName("tr");
        for(let i = 0; i < all_td.length; i++){
            if (i!==0) {
                all_td[i].innerHTML="";
                //?????????????????? ?????????? ???? ?????????????????? - ?????? ???????????? ?????????????? ?????????????????? ??????????????.
            }
        }
    }
</script>

<script>
    $(document).ready(function () {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/todo/allCategory",
            dataType: 'json',
            success: function (data) {
                let category = "";
                for (let i = 0; i < data.length; i++) {
                    category += "<option value=" + data[i]['id'] + ">" + data[i]['name'] + "</option>";
                }
                $('#idCategories option:last').after(category);
            }
        })
    })
</script>

</html>
