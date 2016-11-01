<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<script type="text/javascript">

    $(document).ready(function () {

        $('#studentTable').DataTable({
            "ajax": "allStudents",
            "columns": [
                {
                    "render": function (data, type, full) {
                        return '<a href=\"#\" onclick=\"editEntity(' + full.id + ')\">Редактировать</a>'
                    }, "searchable": false, "orderable": false
                },
                {"data": "surname", 'title': 'Фамилия'},
                {"data": "name", 'title': 'Имя'},
                {"data": "gender", 'title': 'Пол'},
                {"data": "birthday", 'title': 'Дата рождения'},
                {
                    "render": function (data, type, full) {
                        return '<a href=\"#\" class="text-danger" onclick=\"deleteStudent(' + full.id + ')\">Удалить</a>'
                    }, "searchable": false, "orderable": false
                }

            ]
        });

    });

</script>
<div class="container">
    <button class="btn btn-success addBtn" onclick="saveEntity('student')">
        <span class="glyphicon glyphicon-plus"></span> Добавить студента
    </button>
    <div id="formPanel"></div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4>Список студентов</h4>
        </div>
        <br>

        <div class="panel-body">
            <table id="studentTable" class="table table-striped table-bordered" cellspacing="0" width="100%"></table>
        </div>
    </div>

</div>
<jsp:include page="footer.jsp"/>
