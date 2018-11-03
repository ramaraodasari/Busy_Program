package com.example.ramar.call;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.provider.Telephony;
import android.telecom.TelecomManager;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.telephony.TelephonyScanManager;
import android.widget.EditText;
import android.widget.Toast;
public class PhoneStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try
        {
            System.out.println("Receiver start");
            SmsManager sms=SmsManager.getDefault();
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            AudioManager am=(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
            am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            String str=intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            String mess;
            Global g=(Global)context.getApplicationContext();
            mess = g.getMessage();

            System.out.println(mess);
            //Toast.makeText(context,mess,Toast.LENGTH_LONG).show();
            if(str.equals("RINGING"))
            {
                //Toast.makeText(context,str.equals("RINGING")+"",Toast.LENGTH_LONG).show();

                sms.sendTextMessage(incomingNumber,null,mess,null,null);
            }
            else if(str.equals("IDLE"))
            {
                am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
