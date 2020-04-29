package com.spark.platform.common.security.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.spark.platform.common.base.constants.GlobalsConstants;
import com.spark.platform.common.security.exception.SparkAuth2Exception;

import java.io.IOException;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.security.support
 * @ClassName: SparkAuth2ExceptionSerializer
 * @Author: wangdingfeng
 * @Description: oauth2 异常序列化 注意请使用spring cloud的稳定版本 否则翻译失效
 * @Date: 2020/4/29 16:24
 * @Version: 1.0
 */
public class SparkAuth2ExceptionSerializer extends StdSerializer<SparkAuth2Exception> {

    public SparkAuth2ExceptionSerializer() {
        super(SparkAuth2Exception.class);
    }

    @Override
    public void serialize(SparkAuth2Exception value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("code", GlobalsConstants.FAIL);
        gen.writeStringField("msg", value.getMessage());
        gen.writeStringField("data", value.getErrorCode());
        gen.writeEndObject();
    }
}
