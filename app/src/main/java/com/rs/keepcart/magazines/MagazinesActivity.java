package com.rs.keepcart.magazines;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rs.keepcart.R;
import com.rs.keepcart.databinding.FragmentMagazinesListBinding;
import com.rs.keepcart.databinding.MagazinesListDesignBinding;
import com.rs.keepcart.fragments.CustomerScreen;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.utills.ApplicationConstants;
import com.rs.keepcart.utills.MySharedData;
import com.rs.keepcart.utills.RecyclerTouchListner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 5/23/2018.
 */

public class MagazinesActivity extends AppCompatActivity {
    public static final String Base_URL = "http://www.uicreations.com/keepkart/assets/images/";
    private Fragment fragment;
    private FragmentTransaction fragmentTransaction;
    private FragmentMagazinesListBinding viewbinding;
    private Context context;
    private MagazinesViewModelClass viewModel;
    private List<MagzinesDetail> magzinesDetail;
    private MagazinesAdapter magazinesAdapter;
    private SetMagazinesInfoClass infoClass;
    private int positionId;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewbinding = DataBindingUtil.setContentView(this, R.layout.fragment_magazines_list);
        magzinesDetail = new ArrayList<>();
        viewbinding.magazinelistRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        viewModel = ViewModelProviders.of(this).get(MagazinesViewModelClass.class);
        viewModel.loginLiveData.observe(this, this::loginResponse);
        //String id_ = MySharedData.getGeneralSaveSession("vend_id");
        String id_ = MySharedData.getGeneralSaveSession("userId");
        infoClass = new SetMagazinesInfoClass(id_);
        viewModel.loginApi(infoClass);
        recycleFilteredList();
        title = findViewById(R.id.page_title);
        title.setText(R.string.magazines_toolbar_title);

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
                    case 200:
                        try {
                            magzinesDetail = resource.data.getMagzinesDetails();
                            // magazinesAdapter = new MagazinesAdapter(getApplicationContext(), magzinesDetail);
                            //viewbinding.magazinelistRecyclerView.setAdapter(magazinesAdapter);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;


                    case ApplicationConstants.SHOW_ONLY_MSG:
                        Snackbar.make(viewbinding.getRoot(), resource.message, Snackbar.LENGTH_SHORT).show();
                        break;
                }
        }
    }



    public void recycleFilteredList() {
        viewbinding.magazinelistRecyclerView.addOnItemTouchListener(new RecyclerTouchListner(context,
                viewbinding.magazinelistRecyclerView, new RecyclerTouchListner.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                MagzinesDetail model = magzinesDetail.get(position);
                //statId =stateFilteredList.get(position).

                positionId = (Integer) view.getId();
                if (fragment == null) {
                    fragment = new CustomerScreen();
                    fragLoadMethod(fragment);

                } else {
                    fragLoadMethod(fragment);
                }
            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    public void fragLoadMethod(Fragment fragment) {
        // fragmentTransaction = (Activity)context.getApplicationContext().
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame1, fragment, fragment.getClass().getName());
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        // where mText is the title you want on your toolbar/actionBar
        title.setText(R.string.user_toolbar_title);
    }

    public void backButton(View view) {
        if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }

    public class MagazinesAdapter extends RecyclerView.Adapter<MagazinesAdapter.ViewHolder>/* implements Filterable*/ {
        List<Collection> filtermagzinesDetail;
        private Context context;
        private List<Collection> magzinesDetail;

        public MagazinesAdapter(FragmentActivity activity) {
            this.context = activity;
        }

        public MagazinesAdapter(Context context, List<Collection> magzinesDetail) {
            this.context = context;
            this.magzinesDetail = magzinesDetail;
            this.filtermagzinesDetail = magzinesDetail;
        }

        @Override
        public MagazinesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.magazines_list_design, parent, false);
            return new MagazinesAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MagazinesAdapter.ViewHolder holder, final int position) {

            Collection magzinesDetaill = magzinesDetail.get(position);

            Glide.with(context).load(Base_URL + magzinesDetaill.getImages())
                    .error(R.drawable.banner)
                    .into(holder.viewBinding.magazineImage);
            holder.viewBinding.magazineName.setText(magzinesDetaill.getName());

            holder.viewBinding.magazineImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "position" + position, Toast.LENGTH_SHORT).show();
                    if (fragment == null) {
                        fragment = new CustomerScreen();
                        fragLoadMethod(fragment);
                    } else {
                        fragLoadMethod(fragment);
                    }
                    fragmentTransaction.addToBackStack(null);
                }
            });
        }

        @Override
        public int getItemCount() {
            return magzinesDetail.size();
        }



        public class ViewHolder extends RecyclerView.ViewHolder {
            private MagazinesListDesignBinding viewBinding;

            public ViewHolder(View itemView) {
                super(itemView);
                viewBinding = DataBindingUtil.bind(itemView);
            }
        }
    }

}
