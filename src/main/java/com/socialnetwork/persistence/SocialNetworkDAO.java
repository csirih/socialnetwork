package com.socialnetwork.persistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCursor;
import com.socialnetwork.handler.SocialNetworkHelper;
import com.socialnetwork.model.SocialNetwork;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

@Component
public class SocialNetworkDAO {
    @Autowired
    MongoClient mongoClient;
    @Autowired
    SocialNetworkHelper helper;

    public String createFriendShip(SocialNetwork friendshipRequest) {

        try {
            Document friendshipDocument = new Document("user", friendshipRequest.getUserId1());
            friendshipDocument.append("friend",friendshipRequest.getUserId2()).append("weight",1);
            mongoClient.getDatabase("socialnetwork").getCollection("friendship").insertOne(friendshipDocument);
        } catch (Exception ex) {
            return "User creation has failed";
        }
        return "Successfully created user";
    }
    public String removeFriendShip(SocialNetwork friendshipRequest) {

        try {
            mongoClient.getDatabase("socialnetwork").getCollection("friendship").deleteOne(and(eq("user", friendshipRequest.getUserId1()),eq("friend", friendshipRequest.getUserId2())));
        } catch (Exception ex) {
            return "User creation has failed";
        }
        return "Successfully created user";
    }
    public List listFriends(SocialNetwork friendshipRequest) {
        List friendsList = new ArrayList<>();
        try {
            MongoCursor<Document> cursor=mongoClient.getDatabase("socialnetwork").getCollection("friendship").find(eq("user", friendshipRequest.getUserId1())).iterator();

            while (cursor.hasNext()) {
                MongoCursor<Document> userCursor=mongoClient.getDatabase("socialnetwork").getCollection("users").find(eq("userId", cursor.next().get("friend"))).iterator();
                friendsList.add(userCursor.next().get("name"));
            }
        } catch (Exception ex) {

        }
        return  friendsList;
    }
   public int calculateDegreeCentrality(SocialNetwork input){
        List connectionList = new ArrayList<>();
        try {
            MongoCursor<Document> socialNetworkCursor=mongoClient.getDatabase("socialnetwork").getCollection("friendship").find().iterator();
            while(socialNetworkCursor.hasNext()){

                SocialNetwork network= new SocialNetwork();
                Document user =socialNetworkCursor.next();

                network.setUserId1((String) user.get("user"));
                network.setUserId2((String) user.get("friend"));
                connectionList.add(network);
            }
           return helper.calculateDegreeCentrality(connectionList,input.getUserId1());
        } catch (Exception ex) {
            return 0;
        }


    }
    public int shortestPath(SocialNetwork input){
        List connectionList = new ArrayList<>();
        try {
            MongoCursor<Document> socialNetworkCursor=mongoClient.getDatabase("socialnetwork").getCollection("friendship").find().iterator();
            while(socialNetworkCursor.hasNext()){

                SocialNetwork network= new SocialNetwork();
                Document user =socialNetworkCursor.next();

                network.setUserId1((String) user.get("user"));
                network.setUserId2((String) user.get("friend"));
                connectionList.add(network);
            }
           return helper.findShortestPath(connectionList,input.getUserId1(),input.getUserId2());
        } catch (Exception ex) {
            return -1;
        }


    }
    public Map<String, List<String>> getCommunities(){
        List connectionList = new ArrayList<>();
        try {
            MongoCursor<Document> socialNetworkCursor=mongoClient.getDatabase("socialnetwork").getCollection("friendship").find().iterator();
            while(socialNetworkCursor.hasNext()){

                SocialNetwork network= new SocialNetwork();
                Document user =socialNetworkCursor.next();

                network.setUserId1((String) user.get("user"));
                network.setUserId2((String) user.get("friend"));
                connectionList.add(network);
            }
        return helper.findConnections(connectionList);
        } catch (Exception ex) {
            return new HashMap<>();
        }

    }
}
