package monos.myapplication.listAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import monos.myapplication.model.OpenBill;
import monos.myapplication.R;

public class OpenBillsAdapter extends RecyclerView.Adapter<OpenBillsAdapter.ViewHolder> {


    private ArrayList<OpenBill> openBills;
    final private OnListItemClickListener mOnListItemClickListener;

    public  OpenBillsAdapter(ArrayList<OpenBill> newOpenBills, OnListItemClickListener mOnListItemClickListener){
        openBills = newOpenBills;

        this.mOnListItemClickListener = mOnListItemClickListener;
    }

    public void setOpenBills(ArrayList<OpenBill> openBills) {
        this.openBills = openBills;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.open_bill_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
     /*
        viewHolder.openedAt.setText("a");
        viewHolder.nameOfBill.setText("b");
        viewHolder.table.setText("c");*/


        viewHolder.openedAt.setText("Opened at: " + openBills.get(position).getOpenedAt());
        viewHolder.nameOfBill.setText("Name of bill: " + openBills.get(position).getNameOfBill());
         viewHolder.table.setText("Table number: "+String.valueOf(openBills.get(position).getTableNumber()));

    }

    @Override
    public int getItemCount() {
        return openBills.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView openedAt;
        TextView nameOfBill;
        TextView table;

        ViewHolder(View itemView) {
            super(itemView);
            openedAt = itemView.findViewById(R.id.open_bill_opened_at);
            nameOfBill = itemView.findViewById(R.id.open_bill_name_of_bill);
            table = itemView.findViewById(R.id.open_bill_table);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());

        }
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }


}
