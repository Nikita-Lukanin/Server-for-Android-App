package server.controller;

import server.entity.JsonObject;
import server.entity.Users;
import server.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServerController {

    @Autowired
    private ServerService service; // Access to DataBase

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject addUser(@RequestBody Users user) {
        return service.addUser(user);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    @ResponseBody
    public void updateUser(@RequestBody JsonObject jsonObject) {
        service.updateUser(jsonObject);
    }

    @RequestMapping(value = "/addConnection", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject addConnection(@RequestBody JsonObject jsonObject){
        return service.addConnection(jsonObject);
    }

    @RequestMapping(value = "/checkConnections", method = RequestMethod.POST)
    @ResponseBody
    public List<JsonObject> checkConnections(@RequestBody JsonObject jsonObject){
        return service.checkConnections(jsonObject);
    }

    @RequestMapping(value = "/confirmLink", method = RequestMethod.PUT)
    @ResponseBody
    public void verifiConnection(@RequestBody JsonObject jsonObject){
        service.verifiConnection(jsonObject);
    }

    @RequestMapping(value = "/getInfo", method = RequestMethod.POST)
    @ResponseBody
    public List<JsonObject> getInfo(@RequestBody List<JsonObject> jsonObjectList){
        return service.getInfo(jsonObjectList);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteConnection(@RequestBody JsonObject jsonObject){
        service.deleteConnection(jsonObject);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test() { return "Connect Success"; }
}
