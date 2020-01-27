package com.example.bakingapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.data.Recipe;

import java.util.List;

public class AdapterRecipe extends RecyclerView.Adapter<AdapterRecipe.VHRecipe> {

    private List<Recipe> mRecipes;
    private ListItemClickeListener mListener;

    public AdapterRecipe(ListItemClickeListener mListener) {
        this.mListener = mListener;
    }

    public void updateData(List<Recipe> recipes) {
        mRecipes = recipes;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull VHRecipe holder, int position) {
        holder.bind(position);
    }

    @NonNull
    @Override
    public VHRecipe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView layout = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_recipe, parent, false);
        return new VHRecipe(layout);
    }

    @Override
    public int getItemCount() {
        if (mRecipes == null) {
            return 0;
        }
        return mRecipes.size();
    }

    interface ListItemClickeListener {
        void onListItemClicked(int position);
    }

    class VHRecipe extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mImage;
        private View rootView;

        public VHRecipe(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.recipe_name);
            mImage = itemView.findViewById(R.id.recipe_image);
            rootView = itemView;

        }

        public void bind(final int position) {
            String name = mRecipes.get(position).getName();
            String image = mRecipes.get(position).getImage();
            mName.setText(name);
            mImage.setText(image);

            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onListItemClicked(position);
                }
            });
        }

    }

}
