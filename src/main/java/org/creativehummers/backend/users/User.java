package org.creativehummers.backend.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

/**
 * Main Bean class that we build the form for.
 * <p>
 * Uses Bean Validation (JSR-303) annotations for automatic validation.
 */
@Data
public class User {

    @Size(min = 8, max = 64)
    private String userid;
    //FIXME Passwords should never be stored in plain text!
    @Size(min = 8, max = 64)
    private String passcode;
    @NotNull
    @Size(min = 1, max = 32)
    private String first_name;
    @NotNull @Size(min = 1, max = 32) 
    private String last_name;
    @Email
    private String email_id;
    @NotNull @Size(min = 12, max = 18)//[+][country code]-[area OR provider code]-[subscriber number]   viz +1-646-7271727 ; +91-9902-001100
    private String phone_number;
    private String gender;
    private LocalDate date_of_birth;
    @Size(min = 4, max = 8)
    private String active_ind;
    private String user_profile_code;
    @Size(min = 2, max = 32)
    private String country;
    @Size(min = 2, max = 32)
    private String state;
    @Size(min = 2, max = 32)
    private String city;
    private String group_primary_uuid;

    public static User getDummy(){
        User usr = new User();
        usr.first_name = "Dummy";
        usr.last_name = "User 001";
        usr.country = "India"; usr.state="ABC"; usr.city="XYZ" ;
        usr.email_id="dummy@abc.com" ; usr.phone_number="+91-9900-001100";
        usr.gender="Male" ; usr.date_of_birth =LocalDate.of(2000, 01, 01);
        return usr;
    }

}