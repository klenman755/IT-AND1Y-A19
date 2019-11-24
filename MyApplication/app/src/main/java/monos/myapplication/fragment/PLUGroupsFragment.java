package monos.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import monos.myapplication.listAdapter.PLUGroupsAdapter;
import monos.myapplication.model.OpenBill;
import monos.myapplication.model.PluGroup;
import monos.myapplication.R;
import monos.myapplication.viewModel.DataViewModelNetwork;

public class PLUGroupsFragment extends Fragment implements PLUGroupsAdapter.OnListItemClickListener {

    private DataViewModelNetwork dataViewModelNetwork;
    private PLUGroupsAdapter pluGroupsAdapter;

    private OpenBill openBill;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_plu_group, container, false);

        openBill = (OpenBill) getArguments().getSerializable("openBill");
        RecyclerView pluGroups;
        dataViewModelNetwork = ViewModelProviders.of(this).get(DataViewModelNetwork.class);
        dataViewModelNetwork.getAllPLUGroups().observe(this, new Observer<List<PluGroup>>() {
            @Override
            public void onChanged(List<PluGroup> notes) {
                if(dataViewModelNetwork.getAllPLUGroups().getValue()!=null){
                    pluGroupsAdapter.setPluGroups(new ArrayList<>(dataViewModelNetwork.getAllPLUGroups().getValue()));
                    pluGroupsAdapter.notifyDataSetChanged();}
            }
        });
        pluGroupsAdapter = new PLUGroupsAdapter(new ArrayList<>(dataViewModelNetwork.getAllPLUGroups().getValue()), this);
        pluGroups = rootView.findViewById(R.id.rv_plu_groups);

        pluGroups.setLayoutManager(new LinearLayoutManager(getContext()));
        pluGroups.setAdapter(pluGroupsAdapter);
        return rootView;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Toast.makeText(getContext(), "plu group clicked", Toast.LENGTH_SHORT).show();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        PluGroup pluGroup = dataViewModelNetwork.getAllPLUGroups().getValue().get(clickedItemIndex);
        Bundle bundle=new Bundle();
        bundle.putSerializable("openBill", openBill);
        bundle.putInt("pluGroupId", pluGroup.getId());
        PLUFragment pluFragment = new PLUFragment();
        pluFragment.setArguments(bundle);
        transaction.replace(R.id.add_items_to_bill_fragment_position,pluFragment);
        transaction.commit();

    }
}
