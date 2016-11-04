package ru.innopolis.mvc.controller;

import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.mvc.Utils;
import ru.innopolis.mvc.editor.DateCustomEditor;
import ru.innopolis.mvc.entity.Student;
import ru.innopolis.mvc.service.StudentService;

import java.util.Date;
import java.util.List;

/**
 * Created by Кирилл on 31.10.2016.
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateCustomEditor());
    }

    /**
     * Получение страницы студентов
     *
     * @return
     */
    @RequestMapping(value = "studentPage", method = RequestMethod.GET)
    public String getStudentPage() {
        return "students";
    }

    /**
     * Получение всех студентов - для плагина DataTable по ajax
     *
     * @return список всех студентов в формате JSON
     */
    @RequestMapping(value = "allStudents", method = RequestMethod.GET)
    public ResponseEntity<String> getStudents() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return Utils.convertListToJson(gsonBuilder, studentService.getStudentList());
    }

    /**
     * Форма создания студента
     *
     * @param map
     * @return форма
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "student", method = RequestMethod.GET)
    public String addStudentPage(ModelMap map) {
        map.addAttribute("type", "Режим добавления студента");
        map.addAttribute("student", new Student());
        return "student";
    }

    /**
     * Форма редактирования студента
     *
     * @param id  - студента
     * @param map
     * @return форма
     */
    @RequestMapping(value = "student/{id}", method = RequestMethod.GET)
    public String editStudentForm(@PathVariable("id") Integer id, ModelMap map) {
        map.addAttribute("type", "Режим редактирования студента");
        map.addAttribute("student", studentService.getStudent(id));
        map.addAttribute("count", studentService.countLesson(id));
        return "student";
    }

    /**
     * Сохранение студента
     *
     * @param student Студент
     * @param map
     * @return msg
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "saveStudent", method = RequestMethod.POST)
    public String saveStudent(Student student, ModelMap map) {
        studentService.saveStudent(student);
        map.put("message", "Студент сохранен!");
        return "success";
    }

    /**
     * Удаление студента
     *
     * @param id студента
     * @param map
     * @return msg
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "deleteStd/{id}", method = RequestMethod.POST)
    public String deleteStudent(@PathVariable("id") Integer id, ModelMap map) {
        studentService.deleteStudent(id);
        map.put("message", "Студент удален!");
        return "success";
    }

}
