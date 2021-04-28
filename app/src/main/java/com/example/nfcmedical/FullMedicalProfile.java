package com.example.nfcmedical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FullMedicalProfile extends AppCompatActivity {

    ExpandableListView fullMedProfile;
    String[] categories;
    ArrayList<String> profileHeadings = new ArrayList<>();
    HashMap<String, ArrayList<String>> categoryResults = new HashMap<>();
    CategoryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_medical_profile);

        fullMedProfile = findViewById(R.id.fullMedProfile);
        //populate array with names of categories in the full profile
        Resources res = getResources();
        categories = res.getStringArray(R.array.categories);
        //convert to ArrayList
        List<String> headings = Arrays.asList(categories);
        profileHeadings = new ArrayList<>(headings);

        //NEED TO MODIFY SO categoryResults IS POPULATED WITH ACTUAL RESULTS FROM QUERYING THE DB
        //DATA WITH MULTIPLE FIELD NEEDS TO BE CONVERTED TO A STRING THAT WILL DISPLAY ON ONE LINE
        //FOR EXAMPLE, IN MEDICATIONS: levothyroxine 75 mcg 1x/day

        //currently using for loop to populate placeholder data items for results in each category
        for (int i = 0; i < profileHeadings.size(); i++) {
            //create an arrayList to initially hold child values (database results for this app)
            ArrayList<String> children = new ArrayList<>();
            //add 5 placeholder items for each category
            for (int j = 0; j < 5; j++) {
                children.add("Item " + j);
            }
            //add values to categoryResults Hash Map
            categoryResults.put(profileHeadings.get(i), children);
        }

        adapter = new CategoryAdapter(profileHeadings, categoryResults);
        fullMedProfile.setAdapter(adapter);
    }
}