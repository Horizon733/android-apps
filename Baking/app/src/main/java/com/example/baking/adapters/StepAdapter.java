package com.example.baking.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baking.databinding.RecipeListItemBinding;
import com.example.baking.databinding.StepsListItemBinding;
import com.example.baking.models.Steps;

import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepsViewHolder> {
    List<Steps> mSteps;
    public StepAdapter(List<Steps> steps){
        this.mSteps = steps;
    }
    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        StepsListItemBinding itemBinding = StepsListItemBinding.inflate(layoutInflater,parent,false);
        return new StepAdapter.StepsViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder holder, int position) {
        Steps steps =  mSteps.get(position);
        holder.stepsListItemBinding.stepsBtn.setText((steps.getId())+1+" "+steps.getShortDescription());
    }

    @Override
    public int getItemCount() {
       if(mSteps.size()==0 || mSteps == null){
           return -1;
       }
       return mSteps.size();
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder{
        StepsListItemBinding stepsListItemBinding;
        public StepsViewHolder(@NonNull StepsListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.stepsListItemBinding = itemBinding;
    }
    }
}
