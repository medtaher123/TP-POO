package authentication;

import models.User;

//TODO: add to doc: explain access levels, every page has an access level,
public class AccessLevel {
    public static final int GUEST = 1;//no account needed
    public static final int ADMIN = 1 << 1;
    public static final int CUSTOMER = 1 << 2;
    public static final int ALL_EXCEPT_GUEST = ADMIN | CUSTOMER;
    public static final int ALL = GUEST | ADMIN | CUSTOMER;

    private static boolean isAuthorized(int accessLevel, int permissionToCheck) {
        return (accessLevel & permissionToCheck) != 0;
    }

    public static boolean isAuthorized(int permission) {
        return isAuthorized(AuthenticationSystem.getActiveUser(), permission);
    }

    public static boolean isAuthorized(User user, int permission){
        return isAuthorized(getUserAccessLevel(user),permission);
    }
    
    public static int getUserAccessLevel() {
        return getUserAccessLevel(AuthenticationSystem.getActiveUser());
    }

    public static int getUserAccessLevel(User user){
        if(user == null) return GUEST;
        
        switch (user.getType()){
            case CUSTOMER:
                return CUSTOMER;
            case ADMIN:
                return ADMIN;
            default:
                return GUEST;
        }
    }
    

}
