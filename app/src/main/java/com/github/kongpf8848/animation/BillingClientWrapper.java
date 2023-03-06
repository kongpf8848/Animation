package com.github.kongpf8848.animation;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.android.billingclient.api.QueryPurchasesParams;


import java.util.ArrayList;
import java.util.List;

public class BillingClientWrapper implements PurchasesUpdatedListener {

    private static final String TAG = "BillingClientWrapper";
    private static final String PRODUCT_ID = "001";
    private Activity activity;
    private BillingClient billingClient;
    private boolean isConnectionEstablished;
    private int tries;
    private SubscriptionCallback callback;


    public interface SubscriptionCallback {
        void onSuccess(String productId, String purchaseToken, String orderId);

        void onFail(String message);
    }

    //初始化BillingClient
    public BillingClientWrapper(Activity activity, SubscriptionCallback callback) {
        this.activity = activity;
        this.callback = callback;

        billingClient = BillingClient.newBuilder(activity)
                .setListener(this)
                .enablePendingPurchases()
                .build();
    }

    //连接到 Google Play
    public void connect() {
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                Log.d(TAG, "onBillingSetupFinished() called with: billingResult = [" + billingResult + "]");
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    queryProductDetails();
                } else {
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

    @Override
    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {
        Log.d(TAG, "onPurchasesUpdated() called with: billingResult = [" + billingResult + "], list = [" + list + "]");
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK
                && list != null) {
            for (Purchase purchase : list) {
                handlePurchase(purchase);
            }
        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
        }
    }

    //重试连接
    private void retryBillingServiceConnection() {
        Log.d(TAG, "retryBillingServiceConnection() called");
        isConnectionEstablished = false;
        tries = 0;
        do {
            try {
                billingClient.startConnection(new BillingClientStateListener() {

                    @Override
                    public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                        Log.d(TAG, "onBillingSetupFinished22() called with: billingResult = [" + billingResult + "]");
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            Log.d(TAG, "Billing connection retry succeeded.");
                            isConnectionEstablished = true;
                        } else {
                            Log.e(TAG, "Billing connection retry failed: " + billingResult);
                            tries++;
                        }
                    }

                    @Override
                    public void onBillingServiceDisconnected() {
                        Log.d(TAG, "onBillingServiceDisconnected22() called");
                        tries++;
                    }

                });
            } catch (Exception e) {
                tries++;
            }
        } while (tries < 3 && !isConnectionEstablished);
        if(!isConnectionEstablished){
            callback.onFail("Connect Google Play Failed");
        }

    }

    //查询商品详情
    private void queryProductDetails() {
        boolean isReady = billingClient.isReady();
        if (!isReady) {
            Log.e(TAG, "billingClient not ready");
            callback.onFail("billingClient not ready");
            return;
        }
        BillingResult br=billingClient.isFeatureSupported(BillingClient.FeatureType.PRODUCT_DETAILS);
        if(br.getResponseCode()!= BillingClient.BillingResponseCode.OK){
            Log.e(TAG, "billingClient not support PRODUCT_DETAILS");
            return;
        }

        List<QueryProductDetailsParams.Product> productList = new ArrayList<>();
        QueryProductDetailsParams.Product product = QueryProductDetailsParams.Product.newBuilder()
                .setProductId(PRODUCT_ID)
                .setProductType(BillingClient.ProductType.SUBS)
                .build();
        productList.add(product);
        QueryProductDetailsParams queryProductDetailsParams =
                QueryProductDetailsParams.newBuilder()
                        .setProductList(productList)
                        .build();
        billingClient.queryProductDetailsAsync(queryProductDetailsParams, (billingResult, list) -> {
            Log.d(TAG, "queryProductDetailsAsync() called with: billingResult = [" + billingResult + "], list = [" + list + "]");
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                for (ProductDetails p : list) {
                    Log.d(TAG, "onProductDetail,title = [" + p.getTitle() + "]");
                    Log.d(TAG, "onProductDetail,name = [" + p.getName() + "]");
                    Log.d(TAG, "onProductDetail,description = [" + p.getDescription() + "]");
                    Log.d(TAG, "onProductDetail,productId = [" + p.getProductId() + "]");
                    Log.d(TAG, "onProductDetail,productType = [" + p.getProductType() + "]");
                }
                queryPurchases(list);
            } else {
                Log.e(TAG, "queryProductDetailsAsync,billingResult = [" + billingResult + "]");
                callback.onFail("queryProductDetailsAsync,billingResult:" + billingResult);
            }
        });
    }

    //查询购买信息
    private void queryPurchases(List<ProductDetails> productDetailsList) {
        Log.d(TAG, "queryPurchases() called with: productDetailsList = [" + productDetailsList + "]");
        QueryPurchasesParams purchasesParams = QueryPurchasesParams.newBuilder()
                .setProductType(BillingClient.ProductType.SUBS)
                .build();
        billingClient.queryPurchasesAsync(purchasesParams, (billingResult, list) -> {
            Log.d(TAG, "onQueryPurchasesResponse() called with: billingResult = [" + billingResult + "], list = [" + list + "]");
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                if (list.size() > 0) {
                    for (Purchase p : list) {
                        Log.d(TAG, "onQueryPurchasesResponse() called with: p = [" + p + "]");
                        handlePurchase(p);
                    }
                } else {
                    lanuchPurchaseFlow(productDetailsList);
                }
            } else {
                lanuchPurchaseFlow(productDetailsList);
            }
        });
    }

    //启动购买流程
    private void lanuchPurchaseFlow(List<ProductDetails> productDetailsList) {
        Log.d(TAG, "lanuchPurchaseFlow() called with: productDetailsList = [" + productDetailsList + "]");
        ProductDetails productDetails = getProductDetails(productDetailsList);
        if (productDetails == null) {
            Log.d(TAG, "lanuchPurchaseFlow(),productDetails==null");
            callback.onFail("lanuchPurchaseFlow(),productDetails==null");
            return;
        }
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
        BillingResult billingResult = billingClient.launchBillingFlow(activity, billingFlowParams);
        Log.d(TAG, "lanuchPurchaseFlow() called with: billingResult = [" + billingResult + "]");
    }

    //处理购买交易
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
        for (String s : purchase.getProducts()) {
            Log.d(TAG, "handlePurchase() called with: Products = [" + s + "]");
        }
        if (purchase.getProducts().contains(PRODUCT_ID)) {
            Log.d(TAG, "handlePurchase() called upload this purchase = [" + purchase + "]");
            acknowledgePurchase(purchase);
            callback.onSuccess(PRODUCT_ID, purchase.getPurchaseToken(), purchase.getOrderId());
        }
    }

    private ProductDetails getProductDetails(List<ProductDetails> productDetailsList) {
        if (productDetailsList != null) {
            for (ProductDetails p : productDetailsList) {
                if (p.getProductId().equalsIgnoreCase(PRODUCT_ID)) {
                    return p;
                }
            }
        }
        return null;
    }

    private void acknowledgePurchase(Purchase purchase) {
        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
            if (!purchase.isAcknowledged()) {
                AcknowledgePurchaseParams acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                .setPurchaseToken(purchase.getPurchaseToken()).build();
                billingClient.acknowledgePurchase(acknowledgePurchaseParams, new AcknowledgePurchaseResponseListener() {
                    @Override
                    public void onAcknowledgePurchaseResponse(@NonNull BillingResult billingResult) {
                        Log.d(TAG, "onAcknowledgePurchaseResponse() called with: billingResult = [" + billingResult + "]");
                    }
                });
            }
        }
    }
}
