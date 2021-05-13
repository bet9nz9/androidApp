package com.example.kursach;

import android.content.Context;
import android.content.Intent;
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
    public void onBindViewHolder(final ItemsAdapter.ViewHolder holder, int position) {
        final VacancyDataModel vacancyDataModel = vacancies.get(position);
        holder.shortDesc.setText(vacancyDataModel.getShortDesc());
        holder.price.setText(vacancyDataModel.getCoast().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: сделать перевод на следующий активити с отдельной вакансией
                Intent intent = new Intent(holder.itemView.getContext(), VacancyDescriptionActivity.class);
                intent.putExtra(VacancyDataModel.class.getSimpleName(), vacancyDataModel);
                holder.itemView.getContext().startActivity(intent);
            }
        });
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
