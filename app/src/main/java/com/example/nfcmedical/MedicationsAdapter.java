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

public class MedicationsAdapter extends RecyclerView.Adapter<MedicationsAdapter.MedicationViewHolder> {

    private Context mContext;
    private ArrayList<String> mMedResults = new ArrayList<>();


    public MedicationsAdapter(Context mContext, ArrayList<String> mMedResults) {
        this.mMedResults = mMedResults;
        this.mContext = mContext;
    }

    //create a tag for debugging purposes
    private static final String TAG = "MedicationsAdapter";

    @NonNull
    @Override
    public MedicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medications_layout, parent, false);
        MedicationViewHolder holder = new MedicationViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MedicationViewHolder holder, int position) {
        //log so we know which item fails in the event of a fail
        Log.d(TAG, "onBindViewHolder: called");
        holder.medResultTextView.setText(mMedResults.get(position));
    }

    @Override
    public int getItemCount() {
        return mMedResults.size();
    }

    public class MedicationViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout parentLayout;
        TextView medResultTextView;


        public MedicationViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parentLayout);
            medResultTextView = itemView.findViewById(R.id.algyResultTextView);
        }
    }
}
