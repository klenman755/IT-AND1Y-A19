package monos.myapplication.networkCommunication;

import java.math.BigDecimal;

import monos.myapplication.model.ClosedBill;
import monos.myapplication.model.PLU;
import monos.myapplication.model.PluGroup;
import monos.myapplication.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface MobileWaiterApi {

    @GET("getPluGroups")
    Call<PluGroup[]> getPluGroups();

    @GET("getPLUs")
    Call<PLU[]> getPLUs();

    @POST("sendBill")
    Call<Integer> sendBill(@Body ClosedBill closedBill);

    @GET("getSales")
    Call<BigDecimal> getSales(@Query("id") String userId);

    @GET("login")
    Call<User> login(@Query("quickPass") String quickPass);



}
