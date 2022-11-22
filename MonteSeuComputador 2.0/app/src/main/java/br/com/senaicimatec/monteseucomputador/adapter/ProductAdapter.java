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
import br.com.senaicimatec.monteseucomputador.model.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>  {
    List<Product> productList;

    public ProductAdapter(List<Product> productList){
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.produto_item, parent, false);
        return new ProductViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.productPhoto.setImageResource(productList.get(position).getPhoto());
        holder.productName.setText(productList.get(position).getName());
        holder.productDescription.setText(productList.get(position).getDescription());
        holder.productPrice.setText(productList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView productPhoto = itemView.findViewById(R.id.productImage);
        TextView productName = itemView.findViewById(R.id.name);
        TextView productDescription = itemView.findViewById(R.id.productDescription);
        TextView productPrice = itemView.findViewById(R.id.productPrice);

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
