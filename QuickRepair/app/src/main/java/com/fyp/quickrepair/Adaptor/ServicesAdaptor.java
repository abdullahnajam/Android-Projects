package com.fyp.quickrepair.Adaptor;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fyp.quickrepair.R;
import com.fyp.quickrepair.Model.ServiceDataModel;
import com.fyp.quickrepair.ShopDetail;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class ServicesAdaptor extends RecyclerView.Adapter<ServiceMessageViewHolder> {

    private ArrayList<ServiceDataModel> messages = new ArrayList<>();
    public ArrayList<String> pid = new ArrayList<>();
    public ArrayList<Float> distance = new ArrayList<>();

    //ArrayList<String> up,down;
    private Context mContext;
    public ServicesAdaptor(Context context,ArrayList<String>id,ArrayList<Float> away) {
        mContext = context;
        pid=id;
        distance=away;

    }
    public ServicesAdaptor() {
    }
    @Override
    public ServiceMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View messageView = inflater.inflate(R.layout.serviceslist__cardview, parent, false);
        return new ServiceMessageViewHolder(messageView);
    }

    @Override
    public void onBindViewHolder(final ServiceMessageViewHolder holder, final int position) {
        final ServiceDataModel message = messages.get(position);
        holder.getkeyid.setText(pid.get(position));
        holder.away.setText(distance.get(position)+" km");



        holder.bind(message);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ShopDetail.class);
                intent.putExtra("id",pid.get(position));
                intent.putExtra("Detail",messages.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void addMessage(ServiceDataModel message) {
        messages.add(message);
        notifyItemChanged(0);
    }
    public void removeMessage()
    {
        messages.clear();
    }
}
class ServiceMessageViewHolder extends RecyclerView.ViewHolder {

    private TextView address;
    TextView getkeyid,away;
    private TextView description;
    private ImageView photo;


    CardView parentLayout;

    public ServiceMessageViewHolder(View itemView) {
        super(itemView);

        address = (TextView) itemView.findViewById(R.id.shopAddress);
        getkeyid = (TextView) itemView.findViewById(R.id.getkeyid);
        description = (TextView) itemView.findViewById(R.id.shopDescription);
        away = (TextView) itemView.findViewById(R.id.shopAway);
        parentLayout = itemView.findViewById(R.id.parentLayout);
    }

   /* public void onClick(final int position)
    {
        upvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecyclerAdaptor.this, NearByRequest.class));
            }
        });
    }*/
    public void bind(ServiceDataModel message) {

        address.setText(message.getAddress().toString());
        description.setText(message.getDescription().toString());
//        away.setText(message.getAway().toString());
        //

        //getkeyid.setText(pid);
    }
}


