package com.github.kongpf8848.animation.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.github.kongpf8848.animation.R;


import java.util.ArrayList;
import java.util.List;

public class BillActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "EditTextActivity";

    private BillingClient billingClient;
    private boolean isConnectionEstablished = false;
    private int maxTries = 3;
    private int tries = 0;

    //购买结果监听
    private PurchasesUpdatedListener purchasesUpdatedListener = new PurchasesUpdatedListener() {
        @Override
        public void onPurchasesUpdated(BillingResult billingResult, List<Purchase> purchases) {
            Log.d(TAG, "onPurchasesUpdated() called with: billingResult = [" + billingResult + "], purchases = [" + purchases + "]");
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                if (purchases != null && purchases.size() > 0) {
                    for (Purchase purchase : purchases) {
                        handlePurchase(purchase);
                    }
                }
            } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {

            }
        }
    };

    private AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener = new AcknowledgePurchaseResponseListener() {
        @Override
        public void onAcknowledgePurchaseResponse(@NonNull BillingResult billingResult) {

        }
    };

    /**
     * 处理购买结果
     * 将购买令牌，订单ID等上传到服务端
     * 确认交易
     */
    private void handlePurchase(Purchase purchase) {
        Log.d(TAG, "handlePurchase(), purchaseToken = [" + purchase.getPurchaseToken() + "]");
        Log.d(TAG, "handlePurchase(), purchaseTime = [" + purchase.getPurchaseTime() + "]");
        Log.d(TAG, "handlePurchase(), packageName = [" + purchase.getPackageName() + "]");
        Log.d(TAG, "handlePurchase(), orderId = [" + purchase.getOrderId() + "]");
        Log.d(TAG, "handlePurchase(), developerPayload = [" + purchase.getDeveloperPayload() + "]");
        Log.d(TAG, "handlePurchase(), signature = [" + purchase.getSignature() + "]");
        Log.d(TAG, "handlePurchase(), purchaseState = [" + purchase.getPurchaseState() + "]");
        Log.d(TAG, "handlePurchase(), quantity = [" + purchase.getQuantity() + "]");
        Log.d(TAG, "handlePurchase(), products = [" + purchase.getProducts() + "]");
        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
            if (!purchase.isAcknowledged()) {
                AcknowledgePurchaseParams acknowledgePurchaseParams =
                        AcknowledgePurchaseParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();
                billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);
            }
        }

    }


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
        billingClient = BillingClient.newBuilder(this)
                .setListener(purchasesUpdatedListener)
                .enablePendingPurchases()
                .build();
    }

    //与Google Play建立连接
    private void onButton1() {

        billingClient.startConnection(new BillingClientStateListener() {

            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                Log.d(TAG, "onBillingSetupFinished() called with: billingResult = [" + billingResult + "]");
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                   isConnectionEstablished=true;
                }else{
                    retryBillingServiceConnection();
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                Log.d(TAG, "onBillingServiceDisconnected() called");
                retryBillingServiceConnection();
            }

        });
    }


    private void retryBillingServiceConnection(){
        Log.d(TAG, "retryBillingServiceConnection() called");
        do {
            try {
                billingClient.startConnection(new BillingClientStateListener(){
                    @Override
                    public void onBillingServiceDisconnected() {
                        Log.d(TAG, "onBillingServiceDisconnected() called");
                        tries++;
                    }

                    @Override
                    public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                        Log.d(TAG, "onBillingSetupFinished() called with: billingResult = [" + billingResult + "]");
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            Log.d(TAG, "Billing connection retry succeeded.");
                            isConnectionEstablished = true;
                        } else {
                            Log.e(TAG, "Billing connection retry failed: ${billingResult.debugMessage}");
                            tries++;
                        }
                    }
                });
            } catch (Exception e) {
                tries++;
            }
        } while (tries <= maxTries && !isConnectionEstablished);
    }

    //查询商品详情
    private void onButton2() {
        boolean isReady=billingClient.isReady();
        if(!isReady){
            Log.d(TAG, "onButton2() called");
            return;
        }
        List<QueryProductDetailsParams.Product> productList = new ArrayList<QueryProductDetailsParams.Product>();
        QueryProductDetailsParams.Product product = QueryProductDetailsParams.Product.newBuilder()
                .setProductId("001")
                .setProductType(BillingClient.ProductType.SUBS)
                .build();
        productList.add(product);
        QueryProductDetailsParams queryProductDetailsParams =
                QueryProductDetailsParams.newBuilder()
                        .setProductList(productList)
                        .build();
        billingClient.queryProductDetailsAsync(queryProductDetailsParams, new ProductDetailsResponseListener() {
            @Override
            public void onProductDetailsResponse(@NonNull BillingResult billingResult, @NonNull List<ProductDetails> list) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    for (ProductDetails p : list) {
                        Log.d(TAG, "onProductDetail,title = [" + p.getTitle() + "]");
                        Log.d(TAG, "onProductDetail,name = [" + p.getName() + "]");
                        Log.d(TAG, "onProductDetail,description = [" + p.getDescription() + "]");
                        Log.d(TAG, "onProductDetail,productId = [" + p.getProductId() + "]");
                        Log.d(TAG, "onProductDetail,productType = [" + p.getProductType() + "]");
                    }
                    onPurchase(list);
                }
            }
        });
    }

    //启动购买流程
    private void onPurchase(List<ProductDetails> list) {
        ProductDetails productDetails = list.get(0);
        List<ProductDetails.SubscriptionOfferDetails> subscriptionOfferDetails = productDetails.getSubscriptionOfferDetails();

        String offerToken = "";
        if (subscriptionOfferDetails != null && subscriptionOfferDetails.size() > 0) {
            offerToken = subscriptionOfferDetails.get(0).getOfferToken();
        }
        List<BillingFlowParams.ProductDetailsParams> productDetailsParamsList = new ArrayList<>();
        BillingFlowParams.ProductDetailsParams.Builder paramsBuilder = BillingFlowParams.ProductDetailsParams.newBuilder().setProductDetails(productDetails);
        if (!TextUtils.isEmpty(offerToken)) {
            paramsBuilder.setOfferToken(offerToken);
        }
        productDetailsParamsList.add(paramsBuilder.build());

        BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                .setProductDetailsParamsList(productDetailsParamsList)
                .setIsOfferPersonalized(true)
                .build();
        BillingResult billingResult = billingClient.launchBillingFlow(this, billingFlowParams);
        if(billingResult!=null){

        }
    }


}