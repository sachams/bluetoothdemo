package com.smansonsmith.bledemo;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DeviceListAdapter extends ArrayAdapter<BluetoothDevice> {
    private final Context context;

    public DeviceListAdapter(Context context, ArrayList<BluetoothDevice> devices) {
        super(context, 0, devices);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.device_row, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.deviceTextView);

        BluetoothDevice device = getItem(position);
        if(device.getName() != null) {
            textView.setText(device.getName());
        } else {
            textView.setText("Unnamed device");
        }
        return rowView;
    }
}
