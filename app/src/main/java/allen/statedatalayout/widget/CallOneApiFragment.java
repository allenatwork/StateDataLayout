package allen.statedatalayout.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import allen.statedatalayout.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Allen on 21-Sep-16.
 */
public abstract class CallOneApiFragment<K extends GetInfoData> extends Fragment implements ControllerTask<K> {
    protected StateDataLayout<K> layout;
    protected Call<K> call;

    protected abstract int getLayoutRes();

    protected abstract void defineRequest();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = new StateDataLayout<>(this, inflater);
        layout.setLayoutRes(getLayoutRes(), R.layout.nodata_layout, R.layout.loading_layout, R.layout.error_layout);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        defineRequest();
    }

    protected Callback<K> requestCallback = new Callback<K>() {
        @Override
        public void onResponse(Call<K> call, Response<K> response) {
            final K data = response.body();
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    layout.bindData(data);
                }
            });
        }

        @Override
        public void onFailure(Call<K> call, Throwable t) {
            final String mes = t.getMessage();
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    layout.bindError(mes);
                }
            });

        }
    };

    protected void callApi() {
        if (call == null) throw new IllegalStateException("Must intilize caller request");
        // Check if request has been executed or enqued. If yes, cancel.
        if (call.isExecuted()) {
            call.cancel();
        }
        call.enqueue(requestCallback);
    }

    @Override
    public void reload() {
        callApi();
    }
}
