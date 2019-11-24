package monos.myapplication.listAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import monos.myapplication.model.BillItem;
import monos.myapplication.R;

public class BillItemsAdapter extends RecyclerView.Adapter<BillItemsAdapter.ViewHolder>{


    private ArrayList<BillItem> billItems;
    final private BillItemsAdapter.OnListItemClickListener mOnListItemClickListener;

    public BillItemsAdapter(ArrayList<BillItem> newBillItems, BillItemsAdapter.OnListItemClickListener mOnListItemClickListener){
        billItems = newBillItems;

        this.mOnListItemClickListener = mOnListItemClickListener;
    }

    @NonNull
    @Override
    public BillItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.items_list_item, parent, false);
        return new BillItemsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillItemsAdapter.ViewHolder viewHolder, int position) {
        viewHolder.name.setText("Name: " + billItems.get(position).getName());
        viewHolder.unitPrice.setText("Unit price: "+String.valueOf(billItems.get(position).getUnitPrice()));
        viewHolder.amount.setText("Amount: "+String.valueOf(billItems.get(position).getAmount()));
        viewHolder.totalPrice.setText("Total price: "+String.valueOf(billItems.get(position).getTotalPrice()));
        final int  positionOfElement = position;

        viewHolder.increaseAmount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mOnListItemClickListener.increaseAmount(positionOfElement);
            }
        });
        viewHolder.decreaseAmount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mOnListItemClickListener.decreaseAmount(positionOfElement);
            }
        });

    }

    @Override
    public int getItemCount() {
        return billItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name;
        TextView unitPrice;
        TextView amount;
        TextView totalPrice;
        Button increaseAmount;
        Button decreaseAmount;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            unitPrice = itemView.findViewById(R.id.item_unit_price);
            amount = itemView.findViewById(R.id.item_amount);
            totalPrice = itemView.findViewById(R.id.item_total_price);
            increaseAmount = itemView.findViewById(R.id.button_increase);
            decreaseAmount = itemView.findViewById(R.id.button_decrease);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());

        }

    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
        void increaseAmount(int clickedItemIndex);
        void decreaseAmount(int clickedItemIndex);

    }

    public void setBillItems(ArrayList<BillItem> billItems) {
        this.billItems = billItems;
    }

    public ArrayList<BillItem> getBillItems(){
        return this.billItems;
    }
}
