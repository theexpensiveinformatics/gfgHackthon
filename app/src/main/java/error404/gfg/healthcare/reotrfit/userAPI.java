package error404.gfg.healthcare.reotrfit;

import error404.gfg.healthcare.model.UserModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface userAPI {
    @POST("/signup")
    Call<UserModel> AddUser(@Body UserModel userModel);

    @POST("/login")
    Call<UserModel> login(@Body UserModel userModel);

    @GET("user/profile")
    Call<UserModel> getUserProfile(@Header("Authorization") String token);

    @PUT("users/{user_email}")
    Call<Void> editUser(@Path("user_email") String userEmail, @Body UserModel userModel);

    @POST("/forgot_password")
    Call<UserModel> forgot_password(@Body UserModel userModel);

    @POST("/verify_otp")
    Call<UserModel> verify_otp(@Body UserModel userModel);

    @POST("/update_password")
    Call<UserModel> update_password(@Body UserModel userModel);
}
