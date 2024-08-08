package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import Model.Account;
import Model.Message;
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
        app.get("/login", this::getAccountByIdHandler);
        app.post("/messages", this::postMessageHandler);
        app.get("/messages", this::getMessageHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.patch("/messages/{message_id}", this::patchMessageByIdHandler); 
        app.get("/accounts/{account_id}/messages", this::getAllAccountMsgByIdHandler);        
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        
        if((account.getUsername() == "") || (account.getPassword().length() < 4 || (service.getAccount(account) != null))){
            ctx.status(400);
        } else {
                Account newAccount = service.addAccount(account);
                ctx.json(mapper.writeValueAsString(newAccount));
        }
    }

    private void loginAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account fetchedAccount = service.getAccount(account);
        if(fetchedAccount == null){
            ctx.status(401);
        } else {
            ctx.json(service.getAccount(account));
        }
    }

    private void postMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Account fetchedAccount = service.getAccountById(message.getPosted_by());

        if((message.getMessage_text() == "") || (message.getMessage_text().length() > 255) || fetchedAccount == null){
            ctx.status(400);
        } else {
                Message newMessage = service.postMessage(message);
                ctx.json(mapper.writeValueAsString(newMessage));
        }
    }

    private void getMessageHandler(Context ctx) {
        ctx.json(service.getAllMessages());
    }

    private void getMessageByIdHandler (Context ctx){
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message fetchedMessage = service.getMessageById(message_id);
        if(fetchedMessage == null){
            ctx.json("");
        } else {
        ctx.json(service.getMessageById(message_id));
        }
    }

    private void getAccountByIdHandler (Context ctx){
        int account_id = Integer.parseInt(ctx.pathParam("account_id"));
        Account fetchedAccount = service.getAccountById(account_id);
        if(fetchedAccount == null){
            ctx.json("");
        } else {
        ctx.json(service.getAccountById(account_id));
        }
    }

    private void deleteMessageByIdHandler(Context ctx) {
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message deletedMessage = service.deleteMessageById(message_id);
        if(deletedMessage == null){
            ctx.json("");
        } else {
        ctx.json(service.deleteMessageById(message_id));
        }
    }

    private void patchMessageByIdHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        String message_text = message.getMessage_text();//ctx.pathParam("message_text");
        Message fetchedMessage = service.getMessageById(message_id);

        if (message_text == "" || message_text.length() > 255 || fetchedMessage == null){
            ctx.status(400);
        } else {
            ctx.json(service.patchMessageById(message_id, message));
        }
    }

    private void getAllAccountMsgByIdHandler(Context ctx) {
        int account_id = Integer.parseInt(ctx.pathParam("account_id"));
        ctx.json(service.getAllAccountMsgById(account_id));
    }
}