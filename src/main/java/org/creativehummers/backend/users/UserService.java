package org.creativehummers.backend.users;

import lombok.extern.java.Log;
import org.creativehummers.authentication.CurrentUser;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Log
public class UserService implements Serializable {

    private static volatile UserService INSTANCE ;
    private static Object mutex = new Object();

    private UserService() {
    }

    public static UserService getInstance(){
        UserService result = INSTANCE;
        if(INSTANCE == null){
            synchronized (mutex) {
                result = INSTANCE;
                if (result == null)
                    INSTANCE = new UserService();
            }
        }
        return result;
    }


    public void store(User userDetails) throws ServiceException {

        String uuid_prefix = "USER_";
//Insert into healthsys_user ( uuid, first_name, last_name, userid , passcode , phone_number, email_id, gender,date_of_birth, user_profile_code , country, state, city, create_time,  create_user_id, comment)
        String[] params = new String[] { uuid_prefix, userDetails.getFirst_name(), userDetails.getLast_name(), userDetails.getUserid(), userDetails.getPasscode(),
                userDetails.getPhone_number(), userDetails.getEmail_id(), userDetails.getGender(), userDetails.getDate_of_birth().toString(),  "USPR_2022_DEC_XRFE_A129_XE12",
                userDetails.getCountry(), userDetails.getState(), userDetails.getCity(), "Signup User Creation"};
        String[][] resultArray = new String[2][1];
        resultArray[0][0]="SUCCESS";
        resultArray[1][0]="1";
        String updateCount;
        try {
            updateCount = resultArray[0][0];
            if(updateCount.contains("ERROR"))
                throw new ServiceException(updateCount);
        } catch( Exception e){
            log.severe(" Error executing or unmarshalling result: " + e.getMessage() );
            throw new ServiceException( e.getMessage() );
        }
    }
    public String validateUserid(String userid) {

        if (userid == null) {
            return "Handle can't be empty";
        }
        if (userid.length() < 4 || userid.length() > 24) {
            return "Handle can't be shorter than 4 characters or > 24 characters";
        }
        List<String> reservedHandles = Arrays.asList("admin", "test", "null", "void");
        if (reservedHandles.contains(userid) || reservedHandles.stream().anyMatch( e -> userid.contains(e))) {
            return String.format("'%s' is not available as a userid", userid);
        }

        return null;
    }


    public static class ServiceException extends Exception {
        public ServiceException(String msg) {
            super(msg);
        }
    }
}
