package allen.statedatalayout.api;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Admin on 9/9/2016.
 */
public interface ApiInterface {

    @GET("bins/16uaq")
    Call<StoryDetailResponse> getStoryDetail();

}
