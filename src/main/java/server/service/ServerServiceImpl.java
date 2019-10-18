package server.service;

import server.entity.Connections;
import server.entity.JsonObject;
import server.entity.Users;
import server.repository.ConnectionsRepository;
import server.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ServerServiceImpl implements ServerService{

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ConnectionsRepository connectionsRepository;

    public JsonObject addUser(Users users) {
        users.setKey(passGenerate());
        Users u = usersRepository.saveAndFlush(users);
        return new JsonObject(u.getId(), u.getKey());
    }

    public void updateUser(JsonObject jsonObject) {
        long json_id = jsonObject.getId();
        String json_uuid = jsonObject.getUuid();
        String firstName = jsonObject.getFirstName();
        String lastName = jsonObject.getLastName();
        double latitude = jsonObject.getLatitude();
        double longitude = jsonObject.getLongitude();

        try{
            Users u = usersRepository.findOne(json_id);
            String user_uuid = u.getUuid();

            if (json_uuid.equals(user_uuid)){
                u.setFirstName(firstName);
                u.setLastName(lastName);
                u.setLatitude(latitude);
                u.setLongitude(longitude);
                usersRepository.saveAndFlush(u);
            }
        }catch (Exception e){}
    }

    public JsonObject addConnection(JsonObject jsonObject) {

        long json_id = jsonObject.getId();
        String json_uuid = jsonObject.getUuid();
        String json_key = jsonObject.getKey();

        try{
            long user_id = usersRepository.findBykey(json_key).getId();
            String user_uuid = usersRepository.findOne(json_id).getUuid();

            if ((json_uuid.equals(user_uuid)) && (user_id != 0L)) {
                long id_connection = connectionsRepository.saveAndFlush(
                        new Connections(user_id,
                                    json_id,
                                    json_key,
                                0)).getId();
                return new JsonObject(id_connection);
            }else
                return null;
        }catch(Exception e){
            return null;
        }
    }

    public List<JsonObject> checkConnections(JsonObject jsonObject){
        long json_id = jsonObject.getId();
        String json_uuid = jsonObject.getUuid();
        String json_key = jsonObject.getKey();

        try{
            Users u = usersRepository.findOne(json_id);
            String user_uuid = u.getUuid();
            String user_key = u.getKey();
            List<Connections> connectionsList = connectionsRepository.findByConn(json_id);

            if ((json_uuid.equals(user_uuid)) && (json_key.equals(user_key))) {
                ArrayList<JsonObject> jsonList = new ArrayList<JsonObject>();
                for(Connections c: connectionsList) {
                    Users invitation = usersRepository.findOne(c.getInvitation_id());
                    jsonList.add(new JsonObject(c.getId(), invitation.getFirstName(), invitation.getLastName()));
                }
                return jsonList;
            }else
                return null;
        }catch(Exception e){
            return null;
        }
    }

    public void verifiConnection(JsonObject jsonObject) {
        long json_id = jsonObject.getId();
        String json_uuid = jsonObject.getUuid();
        String json_key = jsonObject.getKey();
        String json_permission = jsonObject.getPermission();
        long json_id_connection = jsonObject.getId_connection();

        try {
            Users u = usersRepository.findOne(json_id);
            long user_id = u.getId();
            String user_uuid = u.getUuid();
            String user_key = u.getKey();

            Connections c = connectionsRepository.findOne(json_id_connection);
            String connection_key = c.getKey();

            if ((json_uuid.equals(user_uuid)) && (json_key.equals(user_key)) && (json_key.equals(connection_key))) {
                if(json_permission.equals("true")){
                    c.setPermission(1);
                    connectionsRepository.saveAndFlush(c);
                }else
                    connectionsRepository.delete(json_id_connection);
            }

        }catch (Exception e){}
    }

    public List<JsonObject> getInfo(List<JsonObject> jsonObjectList) {

        ArrayList<JsonObject> answerList = new ArrayList<JsonObject>();
        try {
            for (JsonObject jsonObject : jsonObjectList) {
                long json_id = jsonObject.getId();
                String json_uuid = jsonObject.getUuid();
                String json_key = jsonObject.getKey();
                long json_id_connection = jsonObject.getId_connection();

                Users u = usersRepository.findOne(json_id);
                long user_id = u.getId();
                String user_uuid = u.getUuid();

                Connections c = connectionsRepository.findOne(json_id_connection);
                String connection_key = c.getKey();
                int connection_p = c.getPermission();
                long connection_user = c.getUser_id();
                long connection_invitation = c.getInvitation_id();

                if ((json_uuid.equals(user_uuid)) && (json_key.equals(connection_key)) && (connection_p == 1)) {

                    if (user_id == connection_user) {
                        Users invitation = usersRepository.findOne(c.getInvitation_id());
                        answerList.add(new JsonObject(c.getId(), invitation.getFirstName(), invitation.getLastName(), invitation.getLatitude(), invitation.getLongitude()));
                    } else if (user_id == connection_invitation) {
                        Users user = usersRepository.findOne(c.getUser_id());
                        answerList.add(new JsonObject(c.getId(), user.getFirstName(), user.getLastName(), user.getLatitude(), user.getLongitude()));
                    }
                }
            }
            return answerList;
        }catch(Exception e){
            return null;
        }
    }

    public void deleteConnection(JsonObject jsonObject) {
        long json_id = jsonObject.getId();
        String json_uuid = jsonObject.getUuid();
        String json_key = jsonObject.getKey();
        long json_id_connection = jsonObject.getId_connection();

        try {
            Users u = usersRepository.findOne(json_id);
            long user_id = u.getId();
            String user_uuid = u.getUuid();
            String user_key = u.getKey();

            Connections c = connectionsRepository.findOne(json_id_connection);
            String connection_key = c.getKey();

            if ((json_uuid.equals(user_uuid)) && (json_key.equals(connection_key))) {
                    connectionsRepository.delete(json_id_connection);
            }
        }catch (Exception e){}
    }

    public static String passGenerate() {
        String pass  = "";
        Random r     = new Random();

        for (int i = 0; i < 8; ++i) {
            char next = 0;
            int range = 10;

            switch(r.nextInt(3)) {
                case 0: {next = '0'; range = 10;} break;
                case 1: {next = 'a'; range = 26;} break;
                case 2: {next = 'A'; range = 26;} break;
            }

            pass += (char)((r.nextInt(range)) + next);
        }

        return pass;
    }
}
