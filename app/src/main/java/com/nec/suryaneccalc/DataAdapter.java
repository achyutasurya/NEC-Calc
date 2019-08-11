package com.nec.suryaneccalc;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>  {


    private ArrayList<String> displayText=new ArrayList<>();

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {

        holder.av.setHeadingString(displayText.get(position));
        holder.av.setHeadingBackGroundColor(Color.parseColor("#4DB6AC"));
        holder.av.setBodyBackGroundColor(Color.parseColor("#E0F2F1"));

        if(AnalysisFragment.isThereMarks[position]) {

            holder.xas1.setText(AnalysisFragment.as1.get(position));
            holder.xas2.setText(AnalysisFragment.as2.get(position));
            holder.xmid.setText(AnalysisFragment.mid.get(position));
            holder.xobj.setText(AnalysisFragment.obj.get(position));
            holder.xtotal.setText(AnalysisFragment.totalCount.get(position));
            holder.xper25.setText(AnalysisFragment.per25Count.get(position));
            holder.xper75.setText(AnalysisFragment.per75Count.get(position));

            if (AnalysisFragment.noTarget[position] == 0) {
                holder.targetText.setText("No");
                holder.target.setText("Targets");
                holder.target.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f);
            } else if(AnalysisFragment.noTarget[position] == 2) {
                ((GradientDrawable) holder.circle.getBackground()).setColor(Color.parseColor("#EF5350"));
                holder.target.setText(AnalysisFragment.targetMarksArray.get(position));
            } else {
                ((GradientDrawable) holder.circle.getBackground()).setColor(Color.parseColor("#FF7043"));
                holder.target.setText(AnalysisFragment.targetMarksArray.get(position));
            }
        }else {
            holder.nothingToShow.setVisibility(View.VISIBLE);
            ((GradientDrawable) holder.circle.getBackground()).setColor(Color.parseColor("#E0F2F1"));
            holder.xas1.setVisibility(View.INVISIBLE);
            holder.xas2.setVisibility(View.INVISIBLE);
            holder.xmid.setVisibility(View.INVISIBLE);
            holder.xobj.setVisibility(View.INVISIBLE);
            holder.xtotal.setVisibility(View.INVISIBLE);
            holder.xper25.setVisibility(View.INVISIBLE);
            holder.xper75.setVisibility(View.INVISIBLE);
            holder.circle.setVisibility(View.INVISIBLE);
            holder.targetText.setVisibility(View.INVISIBLE);
            holder.target.setVisibility(View.INVISIBLE);

            holder.peras1.setVisibility(View.INVISIBLE);
            holder.peras2.setVisibility(View.INVISIBLE);
            holder.permid.setVisibility(View.INVISIBLE);
            holder.perobj.setVisibility(View.INVISIBLE);
            holder.perTotal.setVisibility(View.INVISIBLE);
            holder.per25.setVisibility(View.INVISIBLE);
            holder.per75.setVisibility(View.INVISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return displayText.size();
    }

    DataAdapter(ArrayList<String> displayText){

        this.displayText=displayText;


    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView xas1, xas2, xmid, xobj, xtotal, xper25, xper75, target, targetText, nothingToShow;
        private TextView peras1, peras2, permid, perobj, perTotal, per25, per75;
        private com.riyagayasen.easyaccordion.AccordionView av;
        private LinearLayout circle;


        public ViewHolder(View itemView) {
            super(itemView);
            av= itemView.findViewById(R.id.tv_country);
            xas1=itemView.findViewById(R.id.ass1TextView);
            xas2=itemView.findViewById(R.id.ass2TextView);
            xmid=itemView.findViewById(R.id.midTextView);
            xobj=itemView.findViewById(R.id.objTextView);
            xtotal=itemView.findViewById(R.id.totalTextView);
            xper25=itemView.findViewById(R.id.per25TextView);
            xper75=itemView.findViewById(R.id.per75TextView);
            circle = itemView.findViewById(R.id.shareLinearLayout);
            target = itemView.findViewById(R.id.targetMarksText);
            targetText = itemView.findViewById(R.id.targetText);
            nothingToShow = itemView.findViewById(R.id.textViewNoMarks);

            peras1= itemView.findViewById(R.id.perTextas1);
            peras2=itemView.findViewById(R.id.perTextas2);
            permid=itemView.findViewById(R.id.perTextmid);
            perobj=itemView.findViewById(R.id.perTextObj);
            perTotal=itemView.findViewById(R.id.perTextTotal);
            per25=itemView.findViewById(R.id.perText25);
            per75=itemView.findViewById(R.id.perText75);
        }
    }

}
