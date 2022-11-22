package br.com.senaicimatec.monteseucomputador.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.senaicimatec.monteseucomputador.R;
import br.com.senaicimatec.monteseucomputador.model.Software;

public class SoftwareAdapter extends RecyclerView.Adapter<SoftwareAdapter.SoftwareViewHolder>  {
    List<Software> softwareList;

    public SoftwareAdapter(List<Software> softwareList){
        this.softwareList = softwareList;
    }

    @NonNull
    @Override
    public SoftwareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.software_item, parent, false);
        return new SoftwareViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull SoftwareViewHolder holder, int position) {
        holder.softwarePhoto.setImageResource(softwareList.get(position).getPhoto());
        holder.softwareName.setText(softwareList.get(position).getName());
        holder.softwareDescription.setText(softwareList.get(position).getDescription());
        holder.softwarePrice.setText(softwareList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return softwareList.size();
    }

    static class SoftwareViewHolder extends RecyclerView.ViewHolder{

        ImageView softwarePhoto = itemView.findViewById(R.id.softwareImage);
        TextView softwareName = itemView.findViewById(R.id.name);
        TextView softwareDescription = itemView.findViewById(R.id.softwareDescription);
        TextView softwarePrice = itemView.findViewById(R.id.softwarePrice);

        public SoftwareViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
