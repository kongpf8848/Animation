package com.github.kongpf8848.animation.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.ProductDetailsResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesResponseListener;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.android.billingclient.api.QueryPurchasesParams;
import com.github.kongpf8848.animation.BillingClientWrapper;
import com.github.kongpf8848.animation.R;
import com.kongpf.commonhelper.ToastHelper;


import java.util.ArrayList;
import java.util.List;

public class BillActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "BillActivity";

    private BillingClientWrapper billingClientWrapper;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        initBillingClient();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                onButton1();
                break;
            case R.id.button2:
                onButton2();
                break;
        }
    }

    //初始化BillingClient
    private void initBillingClient() {
        billingClientWrapper=new BillingClientWrapper(this, new BillingClientWrapper.SubscriptionCallback() {
            @Override
            public void onSuccess(String productId,String purchaseToken,String orderId) {
                Log.d(TAG, "onSuccess() called with: productId = [" + productId + "], purchaseToken = [" + purchaseToken + "], orderId = [" + orderId + "]");
            }

            @Override
            public void onFail(String message) {
                Log.d(TAG, "onFail() called with: message = [" + message + "]");
                Toast.makeText(BillActivity.this, "onFail:"+message, Toast.LENGTH_LONG).show();
            }
        });
    }

    //与Google Play建立连接
    private void onButton1() {
        billingClientWrapper.connect();

    }

    private void onButton2() {

    }



}