package com.spark.platform.common.security.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.spark.platform.common.base.enums.SparkHttpStatus;

import java.io.IOException;

/**
 * @author: wangdingfeng
 * @Date: 2020/6/13 19:03
 * @Description:
 */
public class SparkOAuthExceptionJacksonSerializer extends StdSerializer<SparkOAuth2Exception> {

    protected SparkOAuthExceptionJacksonSerializer() {
        super(SparkOAuth2Exception.class);
    }

    @Override
    public void serialize(SparkOAuth2Exception value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("code", SparkHttpStatus.COMMON_FAIL.getCode());
        gen.writeStringField("msg", value.getMessage());
        gen.writeStringField("data", value.getOAuth2ErrorCode());
        gen.writeEndObject();
    }
}
