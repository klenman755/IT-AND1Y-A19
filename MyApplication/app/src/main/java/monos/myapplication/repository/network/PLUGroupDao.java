package monos.myapplication.repository.network;

import android.content.res.Resources;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import monos.myapplication.model.PluGroup;
import monos.myapplication.R;
import monos.myapplication.networkCommunication.MobileWaiterApi;
import monos.myapplication.networkCommunication.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PLUGroupDao {

    private MutableLiveData<List<PluGroup>> allPLUGroups;
    private static PLUGroupDao instance;

    private PLUGroupDao() {
        allPLUGroups = new MutableLiveData<>();


       List<PluGroup> newList = new ArrayList<>();
       allPLUGroups.setValue(newList);
       requestPLUGroups();
    }

    public static PLUGroupDao getInstance(){
        if(instance == null) {
            instance = new PLUGroupDao();
        }
        return instance;
    }

    public LiveData<List<PluGroup>> getAllPLUGroups() {
        return allPLUGroups;
    }




    public void requestPLUGroups() {
        MobileWaiterApi mobileWaiterApi = ServiceGenerator.MobileWaiterApi();
         Call<PluGroup[]> call = mobileWaiterApi.getPluGroups();
        call.enqueue(new Callback<PluGroup[]>() {
            @Override
            public void onResponse(Call<PluGroup[]> call, Response<PluGroup[]> response) {
                if (response.code() == 200) {
                    PluGroup[] plus =response.body();
                    allPLUGroups.setValue(new ArrayList<>(Arrays.asList(plus)));
                }
            }
            @Override
            public void onFailure(Call<PluGroup[]> call, Throwable t) {
                Log.d("Retrofit", Resources.getSystem().getString( R.string.retrofit_error));
                Log.d("Retrofit", t.getMessage());
            }
        });
    }
}
