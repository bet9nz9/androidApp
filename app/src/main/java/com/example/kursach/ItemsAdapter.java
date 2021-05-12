package com.example.kursach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursach.model.VacancyDataModel;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<VacancyDataModel> vacancies;

    ItemsAdapter(Context context, List<VacancyDataModel> vacancies) {
        this.vacancies = vacancies;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemsAdapter.ViewHolder holder, int position) {
        VacancyDataModel vacancyDataModel = vacancies.get(position);
        holder.shortDesc.setText(vacancyDataModel.getShortDesc());
        holder.price.setText(vacancyDataModel.getCoast().toString());
    }

    @Override
    public int getItemCount() {
        return vacancies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView shortDesc;
        private TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shortDesc = itemView.findViewById(R.id.shortDesc);
            price = itemView.findViewById(R.id.price);
        }
    }
}
