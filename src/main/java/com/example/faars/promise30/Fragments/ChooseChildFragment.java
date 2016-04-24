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

import com.example.faars.promise30.Dialogs.ChildActionDialog;
import com.example.faars.promise30.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseChildFragment extends Fragment implements View.OnClickListener{


    public ChooseChildFragment() {
        // Required empty public constructor
    }

    /*It's generally a good practice to define keys for intent extras using your app's package name
    as a prefix. This ensures the keys are unique, in case your app interacts with other apps: */
    public final static String EXTRA_CHILDNAME = "com.example.faars.promise20";
    ListView lvRegisteredChildren;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_choose_child, container, false);
        //TODO: Get registered children on current profile and display in listView: R.id.lvRegisteredChild

        lvRegisteredChildren = (ListView) viewGroup.findViewById(R.id.lvRegisteredChild);

        TextView orRegisterNewChild = (TextView) viewGroup.findViewById(R.id.orRegisterNewChildOption);
        TextView profilePreview = (TextView) viewGroup.findViewById(R.id.profilePreview);

        SpannableString content = new SpannableString("or register a new child");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        orRegisterNewChild.setText(content);


        orRegisterNewChild.setOnClickListener(this);

        // TODO: replace with saved children list:
        String[] ListNames = new String[] { "Alfred",
                "Frida",
                "Theo"
        };

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

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                // Create and show the dialog.
                ChildActionDialog newDialog = new ChildActionDialog();
                newDialog.show(ft, "dialog");

                /* A Toast message when a child is chosen:
                // ListView Clicked item index
                int itemPosition = position;
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show(); */
            }
        });

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
