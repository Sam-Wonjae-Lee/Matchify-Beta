package entity;

/**
 * CloseFriend entity stands out from Friend entity.
 * Close friends have the privilege to message between users whereas friends do not.
 */

public class CloseFriend extends Friend {

    public CloseFriend(int userID, String name) {
        super(userID, name);
    }

    public void setNickname() {

    }


}
