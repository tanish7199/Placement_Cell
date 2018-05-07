package com.gmail.placement_cell;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanish on 15-03-2018.
 */

public class CompaniesAdapter extends ArrayAdapter<CompaniesList> {

    public CompaniesAdapter(Context context, List<CompaniesList> companiesList) {
        super(context, R.layout.grid_view, companiesList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View ConvertView, @NonNull ViewGroup parent) {
        ViewHolder Viewholder;
        Integer[] Images = {
                R.drawable.ab, R.drawable.cd,
                R.drawable.ef, R.drawable.gh,
                R.drawable.ij, R.drawable.kl,
                R.drawable.mn, R.drawable.op,
                R.drawable.qr, R.drawable.st
        };
        if (ConvertView == null) {
            ConvertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.grid_view, parent, false);
            Viewholder = new ViewHolder(ConvertView);
            ConvertView.setTag(Viewholder);
        } else
            Viewholder = (ViewHolder) ConvertView.getTag();
        CompaniesList companiesList = getItem(position);
        Viewholder.CompanyName.setText(companiesList.getName());
        Viewholder.CompanyImage.setImageResource(companiesList.getImage());
        //Viewholder.CompanyImage2.setImageResource(companiesList.getName1()); /*Set Image Resource*/
        //Viewholder.CompanyImage1.setText(companiesList.getName2()); /*Set Image Resource here*/
        return (ConvertView);
    }

    public static class ViewHolder
    {
        TextView CompanyName;
        ImageView CompanyImage;

        public ViewHolder(View ConvertView) {
            CompanyImage = ConvertView.findViewById(R.id.CompanyImage);
            CompanyName = ConvertView.findViewById(R.id.CompanyName);
        }

    }


}
