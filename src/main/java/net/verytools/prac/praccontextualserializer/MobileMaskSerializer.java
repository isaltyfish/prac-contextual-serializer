package net.verytools.prac.praccontextualserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class MobileMaskSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private int maskCount;

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (!StringUtils.hasText(s)) {
            jsonGenerator.writeString(s);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maskCount; i++) {
            sb.append("*");
        }
        jsonGenerator.writeString(sb.toString() + s.substring(maskCount));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) {
        MobileMask mobileMask = beanProperty.getAnnotation(MobileMask.class);
        MobileMaskSerializer ret = new MobileMaskSerializer();
        ret.setMaskCount(mobileMask.masks());
        return ret;
    }

    public void setMaskCount(int maskCount) {
        this.maskCount = maskCount;
    }
}
