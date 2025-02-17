package com.socialnetwork.service;

import com.socialnetwork.handler.ResponseBuilder;
import com.socialnetwork.model.Response;
import com.socialnetwork.model.SocialNetwork;
import com.socialnetwork.persistence.SocialNetworkDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class SocialNetworkService {
    Logger logger = LoggerFactory.getLogger(SocialNetworkService.class);
    @Autowired
    private SocialNetworkDAO socialNetworkDAO;
    @Autowired
    private ResponseBuilder builder;

    @PostMapping("/createFriendship")
    public Response createFriendship(@RequestBody SocialNetwork friendshipRequest) {
        return builder.createResponse(socialNetworkDAO.createFriendShip(friendshipRequest));
    }
    @PostMapping("/removeFriendship")
    public Response removeFriendship(@RequestBody SocialNetwork friendshipRequest) {
        return builder.createResponse(socialNetworkDAO.removeFriendShip(friendshipRequest));
    }
    @PostMapping("/listFriends")
    public List listFriends(@RequestBody SocialNetwork friendshipRequest) {
        return socialNetworkDAO.listFriends(friendshipRequest);
    }
    @PostMapping("/calculateDegreeCentrality")
    public Response calculateDegreeCentrality(@RequestBody SocialNetwork friendshipRequest) {
        int degreeOfCentrality=socialNetworkDAO.calculateDegreeCentrality(friendshipRequest);
        String message= String.format("Degree of centrality for user is {%s}",degreeOfCentrality);
        return builder.createResponse(message);
    }
    @PostMapping("/findShortestPath")
    public Response findShortestPath(@RequestBody SocialNetwork friendshipRequest) {
        int distance=socialNetworkDAO.shortestPath(friendshipRequest);
        String message= String.format("Shortest distance between  users is {%s}",distance);
        return builder.createResponse(message);
    }
    @GetMapping("/identifyCommunities")
    public Map<String, List<String>> getCommunities() {
        return socialNetworkDAO.getCommunities();

    }

}
