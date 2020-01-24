package com.example.bakingapp.recipe;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.data.Step;

import java.util.List;


public class AdapterStep extends RecyclerView.Adapter<AdapterStep.VHStep> {

    private List<Step> steps;
    private ListItemClickeListener mListener;
    private int mRecipePosition;


    public AdapterStep(ListItemClickeListener mListener, int recipePosition) {
        this.mListener = mListener;
        mRecipePosition = recipePosition;
    }

    interface ListItemClickeListener {
        void onListItemClicked(int position, int recipePosition);
    }

    public void updateData(List<Step> st){
        steps= st;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull VHStep holder, int position) {
        holder.bind(position);

    }

    @NonNull
    @Override
    public VHStep onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_step,parent,false);

        return new VHStep(tv);
    }

    @Override
    public int getItemCount() {
        if(steps == null){return 0;}
        return steps.size();
    }

    class VHStep extends RecyclerView.ViewHolder{
        private TextView tv;
        public VHStep(@NonNull TextView itemView) {
            super(itemView);
            tv = itemView;
        }

        public void bind(final int position){
            tv.setText(steps.get(position).getShortDescription());
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onListItemClicked(position, mRecipePosition);
                }
            });
        }
    }
}
