package monos.myapplication.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import monos.myapplication.model.User;
import monos.myapplication.R;
import monos.myapplication.networkCommunication.MobileWaiterApi;
import monos.myapplication.networkCommunication.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class LoginFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        Button btn = rootView.findViewById(R.id.button_login);
        final EditText editText = rootView.findViewById(R.id.editTextQuickPass);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (editText.getText() != null && !editText.getText().toString().equals("")) {
                    login(editText.getText().toString());
                }
            }
        });
        return rootView;
    }

    public void login(final String quickPass) {
        MobileWaiterApi mobileWaiterApi = ServiceGenerator.MobileWaiterApi();

        Call<User> call = mobileWaiterApi.login(quickPass);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    User plus = response.body();
                    if (plus == null) {
                        Toast.makeText(getActivity().getApplicationContext(), R.string.login_wrong_password, Toast.LENGTH_SHORT).show();
                    } else {
                        SharedPreferences prefs = getActivity().getApplicationContext().getSharedPreferences("MyPreferences", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("user_id", plus.getName());
                        editor.apply();

                        // borrowed this code from https://gist.github.com/chrisjenx/3176258
                        Intent intent = getActivity().getIntent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        getActivity().overridePendingTransition(0, 0);
                        getActivity().finish();
                        getActivity().overridePendingTransition(0, 0);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Retrofit", getString(R.string.retrofit_error));
                Log.d("Retrofit", t.getMessage());
                Toast.makeText(getActivity().getApplicationContext(), R.string.service_unavailable, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
