package allen.statedatalayout;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import allen.statedatalayout.api.ApiInterface;
import allen.statedatalayout.api.RetrofitSingleton;
import allen.statedatalayout.api.StoryDetailResponse;
import allen.statedatalayout.widget.ControllerTask;
import allen.statedatalayout.widget.StateDataLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment implements ControllerTask {
    public static final String TAG = "STATE LAYOUT DATA";
    private ApiInterface apiInterface;
    StateDataLayout<StoryDetailResponse> layout;
    private Call<StoryDetailResponse> getStory;

    public TestFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiInterface = RetrofitSingleton.getApiService();
        Log.d(TAG, "On create interface success");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout = new StateDataLayout<>(getContext());
        layout.setLayoutRes(R.layout.fragment_test, R.layout.nodata_layout, R.layout.loading_layout, R.layout.error_layout);
        layout.setControllerTask(this);
        return layout;
    }

//    @Override
//    public void setMenuVisibility(boolean menuVisible) {
//        super.setMenuVisibility(menuVisible);
//        if (menuVisible) {
//            Log.d(TAG, "menu Visible");
//            layout.bindLoading();
//            Call<StoryDetailResponse> getStory = apiInterface.getStoryDetail("16uaq");
//            getStory.enqueue(new Callback<StoryDetailResponse>() {
//                @Override
//                public void onResponse(Call<StoryDetailResponse> call, Response<StoryDetailResponse> response) {
//                    StoryDetailResponse data = response.body();
//                    layout.bindData(data);
//                }
//
//                @Override
//                public void onFailure(Call<StoryDetailResponse> call, Throwable t) {
//                    layout.bindError(t.getMessage());
//                }
//            });
//        }
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "Activity Created");
        layout.bindLoading();
        getStory = apiInterface.getStoryDetail();
        callApi(getStory);
    }

    private void callApi(Call<StoryDetailResponse> getStory) {
        getStory.enqueue(new Callback<StoryDetailResponse>() {
            @Override
            public void onResponse(Call<StoryDetailResponse> call, Response<StoryDetailResponse> response) {
                final StoryDetailResponse data = response.body();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        layout.bindData(data);
                    }
                });

            }

            @Override
            public void onFailure(Call<StoryDetailResponse> call, Throwable t) {
                final Throwable ti = t;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        layout.bindError(ti.getMessage());
                    }
                });

            }
        });
    }

    @Override
    public void reload() {
        callApi(getStory);
    }

    @Override
    public void displayData(Object data) {

    }

}
