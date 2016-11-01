package ru.innopolis.mvc;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.nio.charset.Charset;

/**
 * Created by IntelliJ IDEA.
 * User: Mahankov
 * Date: 13.05.2015
 * Time: 15:28:50
 * To change this template use File | Settings | File Templates.
 */
public class Utils {  

    /**
     * Преобразование коллекции элементов в JSON массив с именем aaData для таблиц
     */
    public static ResponseEntity<String> convertListToJson(GsonBuilder gson, Iterable collection) {
        gson.serializeNulls();
        JsonObject obj = new JsonObject();
        obj.add("aaData", gson.create().toJsonTree(collection));
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));
        return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.OK);
    }

    /**
     * Создание JSON ответа
     */
    public static ResponseEntity<String> createJsonResponse(GsonBuilder gson, Object obj) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));
        return new ResponseEntity<String>(gson.create().toJson(obj), responseHeaders, HttpStatus.OK);
    }

     /**
     * Метод парсит число в переданной строке.
     *
     * @param paramValue Строка, которую необходимо превратить в число.
     * @return Число, если переданная строка являлась числом и null,
     *         если переданную строку невозможно распарсить.
     */
    public static Integer parseInteger(String paramValue) {
        try {
            return Integer.valueOf(paramValue);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    
}
