package monos.myapplication.networkCommunication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * the server is run locally at http://localhost:8080/
 * in order to achieve connection without extra issues the android device is connected
 * to hotspot provided by computer
 */
public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("http://84.238.41.46:8080/")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static MobileWaiterApi mobileWaiterApi = retrofit.create(MobileWaiterApi.class);

    public static MobileWaiterApi MobileWaiterApi() {
        return mobileWaiterApi;
    }
}
