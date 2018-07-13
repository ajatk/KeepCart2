package com.rs.keepcart.wallet;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.paytm.pgsdk.Log;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import com.rs.keepcart.R;
import com.rs.keepcart.databinding.ActivityPayNowBinding;
import com.rs.keepcart.paytmSet.Checksum;
import com.rs.keepcart.paytmSet.Constants;
import com.rs.keepcart.paytmSet.Paytm;
import com.rs.keepcart.paytmSet.PaytmOnTransactionResponseViewModelClass;
import com.rs.keepcart.paytmSet.TransactionResponseElementsModelClass;
import com.rs.keepcart.retrofit.ApiClient;
import com.rs.keepcart.retrofit.ApiInterface;
import com.rs.keepcart.vendorUserList.UserListRecyclerAdapter;
import com.rs.keepcart.utills.MySharedData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayNowActivity extends AppCompatActivity implements View.OnClickListener, PaytmPaymentTransactionCallback {

    public static final String TAG = UserListRecyclerAdapter.class.getSimpleName();
    private ActivityPayNowBinding viewBinding;
    private ApiInterface apiInterface_checksum;
    String custId, Email, mobileNo;
    int walletBalance_,payAmount, your_payamnt ;
    PaytmOnTransactionResponseViewModelClass viewModelClass;
    private String TXNID, BANKTXNID, ORDERID, TXNAMOUNT,TXNDATE,
            STATUS, GATEWAYNAME, RESPCODE, BANKNAME, MID, PAYMENTMODE, RESPMSG, CHECKSUMHASH, CURRENCY;
    private JSONObject jsonObject;

    private TransactionResponseElementsModelClass elementsModelClass;
    int pay_to_merchant_fd_cash = 0;
    int online_pay_to_merchant_fd_cash = 0;
    int counter = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       viewBinding= DataBindingUtil.setContentView(this,R.layout.activity_pay_now);
       payAmount = Integer.parseInt(MySharedData.getGeneralSaveSession("payAmount"));
      viewBinding.totalBill.setText(payAmount);
        custId = MySharedData.getGeneralSaveSession("userId");
        Email = MySharedData.getGeneralSaveSession("email_dash");
        mobileNo = MySharedData.getGeneralSaveSession("mobile_no_dash");
      viewBinding.payByPaytmBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){

             case R.id.payByPaytm_btn:
                 generateCheckSum();
                 break;
         }
    }

    private void generateCheckSum() {
        final Paytm paytm;
        if(walletBalance_<=0){
            viewBinding.radioBtnWallet.setChecked(false);

        }
      /*  viewBinding.radioBtnWallet.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(payAmount >= walletBalance_)
            {
                your_payamnt = payAmount - walletBalance_;


            }else if(payAmount<= walletBalance_)
            {
                your_payamnt = payAmount - walletBalance_;

            }else if(payAmount<= )
            {

            }
        });*/

        try {
                TextView texttv = viewBinding.totalBill;//
               texttv.setText("1");
                String txnAmount = texttv.getText().toString();
                 walletBalance_ = Integer.parseInt(MySharedData.getGeneralSaveSession("walletBalance"));

                if(viewBinding.radioBtnWallet.isChecked())
                {
                    Toast.makeText(this, " This facility is in Progress", Toast.LENGTH_SHORT).show();
                   /* apiInterface_checksum = ApiClient.getClient().create(ApiInterface.class);
                            paytm = new Paytm(
                            // payAmount,
                            txnAmount,
                            custId,
                            Email,
                            mobileNo
                    );
                }else{
                    apiInterface_checksum = ApiClient.getClient().create(ApiInterface.class);

                            paytm = new Paytm(
                            // payAmount,
                            txnAmount,
                            custId,
                            Email,
                            mobileNo
                    );*/
                }
            // ORDER_ID,TXN_AMOUNT,CUST_ID,EMAIL,MOBILE_NO
            //creating a call object from the apiService

            apiInterface_checksum = ApiClient.getClient().create(ApiInterface.class);

            paytm = new Paytm(
                    // payAmount,
                    txnAmount,
                    custId,
                    Email,
                    mobileNo);

            Call<Checksum> call = apiInterface_checksum.getChecksum(
                    paytm.getORDER_ID(),
                    paytm.getTXN_AMOUNT(),
                    paytm.getCUST_ID(),
                    paytm.getEMAIL(),
                    paytm.getMOBILE_NO()
            );

            //making the call to generate checksum
            call.enqueue(new Callback<Checksum>() {
                @Override
                public void onResponse(Call<Checksum> call, Response<Checksum> response) {

                    //once we get the checksum we will initiailize the payment.
                    //the method is tamsnking the checksum we got and the paytm object as the parameter
                    initializePaytmPayment(response.body().getChecksumHash(), response.body());
                }

                @Override
                public void onFailure(Call<Checksum> call, Throwable t) {

                    System.out.println("what is the fucking url :" + call.toString() + "and the error is :" + t.getMessage());
                }
            });
        } catch (Exception e) {
            System.out.println("what is the fucking url :" + e.getMessage());
        }
    }
    private void initializePaytmPayment(String checksumHash, Checksum paytm) {

        //getting paytm service
        PaytmPGService Service = PaytmPGService.getStagingService();

        //creating a hashmap and adding all the values required
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("MID", Constants.M_ID);
        paramMap.put("ORDER_ID", paytm.getOrderId());
        paramMap.put("CUST_ID", paytm.getCUST_ID());
        paramMap.put("CHANNEL_ID", paytm.getCHANNEL_ID());
        paramMap.put("TXN_AMOUNT", paytm.getTXN_AMOUNT());
        paramMap.put("WEBSITE", paytm.getWEBSITE());
        paramMap.put("CALLBACK_URL", paytm.getCALLBACK_URL());
        paramMap.put("CHECKSUMHASH", checksumHash);
        paramMap.put("MOBILE_NO", paytm.getMOBILE_NO());
        paramMap.put("EMAIL", paytm.getEMAIL());
        paramMap.put("INDUSTRY_TYPE_ID", Constants.INDUSTRY_TYPE_ID);


        //creating a paytm order object using the hashmap
        PaytmOrder order = new PaytmOrder(paramMap);

        //intializing the paytm service
        Service.initialize(order, null);

        //finally starting the payment transaction
        Service.startPaymentTransaction(this, true, true, this);

    }

    //all these overriden method is to detect the payment result accordingly
    @Override
    public void onTransactionResponse(Bundle bundle) {

        TXNID = bundle.getString("TXNID");
        BANKTXNID = bundle.getString("BANKTXNID");
        ORDERID = bundle.getString("ORDERID");
        TXNAMOUNT = bundle.getString("TXNAMOUNT");
        TXNDATE = bundle.getString("TXNDATE");
        STATUS = bundle.getString("STATUS");
        GATEWAYNAME = bundle.getString("GATEWAYNAME");
        RESPCODE = bundle.getString("RESPCODE");
        BANKNAME = bundle.getString("BANKNAME");
        MID = bundle.getString("MID");
        PAYMENTMODE = bundle.getString("PAYMENTMODE");
        RESPMSG = bundle.getString("RESPMSG");
        CHECKSUMHASH = bundle.getString("CHECKSUMHASH");
        CURRENCY = bundle.getString("CURRENCY");

        Log.e(TAG, " response paytm" + bundle.toString());

        // Toast.makeText(context, bundle.toString(), Toast.LENGTH_LONG).show();

//        viewModelClass.loginApiPaytm(TXNID,BANKTXNID,ORDERID,TXNAMOUNT,
//                STATUS,GATEWAYNAME,RESPCODE,BANKNAME,MID,PAYMENTMODE,RESPMSG,CHECKSUMHASH,CURRENCY);
        jsonObject = new JSONObject();
        try {
            /*{
                "ORDERID":275,
                    "ID":11,
                    "TXNID":30,
                    "STATUS":1,
                    "TXNAMOUNT":4566,
                    "BANKTXNID":4556,
                    "TXNDATE":25
            }*/
            jsonObject.put("TXNID", TXNID);
            jsonObject.put("BANKTXNID", BANKTXNID);
            jsonObject.put("ORDERID", ORDERID);
            jsonObject.put("TXNAMOUNT", TXNAMOUNT);
            jsonObject.put("STATUS", STATUS);
            jsonObject.put("TXNDATE", TXNDATE);
//            /*jsonObject.put("GATEWAYNAME", GATEWAYNAME);
//            jsonObject.put("RESPCODE", RESPCODE);
//            jsonObject.put("BANKNAME", BANKNAME);
//            jsonObject.put("MID", MID);
//            jsonObject.put("PAYMENTMODE", PAYMENTMODE);
//            jsonObject.put("RESPMSG", RESPMSG);
//            jsonObject.put("CHECKSUMHASH", CHECKSUMHASH);
//            jsonObject.put("CURRENCY", CURRENCY);*/
            jsonObject.put("ID", custId);

            Log.e(TAG, "jsonObject=== response paytm" + jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        try {
//            jsonArray = new JSONArray();
//            jsonArray.put(jsonObject);
//        } catch (Exception s) {
//            s.printStackTrace();
//        }
/*String ORDERID, String TXNID, String STATUS,
                                                 String TXNAMOUNT, String BANKTXNID, String ID, String TXNDATE*/

        viewModelClass.loginApiPaytm(
                new TransactionResponseElementsModelClass(ORDERID,TXNID, STATUS, TXNAMOUNT, BANKTXNID,custId, TXNDATE));
        bottomASheet();
    }
    // BottomSheetDialogFragment bottomSheetDialogFragment = new BottomFragment();
    //                bottomSheetDialogFragment.show(getActivity().getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");

    @Override
    public void networkNotAvailable() {
        Toast.makeText(this, "Network error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void clientAuthenticationFailed(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void someUIErrorOccurred(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onErrorLoadingWebPage(int i, String s, String s1) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressedCancelTransaction() {
        Toast.makeText(this, "Back Pressed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTransactionCancel(String s, Bundle bundle) {
        Toast.makeText(this, s + bundle.toString(), Toast.LENGTH_LONG).show();
    }
    public void bottomASheet()
    {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dailog_transaction_response);
        dialog.setCancelable(false);

        dialog.setTitle("KeepCart Transaction Status...");

        // set the custom dialog components - text, image and button
        // TextView amount = (TextView) dialog.findViewById(R.id.paidAmount);
        TextView paidFor = (TextView) dialog.findViewById(R.id.Newspaer_magazin);
        TextView amount = (TextView) dialog.findViewById(R.id.get_Amount);
        TextView status = (TextView) dialog.findViewById(R.id.status_here);


        paidFor.setText(ORDERID);
        amount.setText(TXNAMOUNT);
        status.setText(STATUS);
        Button dialogButton = (Button) dialog.findViewById(R.id.okStatus);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
//        BottomSheetDialogFragment bottomSheetDialogFragment = new BottomFragment();
//        bottomSheetDialogFragment.show(context.getApplicationContext()., "Bottom Sheet Dialog Fragment");
    }

}
