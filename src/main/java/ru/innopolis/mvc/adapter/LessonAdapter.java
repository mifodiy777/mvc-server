package ru.innopolis.mvc.adapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.innopolis.mvc.entity.Lesson;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

/**
 * Created by Кирилл on 01.11.2016.
 */
public class LessonAdapter implements JsonSerializer<Lesson> {

    @Override
    public JsonElement serialize(Lesson src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject jsonObject = new JsonObject();
        if (src != null) {
            jsonObject.addProperty("id", src.getId());
            jsonObject.addProperty("topic", src.getTopic());
            jsonObject.addProperty("description", src.getDescription());
            jsonObject.addProperty("duration", src.getDuration());
            jsonObject.addProperty("date_lesson", new SimpleDateFormat("dd.MM.yyyy").format(src.getDateLesson()));


        }
        return jsonObject;
    }
}
