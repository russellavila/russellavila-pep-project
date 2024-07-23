package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Service.MediaService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    MediaService service = new MediaService();
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postAccountHandler);
        app.post("/login", this::loginAccountHandler);
        app.post("/messages", this::postMessageHandler);
        app.get("/messages", this::getMessageHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler); 
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.patch("/messages/{message_id}", this::patchMessageByIdHandler); 
        app.get("/account/{account_id}", this::getAllAccountMsgByIdHandler);       
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account newAccount = service.addAccount(account);
        if((newAccount.getUsername() == "") || (newAccount.getPassword().length() < 4) || (newAccount == null)){
            ctx.status(400);
        } else {
            ctx.json(mapper.writeValueAsString(newAccount));
        }
    }

    private void loginAccountHandler(Context context) {
        context.json("sample text");
    }

    private void postMessageHandler(Context context) {
        context.json("sample text");
    }

    private void getMessageHandler(Context context) {
        context.json("sample text");
    }

    private void getMessageByIdHandler(Context context) {
        context.json("sample text");
    }

    private void deleteMessageByIdHandler(Context context) {
        context.json("sample text");
    }

    private void patchMessageByIdHandler(Context context) {
        context.json("sample text");
    }

    private void getAllAccountMsgByIdHandler(Context context) {
        context.json("sample text");
    }


}