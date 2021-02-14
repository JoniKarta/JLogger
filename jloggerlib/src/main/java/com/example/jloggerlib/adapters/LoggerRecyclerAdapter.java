package com.example.jloggerlib.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jloggerlib.R;
import com.example.jloggerlib.model.Logger;

public class LoggerRecyclerAdapter extends ListAdapter<Logger, LoggerRecyclerAdapter.LoggerViewHolder> {

    public LoggerRecyclerAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<Logger> DIFF_CALLBACK = new DiffUtil.ItemCallback<Logger>() {
        @Override
        public boolean areItemsTheSame(@NonNull Logger oldItem, @NonNull Logger newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Logger oldItem, @NonNull Logger newItem) {
            return oldItem.getDate().equals(newItem.getDate());
        }

    };

    @NonNull
    @Override
    public LoggerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.logger_item, parent, false);
        return new LoggerViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull LoggerViewHolder holder, int position) {
        holder.dateTextView.setText(String.valueOf(getItem(position).getDate()));
        holder.tagTextView.setText(getItem(position).getTag());
        holder.msgTextView.setText(String.valueOf(getItem(position).getMsg()));
        int imageResource;
        switch (getItem(position).getTag()) {
            case Logger.WARNING:
                imageResource = R.drawable.warning;
                break;
            case Logger.ERROR:
                imageResource = R.drawable.error;
                break;
            case Logger.INFO:
                imageResource = R.drawable.info;
                break;
            case Logger.TRACE:
                imageResource = R.drawable.trace;
                break;
            case Logger.DEBUG:
                imageResource = R.drawable.debug;
                break;
            default:
                imageResource = R.drawable.other;
        }
        holder.logImageView.setImageResource(imageResource);
    }

    public Logger getLoggerPositionAt(int position) {
        return getItem(position);
    }

    public static class LoggerViewHolder extends RecyclerView.ViewHolder {
        private final TextView dateTextView;
        private final TextView tagTextView;
        private final TextView msgTextView;
        private final ImageView logImageView;

        public LoggerViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.logger_item_date_text_view);
            tagTextView = itemView.findViewById(R.id.logger_item_tag_text_view);
            msgTextView = itemView.findViewById(R.id.logger_item_msg_text_view);
            logImageView = itemView.findViewById(R.id.logger_item_log_image);
        }
    }
}
