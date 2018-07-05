package com.rs.keepcart.salesReport;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.rs.keepcart.R;
import com.rs.keepcart.databinding.FragmentSalesReportBinding;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.salesReport.newspaperSales.NewspaperSalesModelClass;
import com.rs.keepcart.salesReport.newspaperSales.PendingAmount;
import com.rs.keepcart.salesReport.newspaperSales.Sale;
import com.rs.keepcart.utills.ApplicationConstants;
import com.rs.keepcart.utills.MySharedData;

import java.util.List;

/**
 * Created by sam on 5/23/2018.
 */

public class SalesReportActivity extends AppCompatActivity {

    private FragmentSalesReportBinding viewbinding;
    private Context context;
    private Boolean pas;
    private Toolbar toolbar_;
    private TextView title;
    private NewsSalesReportViewModelClass viewModel;
    private List<Sale>salesReportsList;
    private List<PendingAmount>pendingAmountList;
    private SalesReportViewpagerAdapter viewpagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewbinding = DataBindingUtil.setContentView(this, R.layout.fragment_sales_report);
        pas = getIntent().getExtras().getBoolean("pass");
        toolbar_ = findViewById(R.id.toolbar123);
        title = findViewById(R.id.page_title);
        title.setText(R.string.salesRreport_toolbar_title);
        viewmodelinitialize();
        String id_ = MySharedData.getGeneralSaveSession("userId");
        viewModel.loginApiSales(id_);

        viewbinding.salesReportTabLayout.setupWithViewPager(viewbinding.salesReportViewPager);
        viewpagerAdapter = new SalesReportViewpagerAdapter(context, getSupportFragmentManager());
        viewbinding.salesReportViewPager.setAdapter(viewpagerAdapter);
    }

public void viewmodelinitialize()
{
    viewModel = ViewModelProviders.of(this).get(NewsSalesReportViewModelClass.class);
//        viewModel.inject();
    viewModel.loginLiveData.observe(this,this::salesReportResponse);
}
public void salesReportResponse(Resource<NewspaperSalesModelClass> resource)
{    viewbinding.progressBarLog.setVisibility(View.GONE);
    switch (resource.status) {
        case ApplicationConstants.LOADING:
            viewbinding.progressBarLog.setVisibility(View.VISIBLE);
            break;
        case ApplicationConstants.HTTP_SUCCESS:
            int status = resource.data.getStatus();
            int i =0;
            switch (status) {
                // NewsPaperModelClass newsPaperModelClass = response.body();
                case 200:
                    try
                    {
                        if(salesReportsList!=null && pendingAmountList!=null)
                        {
                            salesReportsList = resource.data.getSales();
                            pendingAmountList = resource.data.getPendingAmount();

                            ((NewsPaperListSales)viewpagerAdapter.getItem(0)).setSaleList(salesReportsList);
                            ((NewsPaperListSales)viewpagerAdapter.getItem(0)).setPendingAmountList(pendingAmountList);
                          //  ((MagazinesListSales)viewpagerAdapter.getItem(0));

                        }
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                    break;
            }
            //Toast.makeText(context, resource.data.get, Toast.LENGTH_SHORT).show();
            break;
        case ApplicationConstants.SHOW_ONLY_MSG:
            Snackbar.make(viewbinding.getRoot(), resource.message, Snackbar.LENGTH_SHORT).show();
            break;
    }
}
    public class SalesReportViewpagerAdapter extends FragmentStatePagerAdapter {
        String a[] = {"NewsPaper", "Magazine"};
        private Context context;

        public SalesReportViewpagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            this.context = context;
        }


        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new NewsPaperListSales();
            }   else {
                return new MagazinesListSales();
            }

        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            if (position == 0) {
                title = a[0];

            } else{
                title = a[1];
            }
            return title;

        }
    }
    public void backButton(View view)
    {
        if (getFragmentManager().getBackStackEntryCount()>0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }
}
