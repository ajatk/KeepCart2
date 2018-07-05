package com.rs.keepcart.magazines;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.rs.keepcart.R;
import com.rs.keepcart.dashboard.NewsPaper;
import com.rs.keepcart.databinding.FragmentMagazinesBinding;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.utills.ApplicationConstants;
import com.rs.keepcart.utills.DatabaseHandlerForModelClass;
import com.rs.keepcart.utills.MySharedData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 5/31/2018.
 */

public class ActivityMagazines extends AppCompatActivity {
    DatabaseHandlerForModelClass dbM;
    int i;
    private FragmentMagazinesBinding viewbinding;
    private Context context;
    private List<MagzinesDetail> collectionList;
    private List<MagzinesDetail> magzinesDetails;
    private List<Fragment> fragmentList;
    private SetMagazinesInfoClass infoClass;
    private MagazinesViewModelClass viewModel;
    private int position = 0;
    private TextView title;
    private MagazinesViewPagerAdapter viewPagerTabAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewbinding = DataBindingUtil.setContentView(this, R.layout.fragment_magazines);
        context = this;
        magzinesDetails = new ArrayList<>();
        collectionList = new ArrayList<>();
        fragmentList = new ArrayList<>();

        viewbinding.userTabLayout.setupWithViewPager(viewbinding.magazineViewPager);
        setUpViewPager(viewbinding.magazineViewPager);
        viewModel = ViewModelProviders.of(this).get(MagazinesViewModelClass.class);
        viewModel.loginLiveData.observe(this, this::loginResponse);
        String id_ = MySharedData.getGeneralSaveSession("userId");
        infoClass = new SetMagazinesInfoClass(id_);
        viewModel.loginApi(infoClass);
        title = findViewById(R.id.page_title);
        title.setText(R.string.magazines_toolbar_title);
        dbM = new DatabaseHandlerForModelClass(context);

    }

    private void loginResponse(Resource<MagazinesModelClass> resource) {
        viewbinding.progressBarLog.setVisibility(View.GONE);
        switch (resource.status) {
            case ApplicationConstants.LOADING:
                viewbinding.progressBarLog.setVisibility(View.VISIBLE);
                break;
            case ApplicationConstants.HTTP_SUCCESS:
                int status = resource.data.getStatus();

                switch (status) {
                    // NewsPaperModelClass newsPaperModelClass = response.body();
                    // List<Collection> magazinesModelClass = resource.data.getMagzinesDetails().get(position).getCollection();
                    case 200:

                        magzinesDetails = resource.data.getMagzinesDetails();

                    /*  for (MagzinesDetail cat_list : magzinesDetails) {
                            collectionList.add(cat_list);
                        }*/
                      /*  for (i = 0; i < magzinesDetails.size(); i++) {
                            Bundle bundl = new Bundle();
                            bundl.putString("cat_id", String.valueOf(magzinesDetails));
                            bundl.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) getItembyId(String.valueOf(magzinesDetails)));
                            MagazinesList magazinesList = new MagazinesList();
                            magazinesList.setArguments(bundl);
                        }
*/                        if(magzinesDetails!=null)
                    {
                        ((MagazinesList)viewPagerTabAdapter.getItem(0)).setMagzines_Details(magzinesDetails);
                    }

                        break;
                }

                break;
            case ApplicationConstants.SHOW_ONLY_MSG:
                Snackbar.make(viewbinding.getRoot(), resource.message, Snackbar.LENGTH_SHORT).show();
                break;
        }

    }

    public List<MagzinesDetail> getItembyId(String catId) {
        List<MagzinesDetail> list = new ArrayList<>();
        for (MagzinesDetail magzn : magzinesDetails) {
            list.add(magzn);
            if (catId.equals(magzn.getName())) {

            }
        }
        return list;
    }
    public void setUpViewPager(ViewPager viewPager)
    {
        fragmentList.add(new MagazinesList());
        fragmentList.add(new MagazinesList());
        fragmentList.add(new MagazinesList());
        fragmentList.add(new MagazinesList());


        viewPagerTabAdapter = new MagazinesViewPagerAdapter(context,getSupportFragmentManager(), fragmentList, viewbinding.magazineViewPager);
        /*for(int j = 0; j<fragmentList.size();j++)
        viewPagerTabAdapter.addFrag(fragmentList.get(j));*/
        viewPager.setAdapter(viewPagerTabAdapter);
        //viewPager.setOffscreenPageLimit(magzinesDetails.size());
        /* viewbinding.magazineViewPager.setAdapter(new MagazinesViewPagerAdapter
        (context, getSupportFragmentManager(), magzinesDetails));*/
    }
    public void backButton(View view) {
        if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }

    public class MagazinesViewPagerAdapter extends FragmentStatePagerAdapter {
        String a[] = {"Education", "New interior", "Sports", "Recipies"};
        Resource resource;
        private List<MagzinesDetail> magzinesDetails_cat;
        private Context context;
        List<Fragment> mfragmentList;

        public MagazinesViewPagerAdapter(Context context, FragmentManager supportFragmentManager, List<Fragment> fragmentList, ViewPager viewPager) {
            super(supportFragmentManager);
            this.context = context;
            this.mfragmentList = fragmentList;
            viewPager.setOffscreenPageLimit(4);
        }

        @Override
        public Fragment getItem(int position) {

//         int c_id = Integer.parseInt(magzinesDetails.get(position).getId());
            return mfragmentList.get(position);


        }
        @Override
        public int getCount() {
            return mfragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            if (position == 0) {
                title = a[0];

            } else if (position == 1) {
                title = a[1];
            } else if (position == 2) {
                title = a[2];
            } else {
                title = a[3];
            }

           /* if (position == 0) {
                title = magzinesDetails_cat.get(0).getName()*//* a[0]*//*;

            } else if (position == 1) {
                title = magzinesDetails_cat.get(1).getName() *//*a[1]*//*;
            } else if (position == 2) {
                title = magzinesDetails_cat.get(2).getName()*//* a[2]*//*;
            } else {
                title = magzinesDetails_cat.get(3).getName() *//*a[3]*//*;
            }*/

            return title;
        }


    }
}
