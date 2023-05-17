package error404.gfg.healthcare.reotrfit;

import error404.gfg.healthcare.model.UserModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface userAPI {
    @POST("/signup")
    Call<UserModel> AddUser(@Body UserModel userModel);

    @POST("/login")
    Call<UserModel> login(@Body UserModel userModel);

    @GET("user/profile")
    Call<UserModel> getUserProfile(@Header("Authorization") String token);
}
