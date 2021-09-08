package com.jsstech.razorpayintegration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {
ImageView imageView;
Button btnPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.img);
        btnPay=findViewById(R.id.payNow);

        String samount="100";
        int amount=Math.round(Float.parseFloat(samount)*100);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checkout checkout=new Checkout();
                checkout.setKeyID("enter razor pay secreat id");
                checkout.setImage(R.drawable.rozar);

                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("name","sanj tech");

                    jsonObject.put("des","test Payment");

                    jsonObject.put("theme.color","#7C4DFF");
                    jsonObject.put("curency","INR");
                    jsonObject.put("amount",amount);

                    jsonObject.put("Mobile number","8887776660");
                    jsonObject.put("prefill.email","sanjtech@gmail.com");
                    checkout.open(MainActivity.this,jsonObject);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("payment id");
        builder.setMessage(s);
        builder.show();
    }

    @Override
    public void onPaymentError(int i,String s) {

        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
}






