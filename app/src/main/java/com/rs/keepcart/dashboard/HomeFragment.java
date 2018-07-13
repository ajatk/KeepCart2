package com.rs.keepcart.dashboard;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.paytm.pgsdk.Log;
import com.rs.keepcart.R;
import com.rs.keepcart.adapter.DashBoardPagerAdapter;
import com.rs.keepcart.adapter.HomeViewPagerAdapter;
import com.rs.keepcart.afterLogin.SelectedInfoModelClass;
import com.rs.keepcart.comingSoon.ComingSoonFragment;
import com.rs.keepcart.comingSoon.ComingSoonServiceFragment;
import com.rs.keepcart.dashboard.dashBoardModel.Banner;
import com.rs.keepcart.dashboard.dashBoardModel.Category;
import com.rs.keepcart.dashboard.dashBoardModel.DashBoardModelClass;
import com.rs.keepcart.dashboard.dashBoardModel.VendorDetail;
import com.rs.keepcart.dashboard.dashBoardModel.VendorPreviou;
import com.rs.keepcart.databinding.FragmentHomeBinding;
import com.rs.keepcart.editProfile.EditProfile;
import com.rs.keepcart.editProfile.profileModel.GetDataProfileModelClass;
import com.rs.keepcart.retrofit.ApiClient;
import com.rs.keepcart.retrofit.ApiInterface;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.userPart_appUserScreens.userDashBoard.UserHomeViewPagerAdapter;
import com.rs.keepcart.userPart_appUserScreens.userDashBoard.UserNewsPaperFragment;
import com.rs.keepcart.utills.ApplicationConstants;
import com.rs.keepcart.utills.MySharedData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;


