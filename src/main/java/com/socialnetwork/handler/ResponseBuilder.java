package com.socialnetwork.handler;

import com.socialnetwork.model.Response;
import org.springframework.stereotype.Component;

import static com.socialnetwork.common.Constants.*;

@Component
public class ResponseBuilder {
    Response response= new Response();
    public Response createResponse(String status){
        response.setResponseMessage(status);

        return response;
    }

}
