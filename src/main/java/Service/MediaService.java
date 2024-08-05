package Service;

import Model.Account;
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
    
}
