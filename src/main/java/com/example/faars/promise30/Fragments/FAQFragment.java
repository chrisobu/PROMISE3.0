package com.example.faars.promise30.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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

    TextView answer1, answer2, answer3, question1, question2, question3;
    ImageView show1, show2, show3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_faq, container, false);

        show1 = (ImageView) viewGroup.findViewById(R.id.show_answer_1);
        show2 = (ImageView) viewGroup.findViewById(R.id.show_answer_2);
        show3 = (ImageView) viewGroup.findViewById(R.id.show_answer_3);
        answer1 = (TextView) viewGroup.findViewById(R.id.Answers);
        answer2 = (TextView) viewGroup.findViewById(R.id.Answers2);
        answer3 = (TextView) viewGroup.findViewById(R.id.Answers3);
        question1 = (TextView) viewGroup.findViewById(R.id.question1);
        question2 = (TextView) viewGroup.findViewById(R.id.question2);
        question3 = (TextView) viewGroup.findViewById(R.id.question3);

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
                    question1.setTextColor(getResources().getColor(R.color.textColor));
                    show1.setImageResource(R.drawable.ic_add_circle_outline_24dp);
                    answer1.setVisibility(View.GONE);
                    answer2.setVisibility(View.GONE);
                    answer3.setVisibility(View.GONE);
                }else {
                    question1.setTextColor(getResources().getColor(R.color.colorPrimary));
                    show1.setImageResource(R.drawable.ic_remove_circle_outline_24dp);
                    show2.setImageResource(R.drawable.ic_add_circle_outline_24dp);
                    show3.setImageResource(R.drawable.ic_add_circle_outline_24dp);
                    question2.setTextColor(getResources().getColor(R.color.textColor));
                    question3.setTextColor(getResources().getColor(R.color.textColor));
                    answer1.setVisibility(View.VISIBLE);
                    answer2.setVisibility(View.GONE);
                    answer3.setVisibility(View.GONE);
                }
                break;
            case R.id.show_answer_2:
                if(answer2.getVisibility() == View.VISIBLE){
                    question2.setTextColor(getResources().getColor(R.color.textColor));
                    show2.setImageResource(R.drawable.ic_add_circle_outline_24dp);
                    answer1.setVisibility(View.GONE);
                    answer2.setVisibility(View.GONE);
                    answer3.setVisibility(View.GONE);
                }else {
                    question2.setTextColor(getResources().getColor(R.color.colorPrimary));
                    show2.setImageResource(R.drawable.ic_remove_circle_outline_24dp);
                    show1.setImageResource(R.drawable.ic_add_circle_outline_24dp);
                    show3.setImageResource(R.drawable.ic_add_circle_outline_24dp);
                    question1.setTextColor(getResources().getColor(R.color.textColor));
                    question3.setTextColor(getResources().getColor(R.color.textColor));
                    answer2.setVisibility(View.VISIBLE);
                    answer1.setVisibility(View.GONE);
                    answer3.setVisibility(View.GONE);
                }
                break;
            case R.id.show_answer_3:
                if(answer3.getVisibility() == View.VISIBLE){
                    question3.setTextColor(getResources().getColor(R.color.textColor));
                    show3.setImageResource(R.drawable.ic_add_circle_outline_24dp);
                    answer1.setVisibility(View.GONE);
                    answer2.setVisibility(View.GONE);
                    answer3.setVisibility(View.GONE);
                }else {
                    question3.setTextColor(getResources().getColor(R.color.colorPrimary));
                    show3.setImageResource(R.drawable.ic_remove_circle_outline_24dp);
                    show1.setImageResource(R.drawable.ic_add_circle_outline_24dp);
                    show2.setImageResource(R.drawable.ic_add_circle_outline_24dp);
                    question2.setTextColor(getResources().getColor(R.color.textColor));
                    question1.setTextColor(getResources().getColor(R.color.textColor));
                    answer3.setVisibility(View.VISIBLE);
                    answer2.setVisibility(View.GONE);
                    answer1.setVisibility(View.GONE);
                }
                break;
        }
    }
}
