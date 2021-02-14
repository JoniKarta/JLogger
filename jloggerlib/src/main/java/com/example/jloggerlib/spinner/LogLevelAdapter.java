package com.example.jloggerlib.spinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.jloggerlib.R;

import java.util.List;

public class LogLevelAdapter extends ArrayAdapter<LogLevelItem> {


    public LogLevelAdapter(@NonNull Context context, @NonNull List<LogLevelItem> levelItemArrayList) {
        super(context, 0, levelItemArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return viewInflater(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return viewInflater(position, convertView, parent);
    }

    private View viewInflater(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.log_level_spinner, parent, false);
        }
        LogLevelItem levelItem = getItem(position);
        TextView textView = convertView.findViewById(R.id.log_level_spinner_level_text_view);
        ImageView imageView = convertView.findViewById(R.id.log_level_spinner_image_view);
        if (levelItem != null) {
            textView.setText(levelItem.getTag());
            imageView.setImageResource(levelItem.getImageResource());
        }
        return convertView;
    }
}
