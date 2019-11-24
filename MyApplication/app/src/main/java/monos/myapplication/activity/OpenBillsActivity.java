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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import monos.myapplication.R;
import monos.myapplication.fragment.LoginFragment;
import monos.myapplication.listAdapter.OpenBillsAdapter;
import monos.myapplication.model.OpenBill;
import monos.myapplication.viewModel.DataViewModelLocal;

public class OpenBillsActivity extends AppCompatActivity implements OpenBillsAdapter.OnListItemClickListener {

    private DataViewModelLocal dataViewModel;
    private OpenBillsAdapter openBillsListAdapter;
    private RecyclerView openBillsList;
    private final OpenBillsAdapter.OnListItemClickListener listner = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_bills);
        SharedPreferences prefs = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String currentUserId = prefs.getString("user_id", "default_name");
        if (currentUserId.equals("default_name")) {
            Log.d("TAG", "onCreate: default_name");
            LoginFragment loginFragment = new LoginFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.open_bills_overview, loginFragment);
            transaction.commit();
        } else {


            dataViewModel = ViewModelProviders.of(this).get(DataViewModelLocal.class);
            dataViewModel.getAllOpenBills().observe(this, new Observer<List<OpenBill>>() {

                @Override
                public void onChanged(List<OpenBill> notes) {
                    openBillsListAdapter.setOpenBills(new ArrayList<>(dataViewModel.getAllOpenBills().getValue()));
                    openBillsListAdapter.notifyDataSetChanged();

                }
            });
            ArrayList<OpenBill> openBills;
            try {
                openBills = new ArrayList<>(dataViewModel.getAllOpenBills().getValue());
            } catch (NullPointerException e) {
                openBills = new ArrayList<>();

            }
            openBillsListAdapter = new OpenBillsAdapter(new ArrayList<>(openBills), this);
            openBillsList = findViewById(R.id.rv_open_bills);
            openBillsList.hasFixedSize();
            openBillsList.setLayoutManager(new LinearLayoutManager(this));
            openBillsList.setAdapter(openBillsListAdapter);
            Toolbar toolbar = findViewById(R.id.my_toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_res, menu);
        return true;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        //after desired bill is clicked we want to open a view for a given bill
        //example of getting a selected item
        Toast.makeText(getApplicationContext(), "open bill was selected", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, OpenBillActivity.class);
        OpenBill openBill = dataViewModel.getAllOpenBills().getValue().get(clickedItemIndex);
        intent.putExtra("openBill", openBill);
        Log.d("XXXXXXXX", openBill.getNameOfBill());

        startActivity(intent);
        // OpenBill bill = dataViewModel.getAllOpenBills().getValue().get(clickedItemIndex);
    }

    public void createNewBill(View view) {
        //  Intent intent = new Intent(this, OpenBillActivity.class);
        OpenBill newOpenBill = new OpenBill(new Date().toString(), "name1", 99);
        dataViewModel.insertOpenBill(newOpenBill);
        //    intent.putExtra("openBill",newOpenBill);
        //   startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_statistics:
                Intent intent = new Intent(this, StatisticsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);

                return true;
            case R.id.action_logout:
                SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
                preferences.edit().remove("user_id").commit();
                recreate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
