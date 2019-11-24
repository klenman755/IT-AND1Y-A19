package monos.myapplication.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import monos.myapplication.model.OpenBill;
import monos.myapplication.R;
import monos.myapplication.viewModel.DataViewModelLocal;


public class BillInfoFragment extends Fragment {

    private DataViewModelLocal dataViewModelLocal;
    private OpenBill openBill;
    private EditText tableNumber;
    private EditText billName;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bill_info_fragment, container, false);
        dataViewModelLocal = ViewModelProviders.of(this).get(DataViewModelLocal.class);
        openBill = (OpenBill) getArguments().getSerializable("openBill");
        tableNumber = rootView.findViewById(R.id.editText2);
        tableNumber.setText( String.valueOf(openBill.getTableNumber()));
        billName=  rootView.findViewById(R.id.editText);
        billName.setText(openBill.getNameOfBill());
        Button btn = rootView.findViewById(R.id.bill_info_button_approve);
        final BillInfoFragment bif = this;
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(tableNumber.getText()!=null && !tableNumber.getText().toString().equals("")
                        &&billName.getText()!=null && !billName.getText().toString().equals("")){
                   openBill.setNameOfBill(billName.getText().toString());
                   openBill.setTableNumber(Integer.valueOf(tableNumber.getText().toString()));
                    dataViewModelLocal.updateOpenBill(openBill);
                    getActivity().getSupportFragmentManager().beginTransaction().remove(bif).commit();

                }


            }
        });

        return rootView;

    }

    }






