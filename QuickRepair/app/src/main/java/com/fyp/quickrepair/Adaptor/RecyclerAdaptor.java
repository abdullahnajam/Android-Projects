package com.fyp.quickrepair.Adaptor;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fyp.quickrepair.DataModel;
import com.fyp.quickrepair.R;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class RecyclerAdaptor extends RecyclerView.Adapter<MyMessageViewHolder> {

    private ArrayList<DataModel> messages = new ArrayList<>();
    //ArrayList<String> up,down;
    private Context mContext;
    public RecyclerAdaptor(Context context) {
        mContext = context;

    }
    public RecyclerAdaptor() {
    }
    @Override
    public MyMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View messageView = inflater.inflate(R.layout.card_services, parent, false);
        return new MyMessageViewHolder(messageView);
    }

    @Override
    public void onBindViewHolder(final MyMessageViewHolder holder, final int position) {
        final DataModel message = messages.get(position);



        holder.bind(message);

        /*holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Activities.class);
                mContext.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void addMessage(DataModel message) {
        messages.add(message);
        notifyItemChanged(0);
    }
    public void removeMessage()
    {
        messages.clear();
    }
}
class MyMessageViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private TextView description;
    private ImageView photo;
    ImageView iconNearby;
    TextView timestampDisplay,getkeyid,upvotebtn,downvotebtn;


    CardView parentLayout;

    public MyMessageViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.serviceName);
        description = (TextView) itemView.findViewById(R.id.serviceDescription);
        photo = (ImageView)itemView.findViewById(R.id.servicePhoto);
        parentLayout = itemView.findViewById(R.id.parentLayout);
    }

    /*public void onClick(final int position)
    {
        upvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecyclerAdaptor.this, NearByRequest.class));
            }
        });
    }*/
    public void bind(DataModel message) {

        title.setText(message.getTitle().toString());
        description.setText(message.getDescription().toString());
        photo.setImageResource(message.getPhotoId());
        //

        //getkeyid.setText(rid);
    }
}


