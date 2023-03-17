package ru.iu3.fclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.DecoderException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.binary.Hex;

import ru.iu3.fclient.databinding.ActivityMainBinding;





public class MainActivity extends AppCompatActivity {
    // Used to load the 'fclient' library on application startup.
    static {
        System.loadLibrary("fclient");
        System.loadLibrary("mbedcrypto");
    }


    //public void onButtonClick(View v)
    // {
    //    Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
    //}
    //public void onButtonClick(View v)
    //{
    //    byte[] key = stringToHex("0123456789ABCDEF0123456789ABCDE0");
    //    byte[] enc = encrypt(key, stringToHex("000000000000000102"));
    //    byte[] dec = decrypt(key, enc);
    //    String s = new String(Hex.encodeHex(dec)).toUpperCase();
    //    Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    //}


    public void onButtonClick(View v)
    {
        Intent it = new Intent(this, PinpadActivity.class);
        startActivity(it);
    }


    public static byte[] stringToHex(String s)
    {
        byte[] hex;
        try
        {
            hex = Hex.decodeHex(s.toCharArray());
        }
        catch (DecoderException ex)
        {
            hex = null;
        }
        return hex;
    }


    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int res=initRng();
        byte[] v = randomBytes(10);
        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        // Example of a call to a native method
        //TextView tv = binding.sampleText;
        //TextView tv = findViewById(R.id.sample_text);

        //tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'fclient' native library,
     * which is packaged with this application.
     */
    public static native byte[] encrypt(byte[] key, byte[] data);
    public static native byte[] decrypt(byte[] key, byte[] data);
    public native String stringFromJNI();
    public static native int initRng();
    public static native byte[] randomBytes(int no);
}


