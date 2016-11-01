package ru.innopolis.mvc.controller;

import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.mvc.Utils;
import ru.innopolis.mvc.adapter.LessonAdapter;
import ru.innopolis.mvc.editor.DateCustomEditor;
import ru.innopolis.mvc.entity.Lesson;
import ru.innopolis.mvc.service.LessonService;
import ru.innopolis.mvc.service.StudentService;

import java.util.Date;

/**
 * Created by Кирилл on 01.11.2016.
 */
@Controller
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private StudentService studentService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateCustomEditor());
    }

    /**
     * Получение страницы занятий
     *
     * @return
     */
    @RequestMapping(value = "lessonPage", method = RequestMethod.GET)
    public String getLessonPage() {
        return "lessons";
    }

    /**
     * Получение всех занятий
     *
     * @return
     */
    @RequestMapping(value = "allLessons", method = RequestMethod.GET)
    public ResponseEntity<String> getLessons() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Lesson.class, new LessonAdapter());
        return Utils.convertListToJson(gsonBuilder, lessonService.getLessonList());
    }

    /**
     * Форма создания занятия
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "lesson", method = RequestMethod.GET)
    public String addLessonPage(ModelMap map) {
        map.addAttribute("type", "Режим добавления занятия");
        map.addAttribute("lesson", new Lesson());
        map.addAttribute("student-list", studentService.getStudentList());
        return "lesson";
    }

    /**
     * ФОРМА РЕДАКТИРОВАНИЯ ЗАНЯТИЯ
     *
     * @param id  - занятия
     * @param map
     * @return
     */
    @RequestMapping(value = "lesson/{id}", method = RequestMethod.GET)
    public String editLessonForm(@PathVariable("id") Integer id, ModelMap map) {
        map.addAttribute("type", "Режим редактирования занятия");
        map.addAttribute("student-list", studentService.getStudentList());
        map.addAttribute("lesson", lessonService.getLesson(id));
        return "lesson";
    }

    /**
     * ФОРМА добавления студентов к занятиям
     *
     * @param idLesson - занятия
     * @param map
     * @return
     */
    @RequestMapping(value = "putStudent/{id}", method = RequestMethod.GET)
    public String putStudentForm(@PathVariable("id") Integer idLesson, ModelMap map) {
        map.addAttribute("type", "Режим добавления студентов к занятиям");
        map.addAttribute("lesson", lessonService.getLesson(idLesson));
        map.addAttribute("studentList", studentService.getStudentListIsNotLesson(idLesson));
        return "putStudent";
    }

    /**
     * Сохранение занятия
     *
     * @param idLesson Занятие
     * @param idStudent Студент
     * @param map
     * @return
     */
    @RequestMapping(value = "putSaveStudent", method = RequestMethod.POST)
    public String putSaveStudent(@RequestParam("id") Integer idLesson,
                                 @RequestParam("student") Integer idStudent, ModelMap map) {
        lessonService.putStudent(idLesson, idStudent);
        map.put("message", "Студент добавлен к занятию!");
        return "success";
    }

    /**
     * Сохранение занятия
     *
     * @param lesson Занятие
     * @param map
     * @return
     */
    @RequestMapping(value = "saveLesson", method = RequestMethod.POST)
    public String saveLesson(Lesson lesson, ModelMap map) {
        lessonService.addLesson(lesson);
        map.put("message", "Занятие сохранено!");
        return "success";
    }

    /**
     * Удаление занятия
     *
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "deleteLesson/{id}", method = RequestMethod.POST)
    public String deleteLesson(@PathVariable("id") Integer id, ModelMap map) {
        lessonService.deleteLesson(id);
        map.put("message", "Занятие удалено!");
        return "success";
    }
}
