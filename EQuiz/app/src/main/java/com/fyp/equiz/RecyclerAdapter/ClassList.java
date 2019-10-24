package com.fyp.equiz.RecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fyp.equiz.CreateClass;
import com.fyp.equiz.Model.ClassData;
import com.fyp.equiz.R;

import java.util.ArrayList;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ClassList extends RecyclerView.Adapter<ClassViewHolder> {

    private ArrayList<ClassData> classes = new ArrayList<>();
    //ArrayList<String> up,down;
    private Context tContext;
    public ClassList(Context context) {
        tContext = context;

    }
    public ClassList() {
    }
    @Override
    public ClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View messageView = inflater.inflate(R.layout.recycler_view_class, parent, false);
        return new ClassViewHolder(messageView);
    }

    @Override
    public void onBindViewHolder(final ClassViewHolder holder, final int position) {
        final ClassData Class = classes.get(position);


        holder.bind(Class);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tContext, CreateClass.class);
                intent.putExtra("Detail",classes.get(position));//where is this?
                classes.clear();
                tContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    public void addMessage(ClassData Class) {
        classes.add(Class);
        notifyItemChanged(0);
    }

    public void removeMessage()
    {
        classes.clear();
    }
}
class ClassViewHolder extends RecyclerView.ViewHolder {

    private TextView className, classID;
    private ImageView photo;


    CardView parentLayout;

    public ClassViewHolder(View itemView) {
        super(itemView);

        className = (TextView) itemView.findViewById(R.id.class_name);
        classID = (TextView) itemView.findViewById(R.id.class_id);
        parentLayout = itemView.findViewById(R.id.parentLayout);
    }

    public void bind(ClassData message) {

        className.setText(message.getClassName().toString());
        classID.setText(message.getClassID().toString());
    }
}
