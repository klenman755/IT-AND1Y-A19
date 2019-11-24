package monos.myapplication.listAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import monos.myapplication.model.PluGroup;
import monos.myapplication.R;

public class PLUGroupsAdapter  extends RecyclerView.Adapter<PLUGroupsAdapter.ViewHolder>{


    private ArrayList<PluGroup> PLUs;
    final private PLUGroupsAdapter.OnListItemClickListener mOnListItemClickListener;

    public PLUGroupsAdapter(ArrayList<PluGroup> newPLUs, PLUGroupsAdapter.OnListItemClickListener mOnListItemClickListener){
        PLUs = newPLUs;

        this.mOnListItemClickListener = mOnListItemClickListener;
    }

    @NonNull
    @Override
    public PLUGroupsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.plu_group_list_item, parent, false);
        return new PLUGroupsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PLUGroupsAdapter.ViewHolder viewHolder, int position) {
        viewHolder.name.setText(PLUs.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return PLUs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.plu_group_name);
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

    public void setPluGroups(ArrayList<PluGroup> PLUs) {
        this.PLUs = PLUs;
    }


}
