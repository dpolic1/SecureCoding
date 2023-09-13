package com.aiis.project.serialization;

import com.aiis.project.model.Order;
import com.aiis.project.request.ArticleRequest;
import com.aiis.project.request.UserRequest;
public class SerializationImpl {

    private final String articleFilePath = "serializedClasses/article.ser";
    private final String userFilePath = "serializedClasses/user.ser";

    private final String orderFilePath = "serializedClasses/order.ser";

    public void serialize() {
        ArticleRequest articleRequest = new ArticleRequest();
        UserRequest userRequest = new UserRequest();

        Order order = new Order();

        Serializer.serialize(articleRequest, articleFilePath);
        Serializer.serialize(userRequest, userFilePath);
        //Serializer.serialize(order, orderFilePath);
    }

    public void deserialize() {
        ArticleRequest articleRequest = (ArticleRequest) Serializer.deserialize(articleFilePath);
        UserRequest userRequest = (UserRequest) Serializer.deserialize(userFilePath);
        //Order order = (Order) Serializer.deserialize(orderFilePath);
    }
}
