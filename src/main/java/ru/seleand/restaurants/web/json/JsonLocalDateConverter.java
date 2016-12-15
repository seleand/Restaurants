package ru.seleand.restaurants.web.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.seleand.restaurants.util.TimeUtil;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by Asus on 15.12.2016.
 */
public class JsonLocalDateConverter {
    public static class LocalDateSerializer extends JsonSerializer<LocalDate> {
        @Override
        public void serialize(LocalDate ld, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeString(TimeUtil.toString(ld));
        }

        @Override
        public Class<LocalDate> handledType() {
            return LocalDate.class;
        }
    }

    public static class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
            return TimeUtil.parseLocalDate(jp.getText());
        }

        @Override
        public Class<LocalDate> handledType() {
            return LocalDate.class;
        }
    }

}
