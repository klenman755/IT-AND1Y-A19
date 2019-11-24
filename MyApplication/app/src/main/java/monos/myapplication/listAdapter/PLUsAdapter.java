package monos.myapplication.listAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


import monos.myapplication.model.PLU;
import monos.myapplication.R;

public class PLUsAdapter extends RecyclerView.Adapter<PLUsAdapter.ViewHolder>{


    private ArrayList<PLU> PLUs;
    final private PLUsAdapter.OnListItemClickListener mOnListItemClickListener;

    public PLUsAdapter(ArrayList<PLU> newPLUs, PLUsAdapter.OnListItemClickListener mOnListItemClickListener){
        PLUs = newPLUs;

        this.mOnListItemClickListener = mOnListItemClickListener;
    }

    @NonNull
    @Override
    public PLUsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.plu_list_item, parent, false);
        return new PLUsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PLUsAdapter.ViewHolder viewHolder, int position) {
        viewHolder.name.setText(PLUs.get(position).getName());
        viewHolder.unitPrice.setText(String.valueOf(PLUs.get(position).getUnitPrice()));


    }

    @Override
    public int getItemCount() {
        return PLUs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name;
        TextView unitPrice;


        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.plu_name);
            unitPrice = itemView.findViewById(R.id.plu_unit_price);

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

    public void setPLUs(ArrayList<PLU> PLUs) {
        this.PLUs = PLUs;
    }

    public ArrayList<PLU> getCurrentPLUs(){return this.PLUs;}
}
