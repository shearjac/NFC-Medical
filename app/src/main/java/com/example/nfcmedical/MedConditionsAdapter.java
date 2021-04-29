package com.example.nfcmedical;
/*
 * MedConditionsAdapter.java
 * Written by Shannon Purrington for SE 475, Spring 2021
 */

import android.content.Context;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;
        import java.util.ArrayList;

public class MedConditionsAdapter extends RecyclerView.Adapter<MedConditionsAdapter.ConditionsViewHolder> {

    private Context mContext;
    private ArrayList<String> mConditionsResults = new ArrayList<>();


    public MedConditionsAdapter(Context mContext, ArrayList<String> mConditionsResults) {
        this.mConditionsResults = mConditionsResults;
        this.mContext = mContext;
    }

    //create a tag for debugging purposes
    private static final String TAG = "MedConditionsAdapter";

    @NonNull
    @Override
    public ConditionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.med_conditions_layout, parent, false);
        ConditionsViewHolder holder = new ConditionsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConditionsViewHolder holder, int position) {
        //log so we know which item fails in the event of a fail
        Log.d(TAG, "onBindViewHolder: called");
        holder.conditionTextView.setText(mConditionsResults.get(position));
    }

    @Override
    public int getItemCount() {
        return mConditionsResults.size();
    }

    public class ConditionsViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout parentLayout;
        TextView conditionTextView;


        public ConditionsViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parentLayout);
            conditionTextView = itemView.findViewById(R.id.conditionTextView);
        }
    }
}

