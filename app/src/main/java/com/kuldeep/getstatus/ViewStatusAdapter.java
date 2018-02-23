package com.kuldeep.getstatus;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by ADMIN on 2/21/2018.
 */

public class ViewStatusAdapter extends RecyclerView.Adapter<ViewStatusAdapter.ViewHolder> {

    public List<Users> usersList;
    public Context context;

    public ViewStatusAdapter(Context context, List<Users> usersList){

        this.usersList = usersList;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        //String name = usersList.get(position).getUserName();
        //Log.d("onBind View Holdr",name);
        holder.mUserTV.setText(usersList.get(position).getUserName());
        holder.mStatusTV.setText(usersList.get(position).getUserStatus());
        final String userId = usersList.get(position).userId;

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"User id : "+ userId,Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        public TextView mUserTV;
        public TextView mStatusTV;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            mUserTV = mView.findViewById(R.id.user_name_tv);
            mStatusTV = mView.findViewById(R.id.user_status);
        }
    }


}
