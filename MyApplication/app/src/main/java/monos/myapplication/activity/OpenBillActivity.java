package monos.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import monos.myapplication.R;
import monos.myapplication.fragment.BillInfoFragment;
import monos.myapplication.listAdapter.BillItemsAdapter;
import monos.myapplication.model.BillItem;
import monos.myapplication.model.ClosedBill;
import monos.myapplication.model.OpenBill;
import monos.myapplication.networkCommunication.MobileWaiterApi;
import monos.myapplication.networkCommunication.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import monos.myapplication.viewModel.DataViewModelLocal;

public class OpenBillActivity extends AppCompatActivity implements BillItemsAdapter.OnListItemClickListener {

    private DataViewModelLocal dataViewModel;
    private BillItemsAdapter billItemsAdapter;
    private RecyclerView billItemsList;
    private OpenBill currentBill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_bill);
        Toolbar toolbar = findViewById(R.id.open_bill_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator( R.drawable.ic_back );
        dataViewModel = ViewModelProviders.of(this).get(DataViewModelLocal.class);
        currentBill = (OpenBill) getIntent().getSerializableExtra("openBill");
        dataViewModel.getCurrentBillItems(currentBill);
        dataViewModel.getAllBillItems().observe(this, new Observer<List<BillItem>>() {
            @Override
            public void onChanged(List<BillItem> notes) {
                if (dataViewModel.getAllBillItems().getValue() != null) {
                    billItemsAdapter.setBillItems(new ArrayList<>(dataViewModel.getAllBillItems().getValue()));
                    billItemsAdapter.notifyDataSetChanged();
                }


            }
        });
        ArrayList<BillItem> openBills;
        try {
            openBills = new ArrayList<>(dataViewModel.getAllBillItems().getValue());
        } catch (NullPointerException e) {
            openBills = new ArrayList<>();
        }
        billItemsAdapter = new BillItemsAdapter(openBills, this);
        billItemsList = findViewById(R.id.rv_open_bill);
        billItemsList.setLayoutManager(new LinearLayoutManager(this));
        billItemsList.setAdapter(billItemsAdapter);
    }


    @Override
    public void onListItemClick(int clickedItemIndex) {

        //maybe after swipe left the item is deleted
    }

    @Override
    public void increaseAmount(int clickedItemIndex) {
        dataViewModel.adjustItemAmount( clickedItemIndex, true);
    }

    @Override
    public void decreaseAmount(int clickedItemIndex) {
        dataViewModel.adjustItemAmount( clickedItemIndex, false);
    }


    public void addItem(View view) {
        Intent i = new Intent(this, AddItemsToBillActivity.class);
        i.putExtra("openBill", currentBill);
        startActivity(i);
    }

    public void payBill(View view) {
        BigDecimal sum = BigDecimal.ZERO;
        for (BillItem bi : billItemsAdapter.getBillItems()) {
            sum = sum.add(BigDecimal.valueOf(bi.getTotalPrice()));
        }
        SharedPreferences prefs = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String currentUserId = prefs.getString("user_id", "default_name");
        ClosedBill closedBill = new ClosedBill(currentUserId, sum, currentBill.getId());

        MobileWaiterApi mobileWaiterApi = ServiceGenerator.MobileWaiterApi();
        Call<Integer> call = mobileWaiterApi.sendBill(closedBill);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.code() == 200) {
                    Integer plus = response.body();
                    Log.d("Retrofit", "paying bill result: " + plus);
                    for (BillItem bi : billItemsAdapter.getBillItems()) {
                        dataViewModel.deleteBillItem(bi);
                    }
                    dataViewModel.deleteOpenBill(currentBill);
                    Intent intent = new Intent( getApplicationContext(), OpenBillsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
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

        }
        return super.onOptionsItemSelected(item);
    }

    public void updateBill(View view) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("openBill", currentBill);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        BillInfoFragment pluFragment = new BillInfoFragment();
        pluFragment.setArguments(bundle);
        Log.d("TAG", "updateBill: ");
        transaction.replace(R.id.open_bill_relative_layout,  pluFragment);
        transaction.commit();
    }
}
