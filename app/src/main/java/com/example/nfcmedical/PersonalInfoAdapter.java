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

public class PersonalInfoAdapter extends RecyclerView.Adapter<PersonalInfoAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> mLabels = new ArrayList<>();
    private ArrayList<String> mResults = new ArrayList<>();

    public PersonalInfoAdapter(Context mContext, ArrayList<String> mLabels, ArrayList<String> mResults) {
        this.mLabels = mLabels;
        this.mResults = mResults;
        this.mContext = mContext;
    }

    //create a tag for debugging purposes
    private static final String TAG = "PersonalInfoAdapter";

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.personal_info_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //log so we know which item fails in the event of a fail
        Log.d(TAG, "onBindViewHolder: called");
        holder.labelTextView.setText(mLabels.get(position));
        holder.resultTextView.setText(mResults.get(position));
    }

    @Override
    public int getItemCount() {
        return mLabels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout parentLayout;
        TextView labelTextView;
        TextView resultTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parentLayout);
            labelTextView = itemView.findViewById(R.id.labelTextView);
            resultTextView = itemView.findViewById(R.id.resultTextView);
        }
    }
}
