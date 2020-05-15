package com.ss.ServerImpl;

        import com.ss.ServerInterface.InsertData;
        import com.ss.pojo.Person;
        import org.springframework.stereotype.Controller;

        import javax.ws.rs.POST;
        import javax.ws.rs.Path;
        import javax.ws.rs.PathParam;
        import javax.ws.rs.QueryParam;

@Path("/insertClass")
public class InsertImpl implements InsertData {

    @Path("/insert")
    @POST
    @Override
    public void insert(@QueryParam("id")int id, @QueryParam("name")String name, @QueryParam("address")String address) {

        System.out.println("id:" + id + " name:"+name + " address" + address);
        System.out.println("aaa");

    }
}
