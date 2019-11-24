package monos.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import monos.myapplication.R;
import monos.myapplication.fragment.PLUGroupsFragment;
import monos.myapplication.model.OpenBill;

public class AddItemsToBillActivity extends AppCompatActivity {
    OpenBill openBill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items_to_bill);
        Toolbar toolbar = findViewById(R.id.add_items_to_bill_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator( R.drawable.ic_back );
        openBill= (OpenBill)  getIntent().getSerializableExtra("openBill");

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Bundle bundle=new Bundle();
        bundle.putSerializable("openBill", openBill);
        PLUGroupsFragment pluGroupsFragment = new PLUGroupsFragment();
        pluGroupsFragment.setArguments(bundle);
        transaction.replace(R.id.add_items_to_bill_fragment_position,pluGroupsFragment);
        transaction.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, OpenBillActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
