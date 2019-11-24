package monos.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import monos.myapplication.listAdapter.PLUsAdapter;
import monos.myapplication.model.BillItem;
import monos.myapplication.model.OpenBill;
import monos.myapplication.model.PLU;
import monos.myapplication.R;
import monos.myapplication.viewModel.DataViewModelNetwork;
import monos.myapplication.viewModel.DataViewModelLocal;

public class PLUFragment extends Fragment implements PLUsAdapter.OnListItemClickListener {

    private DataViewModelNetwork dataViewModelNetwork;
    private DataViewModelLocal dataViewModelLocal;
    private PLUsAdapter pluAdapter;
    private OpenBill openBill;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_plu, container, false);
        RecyclerView pluList;
        ArrayList<PLU> plus;
        dataViewModelNetwork = ViewModelProviders.of(this).get(DataViewModelNetwork.class);
        dataViewModelLocal = ViewModelProviders.of(this).get(DataViewModelLocal.class);
        openBill = (OpenBill) getArguments().getSerializable("openBill");
        final int pluGroupId = getArguments().getInt("pluGroupId");
        plus = new ArrayList<>();
        for (PLU plu : dataViewModelNetwork.getAllPLUs().getValue()) {
            if (plu.getPluGroupId() == pluGroupId ) {
                plus.add(plu);
            }
        }
        pluAdapter = new PLUsAdapter(plus, this);
        dataViewModelNetwork.getAllPLUs().observe(this, new Observer<List<PLU>>() {
            @Override
            public void onChanged(List<PLU> notes) {

                if (dataViewModelNetwork.getAllPLUs().getValue() != null) {
                    ArrayList<PLU> plusOfGroup = new ArrayList<>();
                    for (PLU plu : dataViewModelNetwork.getAllPLUs().getValue()) {
                        if (plu.getPluGroupId() == pluGroupId ) {
                            plusOfGroup.add(plu);
                        }
                    }
                    pluAdapter.setPLUs(plusOfGroup);
                    pluAdapter.notifyDataSetChanged();
                }
            }


        });
        pluList = rootView.findViewById(R.id.rv_plu_list);
        pluList.setLayoutManager(new LinearLayoutManager(getContext()));
        pluList.setAdapter(pluAdapter);
        return rootView;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        PLU pluToAdd = pluAdapter.getCurrentPLUs().get(clickedItemIndex);
        BillItem billItemToAdd = new BillItem(pluToAdd.getName(), pluToAdd.getUnitPrice(), 1, pluToAdd.getUnitPrice());
        billItemToAdd.setOpenBillID(openBill.getId());
        dataViewModelLocal.insertBillItem(billItemToAdd);


    }
}
