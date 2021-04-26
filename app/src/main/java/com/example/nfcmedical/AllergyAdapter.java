package com.example.nfcmedical;

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

public class AllergyAdapter extends RecyclerView.Adapter<AllergyAdapter.AllergyViewHolder> {

    private Context mContext;
    private ArrayList<String> mAllergyResults = new ArrayList<>();
    

    public AllergyAdapter(Context mContext, ArrayList<String> mAllergyResults) {
        this.mAllergyResults = mAllergyResults;
        this.mContext = mContext;
    }

    //create a tag for debugging purposes
    private static final String TAG = "AllergyAdapter";

    @NonNull
    @Override
    public AllergyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alllergy_layout, parent, false);
        AllergyViewHolder holder = new AllergyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllergyViewHolder holder, int position) {
        //log so we know which item fails in the event of a fail
        Log.d(TAG, "onBindViewHolder: called");
        holder.algyResultTextView.setText(mAllergyResults.get(position));
    }

    @Override
    public int getItemCount() {
        return mAllergyResults.size();
    }

    public class AllergyViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout parentLayout;
        TextView algyResultTextView;
        

        public AllergyViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parentLayout);
            algyResultTextView = itemView.findViewById(R.id.algyResultTextView);
        }
    }
}
