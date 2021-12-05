package com.example.a20211201_chunlin_nycschools;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a20211201_chunlin_nycschools.click_listeners.EmailClickListener;
import com.example.a20211201_chunlin_nycschools.click_listeners.LocationClickListener;
import com.example.a20211201_chunlin_nycschools.click_listeners.PhoneClickListener;
import com.example.a20211201_chunlin_nycschools.click_listeners.SiteClickListener;
import com.example.a20211201_chunlin_nycschools.databinding.ViewholderLayoutBinding;
import com.example.a20211201_chunlin_nycschools.model.NYCSchoolModel;

import java.util.ArrayList;
import java.util.List;

public class NYCSchoolsRecyclerViewAdapter
        extends RecyclerView.Adapter<NYCSchoolsRecyclerViewAdapter.ViewHolder> {

    private List<NYCSchoolModel> listOfSchools;
    private final EmailClickListener emailClickListener;
    private final LocationClickListener locationClickListener;
    private final PhoneClickListener phoneClickListener;
    private final SiteClickListener siteClickListener;
    NYCSchoolsRecyclerViewAdapter(List<NYCSchoolModel> items, EmailClickListener emailClickListener, LocationClickListener locationClickListener, PhoneClickListener phoneClickListener, SiteClickListener siteClickListener) {
        listOfSchools = items;
        this.emailClickListener = emailClickListener;
        this.locationClickListener = locationClickListener;
        this.phoneClickListener = phoneClickListener;
        this.siteClickListener = siteClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewholderLayoutBinding binding =
                ViewholderLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final String email = listOfSchools.get(position).getSchoolEmail();
        final String phone = listOfSchools.get(position).getPhoneNumber();
        final String location = listOfSchools.get(position).getFormattedLocation();
        final String site = listOfSchools.get(position).getWebsite();
        final String gpsLocation = String.format("geo:0,0?q=%s",location);

        holder.name.setText(listOfSchools.get(position).getSchoolName());
        holder.phone.setText(phone);
        holder.email.setText(email);
        holder.site.setText(site);
        holder.location.setText(location);
        holder.paragraph.setText(listOfSchools.get(position).getOverviewParagraph());
        holder.readScore.setText(String.format("SAT Reading Average: %s", listOfSchools.get(position).getSatAvgReadScore()));
        holder.mathScore.setText(String.format("SAT Math Average: %s", listOfSchools.get(position).getSatAvgMathScore()));
        holder.writeScore.setText(String.format("SAT Writing Average: %s", listOfSchools.get(position).getSatAvgWriteScore()));
        //set position as the tag for each textview so we can retrieve it later when view is clicked
        holder.email.setTag(position);
        holder.location.setTag(position);
        holder.phone.setTag(position);
        holder.site.setTag(position);

        holder.email.setOnClickListener(emailClickListener.setData(email, position));
        holder.phone.setOnClickListener(phoneClickListener.setData(phone, position));
        holder.location.setOnClickListener(locationClickListener.setData(gpsLocation, position));
        holder.site.setOnClickListener(siteClickListener.setData(site, position));
    }

    @Override
    public int getItemCount() {
        return listOfSchools.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFilter(ArrayList<NYCSchoolModel> newList){
        listOfSchools = new ArrayList<>();
        listOfSchools.addAll(newList);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final LinearLayout schoolStats;
        final TextView name;
        final TextView phone;
        final TextView email;
        final TextView site;
        final TextView location;
        final TextView paragraph;
        final TextView readScore;
        final TextView mathScore;
        final TextView writeScore;

        ViewHolder(ViewholderLayoutBinding binding) {
            super(binding.getRoot());
            schoolStats = binding.schoolInfo;
            name = binding.schoolName;
            phone = binding.schoolPhone;
            email = binding.schoolEmail;
            site = binding.schoolSite;
            location = binding.schoolLocation;
            paragraph = binding.schoolParagraph;
            readScore = binding.satReadScore;
            mathScore = binding.satMathScore;
            writeScore = binding.satWriteScore;
        }

    }
}
