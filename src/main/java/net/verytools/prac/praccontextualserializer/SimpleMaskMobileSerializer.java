package net.verytools.prac.praccontextualserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class SimpleMaskMobileSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (!StringUtils.hasText(s)) {
            jsonGenerator.writeString(s);
            return;
        }
        jsonGenerator.writeString("****" + s.substring(4));
    }
}
