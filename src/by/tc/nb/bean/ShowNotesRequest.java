package by.tc.nb.bean;

/**
 * Created by Pavel_Sereda on 10/12/2016.
 */
public class ShowNotesRequest extends Request {
    private int userID;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
