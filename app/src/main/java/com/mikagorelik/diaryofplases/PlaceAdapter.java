package com.mikagorelik.diaryofplases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Place place);
    }

    private final List<Place> places;
    private final OnItemClickListener listener;

    public PlaceAdapter(List<Place> places, OnItemClickListener listener) {
        this.places = places;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View placeView = inflater.inflate(R.layout.list_item_place, parent, false);
        return new PlaceViewHolder(placeView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        Place place = places.get(position);
        holder.bind(place, listener);
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewPlaceName;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            textViewPlaceName = itemView.findViewById(R.id.textViewPlaceName);
        }

        public void bind(final Place place, final OnItemClickListener listener) {
            textViewPlaceName.setText(place.getPlaceName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(place);
                }
            });
        }
    }
}
