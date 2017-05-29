package gachon.mobile.programming.android.finalproject.models;

/**
 * Created by JJSOFT-DESKTOP on 2017-05-09.
 */

public class SingleData {
    private boolean isSuccess;
    private String data;

    public SingleData(boolean isSuccess, String data) {
        this.isSuccess = isSuccess;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
