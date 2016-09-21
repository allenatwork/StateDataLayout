package allen.statedatalayout;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.TextView;

import allen.statedatalayout.api.ApiInterface;
import allen.statedatalayout.api.RetrofitSingleton;
import allen.statedatalayout.api.StoryDetailResponse;
import allen.statedatalayout.widget.CallOneApiFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends CallOneApiFragment<StoryDetailResponse> {
    public static final String TAG = "STATE LAYOUT DATA";
    private ApiInterface apiInterface;

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
    protected int getLayoutRes() {
        return R.layout.fragment_test;
    }

    @Override
    protected void defineRequest() {
        call = apiInterface.getStoryDetail();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "Activity Created");
        layout.bindLoading();
        callApi();
    }

    @Override
    public void displayData(StoryDetailResponse data) {
        ((TextView) layout.getDataView().findViewById(R.id.text)).setText(data.toString());
    }
}
