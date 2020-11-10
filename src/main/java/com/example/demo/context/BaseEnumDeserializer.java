package com.example.demo.context;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author hushunjian
 */
@Slf4j
public class BaseEnumDeserializer extends JsonDeserializer<Enum> {

    /**
     * 平台项目中的反序列方法都统一实现方法：fromKey(int key)
     */
    private final static String METHOD = "fromValue";

    @Override
    @SuppressWarnings("unchecked")
    public Enum deserialize(JsonParser jp, DeserializationContext context) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        Class clazz = BeanUtils.findPropertyType(jp.currentName(), jp.getCurrentValue().getClass());
        Enum result = null;
        // 用于前端传key,后台转换成枚举
        try {
            Method fromKey = clazz.getMethod(METHOD, Integer.class);
            // 有两种传值,一种是直接传 key, 另一种是 key-value
            Integer key = node.canConvertToInt() ? node.asInt() : node.get("value").asInt();
            // 调用fromKey方法获取
            result = (Enum) fromKey.invoke(clazz.getEnumConstants()[0], key);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error("could not found Enum by error node : " + node.toString() + ", by clazz : " + clazz.getName());
            log.error(Throwables.getStackTraceAsString(e));
        }
        return result;
    }
}
