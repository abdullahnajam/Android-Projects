package com.example.scouts;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

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
        View messageView = inflater.inflate(R.layout.event_layout, parent, false);
        return new MyMessageViewHolder(messageView);
    }

    @Override
    public void onBindViewHolder(final MyMessageViewHolder holder, final int position) {
        final DataModel message = messages.get(position);



        holder.bind(message);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Activities.class);
                mContext.startActivity(intent);
            }
        });
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

    private TextView item;
    private TextView price;
    ImageView iconNearby;
    TextView timestampDisplay,getkeyid,upvotebtn,downvotebtn;


    LinearLayout parentLayout;

    public MyMessageViewHolder(View itemView) {
        super(itemView);

        item = (TextView) itemView.findViewById(R.id.description);
        price = (TextView) itemView.findViewById(R.id.postedby);

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

        item.setText(message.getTitle().toString());
        price.setText(message.getPostedby().toString());
        //

        //getkeyid.setText(rid);
    }
}


