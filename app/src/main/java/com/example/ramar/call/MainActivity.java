package com.example.ramar.call;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   EditText edit;
    Global g;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         g= (Global) getApplicationContext();
        //Toast.makeText(getApplicationContext(),)
        Switch s = (Switch) findViewById(R.id.switch1);
        edit=(EditText)findViewById(R.id.editText);

        if(savedInstanceState!=null)

        {
            edit.setText(savedInstanceState.getString("text"));

        }


        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            String text;


            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position

                if (isChecked) {
                    PackageManager pm = MainActivity.this.getPackageManager();
                    ComponentName componentName = new ComponentName(MainActivity.this, PhoneStateReceiver.class);
                    pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                            PackageManager.DONT_KILL_APP);
                    Toast.makeText(getApplicationContext(), "Activated", Toast.LENGTH_LONG).show();
                    g.setMessage(edit.getText().toString());

                } else {
                    PackageManager pm = MainActivity.this.getPackageManager();
                    ComponentName componentName = new ComponentName(MainActivity.this, PhoneStateReceiver.class);
                    pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                            PackageManager.DONT_KILL_APP);
                    Toast.makeText(getApplicationContext(), "cancelled", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    public void onSavedInstanceState(Bundle o)
    {
        o.putString("text", edit.getText().toString());
    }
  public void ok(View v)
    {
        //Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_LONG).show();
        g.setMessage(edit.getText().toString());
        edit.setEnabled(false);

    }
   public void edit1(View v)
    {
       // Toast.makeText(getApplicationContext(),"edit",Toast.LENGTH_SHORT).show();
        edit.setEnabled(true);
    }

}
