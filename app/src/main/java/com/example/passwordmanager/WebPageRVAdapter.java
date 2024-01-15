package com.example.passwordmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WebPageRVAdapter extends ListAdapter<LoginDetailsModal, WebPageRVAdapter.ViewHolder> implements Filterable {

    private OnItemClickListener listener;
    private List<LoginDetailsModal> detailList = new ArrayList<>();
    private List<LoginDetailsModal> detailsListFull = new ArrayList<>();


    // creating a constructor class for our adapter class.
    WebPageRVAdapter() {
        super(DIFF_CALLBACK);
    }

    // creating a call back for item of recycler view.
    private static final DiffUtil.ItemCallback<LoginDetailsModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<LoginDetailsModal>() {
        @Override
        public boolean areItemsTheSame(LoginDetailsModal oldItem, LoginDetailsModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(LoginDetailsModal oldItem, LoginDetailsModal newItem) {
            return oldItem.getPlatformName().equals(newItem.getPlatformName()) &&
                    oldItem.getUsername().equals(newItem.getUsername()) &&
                    oldItem.getPassword().equals(newItem.getPassword());
        }
    };


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_web_page_rvadapter, parent, false);
        return new ViewHolder(item);
    }

    // Binds data to the views in each ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        LoginDetailsModal model = getDetailAt(position);
        holder.platformNameTV.setText(model.getPlatformName());
        holder.usernameTV.setText(model.getUsername());
        holder.passwordTV.setText(model.getPassword());
    }

    // Gets the LoginDetailsModal object at a given position
    public LoginDetailsModal getDetailAt(int position) {
        return getItem(position);
    }

    // Start of filer it is not working correctly
    @Override
    public Filter getFilter() {
        return detailsFilter;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView platformNameTV, usernameTV, passwordTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Give components variable names
            platformNameTV = itemView.findViewById(R.id.tvPlatformName);
            usernameTV = itemView.findViewById(R.id.tvUsername);
            passwordTV = itemView.findViewById(R.id.tvPassword);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(LoginDetailsModal model);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    // filter stuff I didn't get it to work
    private Filter detailsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence sequence) {
            List<LoginDetailsModal> filteredList = new ArrayList<>();

            if (sequence == null || sequence.length() == 0) {
                filteredList.addAll(detailsListFull);
            } else {
                String filterPattern = sequence.toString().toLowerCase().trim();

                for (LoginDetailsModal item : detailsListFull) {
                    if (item.getPlatformName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            detailList.clear();
            detailList.addAll((List<LoginDetailsModal>) results.values);
            notifyDataSetChanged();
        }
    };




}


