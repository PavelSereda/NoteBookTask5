package by.tc.nb.bean;


public class FindNotesRequest extends Request {
    private String searchingContent;
    private String searchingDate;
    private int userID;

    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getSearchingContent() {
        return searchingContent;
    }
    public void setSearchingContent(String searchingContent) {
        this.searchingContent = searchingContent;
    }

    public String getSearchingDate() {
        return searchingDate;
    }
    public void setSearchingDate(String searchingDate) {
        this.searchingDate = searchingDate;
    }
}
