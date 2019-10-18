package server.service;

import server.entity.JsonObject;
import server.entity.Users;

import java.util.List;

public interface ServerService {
     JsonObject addUser(Users users);
     void updateUser(JsonObject jsonObject);
     JsonObject addConnection(JsonObject jsonObject);
     List<JsonObject> checkConnections(JsonObject jsonObject);
     void verifiConnection(JsonObject jsonObject);
     List<JsonObject> getInfo(List<JsonObject> jsonObjectList);
     void deleteConnection(JsonObject jsonObject);
}
