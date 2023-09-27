package net.verytools.prac.praccontextualserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MobileMaskSerializer2 extends JsonSerializer<String> implements ContextualSerializer {

    private int maskCount;
    private static final Map<Integer, MobileMaskSerializer2> serializerMap = new ConcurrentHashMap<>();
    private static final Logger log = LoggerFactory.getLogger(MobileMaskSerializer2.class);

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
        int masks = mobileMask.masks();
        MobileMaskSerializer2 serializer = serializerMap.get(masks);
        if (serializer != null) {
            log.info("serializer reused mask count: {}", masks);
            return serializer;
        }
        MobileMaskSerializer2 ret = new MobileMaskSerializer2();
        ret.setMaskCount(mobileMask.masks());
        serializerMap.putIfAbsent(masks, ret);
        return ret;
    }

    public void setMaskCount(int maskCount) {
        this.maskCount = maskCount;
    }
}
