package org.shop.utils;

import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.shop.model.Shop;

@UtilityClass
public class Protobuf {

    @SneakyThrows
    public Object fromJson(Response response) {
        Message.Builder message = Shop.Album.newBuilder();
        JsonFormat.parser().ignoringUnknownFields().merge(response.asString(), message);
        return message.build();
    }

    @SneakyThrows
    public String toJson(MessageOrBuilder body) {
        return JsonFormat.printer().print(body);
    }


}
