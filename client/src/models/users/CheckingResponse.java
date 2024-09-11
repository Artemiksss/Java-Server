package models.users;

public class CheckingResponse {

    static public String checkingResponse(String response) {

        if (response.startsWith("OK")) {
            System.out.println("------------- Access is allowed -------------");
            User.setAccess(Access.ALLOWED);
            return "Your ID: " + response.replaceAll("\\D+", "");
        }
        if (response.startsWith("Admin")) {
            User.setAccess(Access.ADMIN);
            return "----------- Admin access is allowed-----------";
        } else {
            return response;
        }
    }
}
