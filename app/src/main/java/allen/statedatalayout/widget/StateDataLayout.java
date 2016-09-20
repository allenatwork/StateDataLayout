package allen.statedatalayout.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import allen.statedatalayout.R;

/**
 * Created by Allen on 20-Sep-16.
 */
public class StateDataLayout extends FrameLayout {
    private LayoutInflater layoutInflater;
    //    private int loading_layout_res;
//    private int error_layout_res;
//    private int data_layout_res;
//    private int nodata_layout_res;
    private View mLoadingView, mErrorView, mNoDataView, mDataView;

    IControllerTask iControllerTask;

    private OnClickListener onRetryButtonClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (iControllerTask != null) {
                iControllerTask.reload();
            } else {
                throw new IllegalStateException("Activity/Fragment must be implement interface IControllerTask");
            }
        }
    };

    public void setControllerTask(IControllerTask iControllerTask) {
        this.iControllerTask = iControllerTask;
    }

    public StateDataLayout(Context context) {
        super(context);
    }

    public StateDataLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StateDataLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public void setLayoutRes(int data_layout_res, int nodata_layout_res, int loading_layout_res, int error_layout_res) {
        setLoadingLayoutRes(loading_layout_res);
        setErrorLayoutRes(error_layout_res);
        setNoDataRes(nodata_layout_res);
        setDataRes(data_layout_res);
    }

    public void setLoadingLayoutRes(int loading_layout_res) {
        mLoadingView = layoutInflater.inflate(loading_layout_res, null, false);
        initViewtoLayout(mLoadingView);
    }

    public void setErrorLayoutRes(int error_layout_res) {
        mErrorView = layoutInflater.inflate(error_layout_res, null, false);
        initViewtoLayout(mErrorView);
        setupRetryButton(mErrorView);
    }

    public void setNoDataRes(int nodata_layout_res) {
        mNoDataView = layoutInflater.inflate(nodata_layout_res, null, false);
        initViewtoLayout(mNoDataView);
        setupRetryButton(mNoDataView);
    }

    public void setDataRes(int data_layout_res) {
        mDataView = layoutInflater.inflate(data_layout_res, null, false);
        initViewtoLayout(mDataView);
    }

    private void initViewtoLayout(View view) {
        view.setVisibility(GONE);
        if (view.getParent() == null)
            addView(view);
    }

    public View getLoadingView() {
        return mLoadingView;
    }

    public View getErrorView() {
        return mErrorView;
    }

    public View getNoDataView() {
        return mNoDataView;
    }

    public View getDataView() {
        return mDataView;
    }

    private void switchToLoadingView() {
        goneView(mDataView, mErrorView, mNoDataView);
        showView(mLoadingView);
    }

    private void switchToErrorView() {
        goneView(mDataView, mNoDataView, mLoadingView);
        showView(mErrorView);
    }

    private void switchToNoDataView() {
        goneView(mDataView, mErrorView, mLoadingView);
        showView(mNoDataView);
    }

    private void switchToDataView() {
        goneView(mNoDataView, mErrorView, mLoadingView);
        showView(mDataView);
    }

    public void goneView(View... views) {
        for (View view : views) {
            if (view.getVisibility() == VISIBLE) {
                view.setVisibility(view.GONE);
            }
        }
    }

    public void showView(View... views) {
        for (View view : views) {
            if (view.getVisibility() != VISIBLE) {
                view.setVisibility(View.VISIBLE);
            }
        }
    }


    private void setupRetryButton(View view) {
        if (view == null) return;
        View retryView = view.findViewById(R.id.bt_retry);
        if (retryView == null) return;
        retryView.setOnClickListener(onRetryButtonClickListener);
    }
}