public class HomeFragment extends Fragment implements
        BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    public static final String TAG = HomeFragment.class.getSimpleName();
    public static final String Base_URL = "http://www.uicreations.com/keepkart/assets/images/";
    String imagurl;
    int[] IMAGES;
    int currentPage = 0;
    int NUM_PAGES = 0;
    int dotscount = 0;
    ImageView[] dots;
    Timer swipeTimer;
    Runnable Update;
    private int currentPostion = 0;
    private TextView titleSet;
    private HomeViewPagerAdapter viewPagerTabAdapter;
    private UserHomeViewPagerAdapter viewPagerTabAdapter_u;
    private FragmentHomeBinding viewBinding;
    private DashBoardPagerAdapter dashAdapter;
    private Context context;
    private DashboardViewModelClass viewModelClass;
    private List<Banner> banner_List;
    private List<Category> cat_List;
    private List<VendorDetail> vendor_List;
    private List<VendorPreviou> vendor_priviousBillList;
    private SelectedInfoModelClass infoClass;
    private ArrayList<Fragment> fragmentList;
    private ArrayList<String> strImageBaner;
    private HomeAtUserDetailViewModel homeViewModelClass;
    private HashMap<String, Banner> Hash_file_maps;
    private SliderLayout sliderLayout;
    private ProgressDialog pDialog;
    private View view;
    private HashMap<String, String> hashMap = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewBinding = DataBindingUtil.bind(view);
        viewModelClass = ViewModelProviders.of(this).get(DashboardViewModelClass.class);
        viewModelClass.loginLiveData.observe(this, this::loginResponse);
        titleSet = ((HomeActivity) getActivity()).findViewById(R.id.page_title);
        titleSet.setText("KeepCart");
        Hash_file_maps = new HashMap<String, Banner>();
        String id_ = MySharedData.getGeneralSaveSession("userId");
        //String id_ = "20";
        //SliderRequest();

        sliderLayout = view.findViewById(R.id.slider);
        strImageBaner = new ArrayList<String>();
        homeViewModelClass = ViewModelProviders.of(this).get(HomeAtUserDetailViewModel.class);
        homeViewModelClass.loginLiveData.observe(this, this::signUpResponse);
        homeViewModelClass.homeUserDetailApiDash(id_);
        infoClass = new SelectedInfoModelClass(id_);
        viewModelClass.loginApiDash(id_);
        context = getActivity();
        //swipeTimer = new Timer();
        homeInternalFrag();
        banner_List = new ArrayList<>();
        cat_List = new ArrayList<>();
        vendor_List = new ArrayList<>();

        inittabs();
        return view;
    }

    private void inittabs() {
        fragmentList = new ArrayList<>();
    }

    private void loginResponse(Resource<DashBoardModelClass> resource) {
        int i = 0;
        viewBinding.progressBarLog.setVisibility(View.GONE);
        switch (resource.status) {
            case ApplicationConstants.LOADING:
                viewBinding.progressBarLog.setVisibility(View.VISIBLE);
                break;
            case ApplicationConstants.HTTP_SUCCESS:
                int status = resource.data.getStatus();

                switch (status) {
                    case 200:
                        banner_List = resource.data.getBanners();
                        cat_List = resource.data.getCategory();
                        vendor_List = resource.data.getVendorDetails();
                        vendor_priviousBillList = resource.data.getVendorPrevious();
                        if (banner_List != null) {

                            for (i = 0; i < banner_List.size(); i++) {
                                strImageBaner.add(Base_URL + banner_List.get(i).getImages());
                            }
                            SliderRequest();
                        }
                        if (vendor_List != null) {
                            ((NewsPaper) viewPagerTabAdapter.getItem(0)).setVendorList(vendor_List);
//                            ((UserNewsPaperFragment) viewPagerTabAdapter.getItem(0)).setVendorList(vendor_List);

                        }
                        if (vendor_priviousBillList != null) {
                            ((NewsPaper) viewPagerTabAdapter.getItem(0)).setVendorPrevious(vendor_priviousBillList);
                       //     ((UserNewsPaperFragment) viewPagerTabAdapter.getItem(0)).setVendorPrevious(vendor_priviousBillList);
                        }
                        hidepDialog();
                        break;
                }
                //Toast.makeText(context, resource.data.get, Toast.LENGTH_SHORT).show();
                break;
            case ApplicationConstants.SHOW_ONLY_MSG:
                Snackbar.make(viewBinding.getRoot(), resource.message, Snackbar.LENGTH_SHORT).show();
                break;
        }

    }

    private void SliderRequest() {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        showpDialog();
        try {
            inItPager();
            sliderLayout.setDuration(3000);
            sliderLayout.addOnPageChangeListener(HomeFragment.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inItPager() {
        for (String banner : strImageBaner) {
            hashMap.put(banner, banner);
        }
        try {
            for (String name : hashMap.keySet()) {
                TextSliderView textSliderView = new TextSliderView(context);
                textSliderView.image(hashMap.get(name));
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle().putString("extra", name);
                sliderLayout.addSlider(textSliderView);
            }
            hidepDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

//    public void inItPager() {
//        int i;
//        //IMAGES = new int[]{R.drawable.banner, R.drawable.banner, R.drawable.banner, R.drawable.banner, R.drawable.banner};
//
//
//        /*try {
//             dashAdapter = new DashBoardPagerAdapter(context, banner_List);
//             viewBinding.viewpagerfragemnt.setAdapter(dashAdapter);
//             dotscount = banner_List.size();
//            dots = new ImageView[dotscount];
//            for (i = 0; i < dotscount; i++) {
//                dots[i] = new ImageView(getActivity());
//                dots[i].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.non_active_dot));
//                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                params.setMargins(8, 0, 8, 0);
//                viewBinding.sliderdots.addView(dots[i], params);
//            }
//            dots[0].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.active_dot));
//            viewBinding.viewpagerfragemnt.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                }
//                @Override
//                public void onPageSelected(int position) {
//                    int i;
//                    try {
//
//                        for (i = 0; i < dotscount; i++) {
//                            dots[i].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.non_active_dot));
//                        }
//                        dots[position % dotscount].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.active_dot));
//                    }catch (Exception e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int state) {
//
//                }
//            });
//            try {
//                swipeTimer.scheduleAtFixedRate(new TimerTask() {
//                    @Override
//                    public void run() {
//                        try{
//                            if (getActivity() == null)
//                                return;
//                            getActivity().runOnUiThread(() -> {
//                                if (currentPage == banner_List.size()) {
//                                    currentPage = 0;
//                                }
//                                        currentPostion++;
//                                        viewBinding.viewpagerfragemnt.setCurrentItem(currentPostion,true);
//                                    }
//                            );
//                        }catch (Exception e)
//                        {
//
//                        }
//                    }
//                },3000,2000);
//            } catch (Exception e) {
//                Log.e(e.getMessage(), "exception timmer");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }*/
//    }

    @Override
    public void onResume() {
        super.onResume();
       /* try {
           swipeTimer = new Timer();
            swipeTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    try{
                        if (getActivity() == null)
                            return;
                        getActivity().runOnUiThread(() -> {
                                    currentPostion++;
                                    viewBinding.viewpagerfragemnt.setCurrentItem(currentPostion, true);
                                }
                        );
                    }catch (Exception e)
                    {

                    }
                }
            },3000,2000);
           // swipeTimer.schedule(new MyTimerTask(),3000);
            //inItPager();
        } catch (Exception e) {
            Log.e(e.getMessage(), "exception timmer");
        }*/
    }

    @Override
    public void onStop() {
        super.onStop();
//        swipeTimer.cancel();
//        swipeTimer.purge();
    }


    public void homeInternalFrag() {
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new NewsPaper());
        arrayList.add(new ComingSoonFragment());
        arrayList.add(new ComingSoonServiceFragment());

        viewPagerTabAdapter = new HomeViewPagerAdapter(viewBinding.viewpagerTab, context, getChildFragmentManager(), vendor_List, viewBinding.tabLayoutFrag.getTabCount(), arrayList);
        viewBinding.tabLayoutFrag.setupWithViewPager(viewBinding.viewpagerTab);
        viewBinding.viewpagerTab.setAdapter(viewPagerTabAdapter);
        viewBinding.tabLayoutFrag.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewBinding.viewpagerTab.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void signUpResponse(Resource<GetDataProfileModelClass> resource) {
        switch (resource.status) {
            case ApplicationConstants.LOADING:

                break;
            case ApplicationConstants.HTTP_SUCCESS:
                int status = resource.data.getStatus();
                String imageReso = String.valueOf(resource.data.getProfile().getImage());
                if (status == 200) {

                    try {
                        MySharedData.setGeneralSaveSession("image_saved", imageReso);
                        //Snackbar.make(viewBinding.getRoot(), resource.data.getMessage(), Snackbar.LENGTH_SHORT).show();
                    } catch (Exception e) {

                    }
                } else {
                    // Snackbar.make(viewBinding.getRoot(), resource.data.getMessage(), Snackbar.LENGTH_SHORT).show();
                }

                break;
            case ApplicationConstants.SHOW_ONLY_MSG:
                Snackbar.make(viewBinding.getRoot(), resource.message, Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

    }

   /* public class MyTimerTask extends TimerTask {
        //        if(getActivity() == null)
//                return;
        @Override
        public void run() {
            try{

                if (getActivity() == null)
                    return;

                getActivity().runOnUiThread(() -> {

                            currentPostion++;
                            viewBinding.viewpagerfragemnt.setCurrentItem(currentPostion);

            }

            );
            }catch (Exception e)
            {

            }
        }

    }*/

}
