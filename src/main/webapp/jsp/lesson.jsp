<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">

    $(document).ready(function () {
        $("#lessonForm").validate({
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    success: function (html) {
                        $('#lessonTable').DataTable().ajax.reload(null, false);
                        showSuccessMessage(html);
                        closeForm();
                        return false;
                    },
                    error: function (xhr) {
                        if (xhr.status == 409) {
                            showErrorMessage(xhr.responseText);
                        }
                    }
                });
            },
            errorPlacement: function (error, element) {
                validPlaceError(error, element)
            },
            success: function (label, element) {
                validPlaceSuccess(label, element);
            }
        });

        $(".datepicker").datepicker({
            autoclose: true,
            format: "dd.mm.yyyy",
            language: 'ru',
            todayBtn: true
        });

        $("#lessonForm").submit(function (e) {
            e.preventDefault();
            $(this).valid();
            return false;
        });
    });
</script>
<div class="panel panel-success">
    <div class="panel-heading">
        <h4 class="panel-title">${type}</h4>
    </div>

    <form:form modelAttribute="lesson" id="lessonForm" method="post" action="saveLesson">
        <form:hidden path="id"/>
        <div class="panel-body">
            <div class="divider"><h4> Занятие: </h4></div>

            <div class="col-md-6">
                <div class="form-group input-group">
                    <label for="topic" class="control-label">Тема</label>
                    <form:input path="topic" id="topic" cssClass="required form-control"/>
                    <span class="help-block"></span>
                </div>
                <div class="form-group input-group">
                    <label for="description" class="control-label">Описание</label>
                    <form:input path="description" id="description" cssClass="form-control"/>
                    <span class="help-block"></span>
                </div>
                <div class="form-group input-group">
                    <label for="duration" class="control-label">Длительность</label>
                    <form:input path="duration" id="duration" cssClass="form-control digits"/>
                    <span class="help-block"></span>
                </div>
                <div class="form-group input-group">
                    <label for="dateLesson" class="control-label">Дата занятия</label>
                    <form:input path="dateLesson" id="dateLesson" cssClass="required dateRU datepicker form-control"/>
                    <span class="help-block"></span>
                </div>

                <div class="student-list">
                    <h4>Студенты:</h4>
                    <ul>
                        <c:forEach items="${lesson.students}" var="student" varStatus="index">
                            <li><p>${student.FIO}</p></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>


        <div class="panel-footer">
            <button class="btn btn-success">
                <span class="glyphicon glyphicon-ok"></span> Сохранить
            </button>

            <button type="reset" onclick="closeForm('lesson');"
                    class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> Закрыть
            </button>
        </div>
    </form:form>
    <div id
</div>
