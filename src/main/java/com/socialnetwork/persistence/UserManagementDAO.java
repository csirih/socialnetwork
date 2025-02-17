package com.socialnetwork.persistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCursor;
import com.socialnetwork.model.User;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Component
public class UserManagementDAO {

@Autowired
    MongoClient mongoClient;

    public String createUser(User user) {

        try {
            Document userDocument = new Document("name", user.getName());
            userDocument.append("userId",user.getUserId()).append("email",user.getEmail());
            mongoClient.getDatabase("socialnetwork").getCollection("users").insertOne(userDocument);
        } catch (Exception ex) {
            return "User creation has failed";
        }
        return "Successfully created user";
    }
    public String removeUser(User user) {

        try {

            mongoClient.getDatabase("socialnetwork").getCollection("users").deleteOne(eq("userId", user.getUserId()));
        } catch (Exception ex) {
            return "User creation has failed";
        }
        return "Successfully created user";
    }
    public List listUser() {

        List userList = new ArrayList<>();
        try {
            MongoCursor<Document> cursor=mongoClient.getDatabase("socialnetwork").getCollection("users").find().iterator();

            while (cursor.hasNext()) {
                userList.add(cursor.next().get("name"));
            }
        } catch (Exception ex) {

        }
        return  userList;
    }

}
