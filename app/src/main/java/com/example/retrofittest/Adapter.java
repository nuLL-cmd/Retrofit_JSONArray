package com.example.retrofittest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofittest.provider.Provider;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.DataHandler> {
    private List<Provider> providerList;

    public Adapter(List<Provider> providerList) {
        this.providerList = providerList;
    }

    @NonNull
    @Override


    public DataHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_celula,parent, false);
        return new DataHandler(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataHandler holder, int position) {
        Provider provider = providerList.get(position);
        holder.txt_userId.setText(String.valueOf(provider.getUserId()));
        holder.txt_title.setText(provider.getTitle());
        holder.txt_body.setText(provider.getBody());
        holder.txt_id.setText(String.valueOf(provider.getId()));

    }

    @Override
    public int getItemCount() {
        return providerList.size();
    }

    public class DataHandler extends RecyclerView.ViewHolder {
        private TextView txt_id;
        private TextView txt_userId;
        private TextView txt_title;
        private TextView txt_body;
        public DataHandler(@NonNull View itemView) {
            super(itemView);

            txt_body = itemView.findViewById(R.id.txt_body);
            txt_id = itemView.findViewById(R.id.txt_id);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_userId = itemView.findViewById(R.id.txt_userId);
        }
    }
}
