package com.mikagorelik.diaryofplases;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    public interface OnInnerContactClickListener {
        void onItemClick(myContact contact);
    }

    private final List<?> items; // Can be List<myContact> or List<String>
    private final OnInnerContactClickListener listener;

    public ContactAdapter(List<?> items, OnInnerContactClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Object item = items.get(position);
        if (item instanceof myContact) {
            myContact contact = (myContact) item;
            holder.bind(contact, listener);
        } else if (item instanceof String) {
            String contactString = (String) item;
            holder.bind(contactString);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView contactName;
        TextView contactDetails;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            contactName = itemView.findViewById(R.id.textViewContactName);
            contactDetails = itemView.findViewById(R.id.textViewContactDetails);
        }

        // Bind for inner contacts (myContact object)
        public void bind(final myContact contact, final OnInnerContactClickListener listener) {
            contactName.setText(contact.getContactName());
            contactDetails.setText(contact.getContactComment());
            itemView.setOnClickListener(v -> listener.onItemClick(contact));
        }

        // Bind for device contacts (String)
        public void bind(final String contactString) {
            // Simple split for "Name: Number" format
            String[] parts = contactString.split(":", 2);
            if (parts.length > 1) {
                contactName.setText(parts[0].trim());
                contactDetails.setText(parts[1].trim());
            } else {
                contactName.setText(contactString);
                contactDetails.setText("");
            }
            itemView.setOnClickListener(null); // Device contacts are not clickable
        }
    }
}
