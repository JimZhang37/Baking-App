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

    private String PLAYER_CURRENT_POS_KEY = "PLAYER_CURRENT_POS_KEY";
    private String PLAYER_IS_READY_KEY = "PLAYER_IS_READY_KEY";
    private int positionStep;
    private int positionRecipe;
    private PlayerView mPlayerView;
    private SimpleExoPlayer mPlayer;
    private List<Recipe> mRecipes;
    private TextView tvStepDestription;
    private Button mPrevious;
    private Button mNext;
    private ButtonClickListener mButtonClickListener;
    private Bundle savedInstanceState;

    public interface ButtonClickListener {
        void onPreviousClick(int currentPosition);
        void onNextClick(int currentPosition);
    }

    public FragmentStep() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_step, container, false);

        tvStepDestription = rootView.findViewById(R.id.tv_step_description);
        mPlayerView = rootView.findViewById(R.id.player_view);
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

        if(savedInstanceState != null){
            this.savedInstanceState = savedInstanceState;
        }
        observeStep();
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

//                        String url = "http://120.77.95.13/-intro-cheesecake.mp4";
                        String url =recipes.get(positionRecipe).getSteps().get(positionStep).getVideoURL() ;
                        //TODO Check if there is a video url or not unless you should display thumbnail instead
                        initializePlayer(Uri.parse(url));
                        Log.d("onChanged", "FragmentStep");
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
        if (mPlayer != null) {
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                    Util.getUserAgent(getContext(), "yourApplicationName"));
            MediaSource videoSource =
                    new ProgressiveMediaSource.Factory(dataSourceFactory)
                            .createMediaSource(mp4VideoUri);
            //TODO You should save state and position of exoplayer before rotation.
            // Prepare the mPlayer with the source.

            mPlayer.prepare(videoSource);
            if(savedInstanceState != null){
                mPlayer.setPlayWhenReady(savedInstanceState.getBoolean(PLAYER_IS_READY_KEY));
                mPlayer.seekTo(savedInstanceState.getLong(PLAYER_CURRENT_POS_KEY));

            }else {
                mPlayer.setPlayWhenReady(true);
            }
            //TODO make mPlayer full screen in phone's landscape mode.
        }
    }

    public void releasePlayer() {
        mPlayer.stop();
        mPlayer.release();
        mPlayer = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPlayer = new SimpleExoPlayer.Builder(getContext()).build();
        mPlayerView.setPlayer(mPlayer);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPlayer.stop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(PLAYER_CURRENT_POS_KEY, Math.max(0, mPlayer.getCurrentPosition()));
        outState.putBoolean(PLAYER_IS_READY_KEY, mPlayer.getPlayWhenReady());
    }
}
