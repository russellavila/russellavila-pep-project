package Service;

import Model.Account;
import Model.Message;
import java.util.List;
import DAO.*;

public class MediaService {

    SocialMediaDao socialMediaDao;

    public MediaService(){
        socialMediaDao = new SocialMediaDao();
    }

    public Account addAccount(Account account){
        return socialMediaDao.addAccount(account);
    }

    public Account getAccount(Account account){
        return socialMediaDao.getAccount(account);
    }

    public Message postMessage (Message message){
        return socialMediaDao.postMessage(message);
    }

    public List<Message> getAllMessages(){
        return socialMediaDao.getAllMessages();
    }

    public Message getMessageById(int id){
        return socialMediaDao.getMessageById(id);
    }

    public Account getAccountById(int id){
        return socialMediaDao.getAccountById(id);
    }

    public Message deleteMessageById(int id){
        return socialMediaDao.getMessageById(id);
    }

    public Message patchMessageById(int id, Message message){
        return socialMediaDao.patchMessageById(id, message);
    }
    
    public List <Message> getAllAccountMsgById(int id){
        return socialMediaDao.getAllAccountMsgById(id);
    }
}
