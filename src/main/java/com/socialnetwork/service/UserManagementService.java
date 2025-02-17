package com.socialnetwork.service;

import com.socialnetwork.handler.ResponseBuilder;
import com.socialnetwork.model.Response;
import com.socialnetwork.model.User;
import com.socialnetwork.persistence.UserManagementDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserManagementService {
    Logger logger = LoggerFactory.getLogger(UserManagementService.class);
    @Autowired
    private UserManagementDAO userMgntDAO;
    @Autowired
    private ResponseBuilder builder;

    @PostMapping("/addUser")
    public Response createUser(@RequestBody User user) {
        return builder.createResponse(userMgntDAO.createUser(user));
    }
    @PostMapping("/removeUser")
    public Response removeUser(@RequestBody User user) {
        return builder.createResponse(userMgntDAO.removeUser(user));
    }

    @GetMapping("/listUsers")
    public List removeUser() {
        Response response = new Response();
        return userMgntDAO.listUser();

    }
/*
    @PostMapping("/createFreindship")
    public Response insertPenalityPayment(@RequestBody Penalities penalities) {
        Response response = new Response();
        if (penalities.getAmount() < 0) {
            Optional<Penalities> penality = userMgntDAO.getPenalities(penalities.getStudentId());
            if (penality.isPresent()) {
                Penalities originalPenality = penality.get();
                double existingPenality = originalPenality.getAmount();
                double newPenality = existingPenality + penalities.getAmount();
                originalPenality.setAmount(newPenality);
                response.setResponseMessage(userMgntDAO.insertPenalities(originalPenality));
            }

        } else {
            response.setResponseMessage(userMgntDAO.insertPenalities(penalities));
        }
        return response;
    }*/
}
