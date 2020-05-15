package com.ss.ServerInterface;

import com.ss.pojo.Person;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

public interface InsertData {

    @POST
    @Path("/insert")
    void insert (int id,String name,String address);

}
