package allen.statedatalayout.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    private static ApiInterface apiService = null;

    private static OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

    private static final String TAG = RetrofitSingleton.class.getSimpleName();

    public static void init() {
        Executor executor = Executors.newCachedThreadPool();
        Gson gson = new GsonBuilder().create();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(ApiConstant.HOST)
                .client(okHttpClientBuilder.build())
                .callbackExecutor(executor)
                .build();
        apiService = retrofit.create(ApiInterface.class);
    }

    public static ApiInterface getApiService() {
        if (apiService != null) return apiService;

        init();
        return getApiService();
    }
}
