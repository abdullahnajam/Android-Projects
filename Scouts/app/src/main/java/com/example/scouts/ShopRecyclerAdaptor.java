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

public class ShopRecyclerAdaptor extends RecyclerView.Adapter<MessageViewHolder> {

    private ArrayList<DataModel> messages = new ArrayList<>();
    //ArrayList<String> up,down;
    private Context mContext;
    public ShopRecyclerAdaptor(Context context) {
        mContext = context;

    }
    public ShopRecyclerAdaptor() {
    }
    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View messageView = inflater.inflate(R.layout.shop_layout, parent, false);
        return new MessageViewHolder(messageView);
    }

    @Override
    public void onBindViewHolder(final MessageViewHolder holder, final int position) {
        final DataModel message = messages.get(position);



        holder.bind(message);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ShopActivity.class);
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
class MessageViewHolder extends RecyclerView.ViewHolder {

    private TextView item;
    private TextView price;
    ImageView iconNearby;
    TextView timestampDisplay,getkeyid,upvotebtn,downvotebtn;


    LinearLayout parentLayout;

    public MessageViewHolder(View itemView) {
        super(itemView);

        item = (TextView) itemView.findViewById(R.id.item);
        price = (TextView) itemView.findViewById(R.id.price);

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


