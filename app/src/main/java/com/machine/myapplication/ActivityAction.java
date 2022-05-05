package com.machine.myapplication;

import java.util.List;

public class ActivityAction {
    public static final int API_ERROR = 1;
    public static final int NO_INTERNET = 6;
    public static final int TIMEOUT = 2;
    public static final int GETPOSTSSUCCESS = 3;
    public static final int GETPOSTFAILED = 4;
    public static final int GETUSERSUCCESS = 5;
    public static final int GETUSERSFAILED = 7;

    String error;

    public List<PostData> getPostData() {
        return postData;
    }

    public void setPostData(List<PostData> postData) {
        this.postData = postData;
    }

    List<PostData> postData;

    public ActivityAction(int mAction, List<PostData> response, Integer post) {
        this.mAction = mAction;
        this.postData = response;
    }

    public ActivityAction(int mAction, List<UsersData> usersDataList) {
        this.mAction = mAction;
        this.usersDataLists = usersDataList;
    }

    List<UsersData> usersDataLists;

    public List<UsersData> getUsersDataLists() {
        return usersDataLists;
    }

    public void setUsersDataLists(List<UsersData> usersDataLists) {
        this.usersDataLists = usersDataLists;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ActivityAction(int mAction, String error) {
        this.mAction = mAction;
        this.error = error;
    }

    private final int mAction;

    public int getmAction() {
        return mAction;
    }
}
