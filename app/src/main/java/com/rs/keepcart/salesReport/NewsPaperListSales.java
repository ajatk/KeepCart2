package com.rs.keepcart.salesReport;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rs.keepcart.R;
import com.rs.keepcart.databinding.FragmentNewsPaperListSalesBinding;
import com.rs.keepcart.fragments.MonthHistory;
import com.rs.keepcart.salesReport.newspaperSales.PendingAmount;
import com.rs.keepcart.salesReport.newspaperSales.Sale;

import java.util.List;

public class NewsPaperListSales extends Fragment {

    private FragmentNewsPaperListSalesBinding viewbinding;
    private SalesReportAdapter salesReportAdapter;
    private Context context;
    private List<Sale> saleList ;
    private List<PendingAmount> pendingAmountList ;
    public List<Sale> getSaleList() {
        return saleList;
    }

    public List<PendingAmount> getPendingAmountList() {
        return pendingAmountList;
    }

    public void setSaleList(List<Sale> saleList) {
        this.saleList = saleList;

    }

    public void setPendingAmountList(List<PendingAmount> pendingAmountList) {
        this.pendingAmountList = pendingAmountList;

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_paper_list_sales, container, false);
        viewbinding = DataBindingUtil.bind(view);
        context = getActivity();
        viewbinding.salesReportTabLayout1.setupWithViewPager(viewbinding.salesReportViewPager1);
        salesReportAdapter = new SalesReportAdapter(context, getChildFragmentManager());
        viewbinding.salesReportViewPager1.setAdapter(salesReportAdapter);
        getlistMethod();
        return view;
    }

    public void getlistMethod()
    {
        try {
            if(saleList!=null && pendingAmountList!=null){
                ((MonthHistory)salesReportAdapter.getItem(0)).setSaleMonthList(saleList);
                ((MonthHistory)salesReportAdapter.getItem(0)).setPendingAmountMonthList(pendingAmountList);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public class SalesReportAdapter extends FragmentStatePagerAdapter {
        String a[] = {"This Month", "lastMonth","lastMonth" };
        private Context context;


         SalesReportAdapter(Context context, FragmentManager fm) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            return new MonthHistory();
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            if (position == 0) {
                title = a[0];
            } else
                {
                title = a[1];
            }

            return title;
        }
    }
    public void backButton(View view)
    {
       getActivity().finish();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
