package com.example.baking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baking.adapters.IngredientsAdapter;
import com.example.baking.adapters.StepAdapter;
import com.example.baking.models.Steps;

import java.util.List;


public class StepsFragment extends Fragment {

List<Steps> stepsList;
    public StepsFragment(List<Steps> steps) {
        // Required empty public constructor
        stepsList = steps;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);
        StepAdapter mAdapter = new StepAdapter(stepsList);
        RecyclerView mRecyclerView = rootView.findViewById(R.id.steps_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }
}
