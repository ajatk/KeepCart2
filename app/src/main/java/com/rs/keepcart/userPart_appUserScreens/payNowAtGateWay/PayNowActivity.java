package com.rs.keepcart.userPart_appUserScreens.payNowAtGateWay;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rs.keepcart.R;
import com.rs.keepcart.databinding.ActivityPayNow2Binding;
import com.rs.keepcart.databinding.ActivityPayNowBinding;

public class PayNowActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityPayNow2Binding viewBinding;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(this,R.layout.activity_pay_now2);

        viewBinding.coupons.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.coupons:
                if(fragment==null)
                {
                    fragment = new PayBillsWithCouponsFragment();
                    loadFragment(fragment);

                } else
                {
                    loadFragment(fragment);
                }

                break;

        }
    }
    public void loadFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.commit();
    }
}
