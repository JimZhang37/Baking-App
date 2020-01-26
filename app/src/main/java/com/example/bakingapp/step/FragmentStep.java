package com.example.bakingapp.step;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.bakingapp.R;
import com.example.bakingapp.RecipeApplication;
import com.example.bakingapp.data.Recipe;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

public class FragmentStep extends Fragment {
    private int positionStep;
    private int positionRecipe;

    private PlayerView mPlayerView;
    private SimpleExoPlayer mPlayer;

    //    private Step step;
    private List<Recipe> mRecipes;
    private TextView tvStepDestription;
    private Button mPrevious;
    private Button mNext;

    private ButtonClickListener mButtonClickListener;

    public interface ButtonClickListener {
        void onPreviousClick(int currentPosition);

        void onNextClick(int currentPosition);

    }

    public FragmentStep() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_step, container, false);

//        Intent intent = getActivity().getIntent();
//        positionStep = intent.getIntExtra(ActivityRecipe.EXTRA_INT_STEP, positionStep);
//            positionRecipe = intent.getIntExtra(ActivityRecipe.EXTRA_INT_RECIPE, 0);

        tvStepDestription = rootView.findViewById(R.id.tv_step_description);
        observeStep();

        mPlayerView = rootView.findViewById(R.id.player_view);
        String url = "http://120.77.95.13/-intro-cheesecake.mp4";
        initializePlayer(Uri.parse(url));

        mPrevious = rootView.findViewById(R.id.button_previous);
        mNext = rootView.findViewById(R.id.button_next);

        mPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mButtonClickListener != null && positionStep > 0) {

                    mButtonClickListener.onPreviousClick(positionStep);
                }
            }
        });

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonClickListener != null && positionStep < mRecipes.get(positionRecipe).getSteps().size() - 1) {
                    mButtonClickListener.onNextClick(positionStep);
                }
            }
        });

        return rootView;

    }

    private void observeStep() {
        ((RecipeApplication) requireContext().getApplicationContext()).getRepository().getRecipes().observe(
                this, new Observer<List<Recipe>>() {
                    @Override
                    public void onChanged(List<Recipe> recipes) {
                        mRecipes = recipes;
                        getActivity().setTitle(recipes.get(positionRecipe).getName());
                        tvStepDestription.setText(recipes.get(positionRecipe).getSteps().get(positionStep).getDescription());

                        Log.d("AAAAA", "data of recipe received. Name is:" + recipes.get(0).getName()
                                + ". Image is:"
                                + recipes.get(0).getImage()
                        );
                    }
                }
        );
    }

    public void setPosition(int step, int recipe, ButtonClickListener listener) {
        positionStep = step;
        positionRecipe = recipe;
        mButtonClickListener = listener;

    }

    public void initializePlayer(Uri mp4VideoUri) {
        mPlayer = new SimpleExoPlayer.Builder(getContext()).build();
        mPlayerView.setPlayer(mPlayer);

        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                Util.getUserAgent(getContext(), "yourApplicationName"));

        // This is the MediaSource representing the media to be played.
        MediaSource videoSource =
                new ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(mp4VideoUri);

        // Prepare the mPlayer with the source.
        mPlayer.prepare(videoSource);
        mPlayer.setPlayWhenReady(true);

    }

    public void releasePlayer() {
        mPlayer.stop();
        mPlayer.release();
        mPlayer = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }
}
