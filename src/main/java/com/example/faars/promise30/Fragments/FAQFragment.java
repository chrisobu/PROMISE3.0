package com.example.faars.promise30.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.faars.promise30.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FAQFragment extends Fragment implements View.OnClickListener{


    public FAQFragment() {
        // Required empty public constructor
    }

    TextView answer1, answer2, answer3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_faq, container, false);

        ImageView show1 = (ImageView) viewGroup.findViewById(R.id.show_answer_1);
        ImageView show2 = (ImageView) viewGroup.findViewById(R.id.show_answer_2);
        ImageView show3 = (ImageView) viewGroup.findViewById(R.id.show_answer_3);
        answer1 = (TextView) viewGroup.findViewById(R.id.Answers);
        answer2 = (TextView) viewGroup.findViewById(R.id.Answers2);
        answer3 = (TextView) viewGroup.findViewById(R.id.Answers3);

        show1.setOnClickListener(this);
        show2.setOnClickListener(this);
        show3.setOnClickListener(this);

        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show_answer_1:
                if(answer1.getVisibility() == View.VISIBLE){
                    answer1.setVisibility(View.GONE);
                    answer2.setVisibility(View.GONE);
                    answer3.setVisibility(View.GONE);
                }else {
                    answer1.setVisibility(View.VISIBLE);
                    answer2.setVisibility(View.GONE);
                    answer3.setVisibility(View.GONE);
                }
                break;
            case R.id.show_answer_2:
                if(answer2.getVisibility() == View.VISIBLE){
                    answer1.setVisibility(View.GONE);
                    answer2.setVisibility(View.GONE);
                    answer3.setVisibility(View.GONE);
                }else {
                    answer2.setVisibility(View.VISIBLE);
                    answer1.setVisibility(View.GONE);
                    answer3.setVisibility(View.GONE);
                }
                break;
            case R.id.show_answer_3:
                if(answer3.getVisibility() == View.VISIBLE){
                    answer1.setVisibility(View.GONE);
                    answer2.setVisibility(View.GONE);
                    answer3.setVisibility(View.GONE);
                }else {
                    answer3.setVisibility(View.VISIBLE);
                    answer2.setVisibility(View.GONE);
                    answer1.setVisibility(View.GONE);
                }
                break;
        }
    }
}
