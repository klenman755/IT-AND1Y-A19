package monos.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import monos.myapplication.R;
import monos.myapplication.activity.OpenBillsActivity;
import monos.myapplication.networkCommunication.MobileWaiterApi;
import monos.myapplication.networkCommunication.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticsActivity extends AppCompatActivity {

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Toolbar toolbar = findViewById(R.id.statistics_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        SharedPreferences prefs = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String currentUserId = prefs.getString("user_id", "default_name");
        textView = findViewById(R.id.statistics_text_view);
        textView.setText(R.string.sale_statistics);
        requestSales(currentUserId);
        requestSales("-1");
    }


    public void requestSales(final String employeeId) {
        MobileWaiterApi mobileWaiterApi = ServiceGenerator.MobileWaiterApi();

        Call<BigDecimal> call = mobileWaiterApi.getSales(employeeId);
        call.enqueue(new Callback<BigDecimal>() {
            @Override
            public void onResponse(Call<BigDecimal> call, Response<BigDecimal> response) {
                if (response.code() == 200) {
                    BigDecimal plus = response.body();
                    if (!employeeId.equals("-1")) {
                        Log.d("Retrofit", "200 particular employee: " + plus);
                        textView.setText(textView.getText() + "\n" + employeeId + ": " + plus);
                    } else {
                        Log.d("Retrofit", "200 all: " + plus);
                        textView.setText(textView.getText() + "\nTogether: " + plus);
                    }

                }
            }

            @Override
            public void onFailure(Call<BigDecimal> call, Throwable t) {
                Log.d("Retrofit", "Something went wrong :(");
                Log.d("Retrofit", t.getMessage());
                Toast.makeText(getApplicationContext(), R.string.service_unavailable, Toast.LENGTH_SHORT).show();


            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, OpenBillsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }


}

