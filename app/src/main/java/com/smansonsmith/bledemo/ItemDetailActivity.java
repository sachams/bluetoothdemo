package com.smansonsmith.bledemo;


import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ItemDetailActivity extends AppCompatActivity {

    private String getBTMajorDeviceClass(int major){
        switch(major){
            case BluetoothClass.Device.Major.AUDIO_VIDEO: 	return "AUDIO_VIDEO";
            case BluetoothClass.Device.Major.COMPUTER: 		return "COMPUTER";
            case BluetoothClass.Device.Major.HEALTH:			return "HEALTH";
            case BluetoothClass.Device.Major.IMAGING:			return "IMAGING";
            case BluetoothClass.Device.Major.MISC:			return "MISC";
            case BluetoothClass.Device.Major.NETWORKING:		return "NETWORKING";
            case BluetoothClass.Device.Major.PERIPHERAL:		return "PERIPHERAL";
            case BluetoothClass.Device.Major.PHONE:			return "PHONE";
            case BluetoothClass.Device.Major.TOY:				return "TOY";
            case BluetoothClass.Device.Major.UNCATEGORIZED:	return "UNCATEGORIZED";
            case BluetoothClass.Device.Major.WEARABLE:		return "AUDIO_VIDEO";
            default: 							return "unknown!";
        }
    }

    private String getTypeString(int type) {
        switch(type) {
            case BluetoothDevice.DEVICE_TYPE_CLASSIC:
                return "Classic";

            case BluetoothDevice.DEVICE_TYPE_LE:
                return "BLE";

            case BluetoothDevice.DEVICE_TYPE_DUAL:
                return "Dual";

            default:
                return "Unknown";
        }
    }

    private String getBondStateString(int bondState) {
        switch(bondState) {
            case BluetoothDevice.BOND_NONE:
                return "None";

            case BluetoothDevice.BOND_BONDING:
                return "Bonding";

            case BluetoothDevice.BOND_BONDED:
                return "Bonded";

            default:
                return "Unknown";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        BluetoothDevice device = (BluetoothDevice)getIntent().getExtras().getParcelable("device");

        setupToolBar(device.getName());

        TextView tvTitle = (TextView)findViewById(R.id.tvTitle);
        String content =
                "Address: " + device.getAddress() + "\n" +
                "Class: " + getBTMajorDeviceClass(device.getBluetoothClass().getMajorDeviceClass()) + "\n" +
                        "Type: " + getTypeString(device.getType()) + "\n" +
                        "Bond state: " + getBondStateString(device.getBondState()) + "\n";

        tvTitle.setText(content);
    }

    private void setupToolBar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar == null) return;

        if(title == null) {
            toolbar.setTitle("Unnamed device");
        } else {
            toolbar.setTitle(title);
        }

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish(); // close this activity as oppose to navigating up
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_detail, menu);
        return true;
    }

}