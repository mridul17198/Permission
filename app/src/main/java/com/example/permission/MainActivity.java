package com.example.permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int LOCATION_PERMISSION_CODE = 1;
    private int SMS_PERMISSION_CODE = 1;
    Button loc, sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loc = (Button) findViewById(R.id.location);
        sm = (Button) findViewById(R.id.sms);
        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permission_method();
            }
        });

        sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permission_method();
            }
        });
    }

    public void permission_method() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, "You have already granted this permission!",
                    Toast.LENGTH_SHORT).show();
        }
        /*else if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, "You have already granted this permission!",
                    Toast.LENGTH_SHORT).show();
        }*/
        else {
            requestLocationPermission();
        }
    }

    private void requestLocationPermission() {
        Toast.makeText(this, "Requesting Permission", Toast.LENGTH_SHORT).show();
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        }
        /*else if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.SEND_SMS)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[] {Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        }*/
        else {
            Toast.makeText(this, "PopUp", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION},SMS_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }

        /*if(requestCode==SMS_PERMISSION_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }*/
    }
}
