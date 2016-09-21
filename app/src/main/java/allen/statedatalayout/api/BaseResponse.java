package allen.statedatalayout.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Allen on 09-Sep-16.
 */
public class BaseResponse {
    @SerializedName("success")
    protected boolean success;

    @SerializedName("message")
    protected String message;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
