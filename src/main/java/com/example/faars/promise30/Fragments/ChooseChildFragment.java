package com.example.faars.promise30.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faars.promise30.Dialogs.ChildActionDialog;
import com.example.faars.promise30.MainActivity;
import com.example.faars.promise30.R;
import com.example.faars.promise30.SQL.MyDBHandler;

import java.util.ArrayList;
import java.util.List;


public class ChooseChildFragment extends Fragment implements View.OnClickListener{

    public ChooseChildFragment() {
        // Required empty public constructor
    }

    ListView lvRegisteredChildren;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_choose_child, container, false);

        lvRegisteredChildren = (ListView) viewGroup.findViewById(R.id.lvRegisteredChild);
        TextView orRegisterNewChild = (TextView) viewGroup.findViewById(R.id.orRegisterNewChildOption);
        TextView noChildRegistered = (TextView) viewGroup.findViewById(R.id.no_child_registered);

        // Underline "Or Register a new child"- text
        SpannableString content = new SpannableString("or register a new child");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        orRegisterNewChild.setText(content);

        orRegisterNewChild.setOnClickListener(this);

        // Get List of child names for current profile:
        MyDBHandler dbHandler = MyDBHandler.getInstance(getActivity());
        ArrayList<String> ListNames = dbHandler.getAllProfileChildren(dbHandler.getCurrentProfile());

        if(ListNames.size()>0){
            // Define a new Adapter
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_selectable_list_item, android.R.id.text1, ListNames);

            // Assign adapter to ListView
            lvRegisteredChildren.setAdapter(adapter);

            // ListView Item Click Listener
            lvRegisteredChildren.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // ListView Clicked item value
                    String  itemValue    = (String) lvRegisteredChildren.getItemAtPosition(position);

                    // Selected name gets highlighted:
                    for(int a = 0; a < parent.getChildCount(); a++) {
                        parent.getChildAt(a).setBackgroundColor(Color.TRANSPARENT);
                    }
                    view.setBackgroundColor(getResources().getColor(R.color.colorSelected));

                    // Update current child
                    MyDBHandler dbHandler = MyDBHandler.getInstance(getActivity()); //cannot be global!
                    dbHandler.updateCurrentChild(itemValue);

                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    // Create and show the dialog.
                    ChildActionDialog newDialog = new ChildActionDialog();
                    newDialog.show(ft, "dialog");
                }
            });
        }else{
            noChildRegistered.setVisibility(View.VISIBLE);
        }

        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        android.support.v4.app.FragmentTransaction fragmentTransactionLogIn;
        fragmentTransactionLogIn = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransactionLogIn.replace(R.id.child_container, new ReadQRcodeFragment());
        fragmentTransactionLogIn.addToBackStack(null);
        fragmentTransactionLogIn.commit();
    }
}