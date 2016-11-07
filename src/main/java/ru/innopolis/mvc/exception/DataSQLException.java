package ru.innopolis.mvc.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.mvc.controller.LessonController;

/**
 * Класс иключения выбрасываемого при любых ошибках в DAO методах
 * Created by Кирилл on 07.11.2016.
 */
public class DataSQLException extends Exception {

    private static Logger logger = LoggerFactory.getLogger(DataSQLException.class);


    public DataSQLException(String message) {
        super(message);
        logger.error(message, this);
    }

    public DataSQLException() {
    }
}
