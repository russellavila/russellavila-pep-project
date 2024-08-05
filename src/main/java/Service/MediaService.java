package Service;

import Model.Account;
import Model.Message;
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
    
}
