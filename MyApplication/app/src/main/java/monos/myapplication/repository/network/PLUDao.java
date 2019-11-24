package monos.myapplication.repository.network;

import android.content.res.Resources;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import monos.myapplication.model.PLU;
import monos.myapplication.R;
import monos.myapplication.networkCommunication.MobileWaiterApi;
import monos.myapplication.networkCommunication.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PLUDao {

    private MutableLiveData<List<PLU>> allPLUs;
    private static PLUDao instance;

    private PLUDao() {
        allPLUs = new MutableLiveData<>();
        List<PLU> newList = new ArrayList<>();
        allPLUs.setValue(newList);
        requestPLUs();

    }

    public static PLUDao getInstance(){
        if(instance == null) {
            instance = new PLUDao();
        }
        return instance;
    }

    public LiveData<List<PLU>> getAllPLUs() {
        return allPLUs;
    }






    public void requestPLUs() {
        MobileWaiterApi mobileWaiterApi = ServiceGenerator.MobileWaiterApi();
        Call<PLU[]> call = mobileWaiterApi.getPLUs();
        call.enqueue(new Callback<PLU[]>() {
            @Override
            public void onResponse(Call<PLU[]> call, Response<PLU[]> response) {
                if (response.code() == 200) {
                    PLU[] plus =response.body();
                    allPLUs.setValue(new ArrayList<>(Arrays.asList(plus)));
                }
            }
            @Override
            public void onFailure(Call<PLU[]> call, Throwable t) {
                Log.d("Retrofit", Resources.getSystem().getString( R.string.retrofit_error));
                Log.d("Retrofit", t.getMessage());
            }
        });
    }
}
