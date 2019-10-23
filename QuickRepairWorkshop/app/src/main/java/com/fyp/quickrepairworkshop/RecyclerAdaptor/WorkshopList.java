package com.fyp.quickrepairworkshop.RecyclerAdaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fyp.quickrepairworkshop.DataModel.WorkshopDataModel;
import com.fyp.quickrepairworkshop.R;
import com.fyp.quickrepairworkshop.Services;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class WorkshopList extends RecyclerView.Adapter<WorkshopMessageViewHolder> {

    private ArrayList<WorkshopDataModel> workshops = new ArrayList<>();
    //ArrayList<String> up,down;
    private Context mContext;
    public WorkshopList(Context context) {
        mContext = context;

    }
    public WorkshopList() {
    }
    @Override
    public WorkshopMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View messageView = inflater.inflate(R.layout.recycler_item_shop, parent, false);
        return new WorkshopMessageViewHolder(messageView);
    }

    @Override
    public void onBindViewHolder(final WorkshopMessageViewHolder holder, final int position) {
        final WorkshopDataModel shop = workshops.get(position);


        holder.bind(shop);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Services.class);
                intent.putExtra("Detail",workshops.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return workshops.size();
    }

    public void addMessage(WorkshopDataModel shop) {
        workshops.add(shop);
        notifyItemChanged(0);
    }
    public void removeMessage()
    {
        workshops.clear();
    }
}
class WorkshopMessageViewHolder extends RecyclerView.ViewHolder {

    private TextView address;
    private TextView description,away;
    private ImageView photo;


    CardView parentLayout;

    public WorkshopMessageViewHolder(View itemView) {
        super(itemView);

        address = (TextView) itemView.findViewById(R.id.shopAddress);
        description = (TextView) itemView.findViewById(R.id.shopDescription);
        parentLayout = itemView.findViewById(R.id.parentLayout);
    }

    public void bind(WorkshopDataModel message) {

        address.setText(message.getAddress().toString());
        description.setText(message.getDescription().toString());
    }
}



