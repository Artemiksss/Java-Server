package models.users;

public class CheckingResponse {

    static public void checkingResponse(String response){
        if (response.equals("Access is allowed.")){
            User.setAccess(Access.ALLOWED);
        }
    }
}
