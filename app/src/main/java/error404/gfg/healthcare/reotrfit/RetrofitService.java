package error404.gfg.healthcare.reotrfit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private Retrofit retrofit;

    public RetrofitService(){
        initilaizeRetorofit();
    }

    private void initilaizeRetorofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.29.109:5000") // host url paste here
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }
}
