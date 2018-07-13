package com.rs.keepcart.newsPaperList;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rs.keepcart.R;
import com.rs.keepcart.databinding.FragmentNewsPaperListBinding;
import com.rs.keepcart.databinding.NewssPaperListBinding;
import com.rs.keepcart.model.SetNewspaperInfoModelClass;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.utills.ApplicationConstants;
import com.rs.keepcart.utills.MySharedData;
import com.rs.keepcart.utills.RecyclerTouchListner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 5/24/2018.
 */

public class NewsPaperListActivity extends AppCompatActivity {
    SearchView searchView;
    private Fragment fragment;
    private FragmentTransaction fragmentTransaction;
    private FragmentNewsPaperListBinding viewBinding;
    private Context context;
    private NewsPaperViewModelClass viewModel;
    List<NewspapersDetail> newspapersDetails;
    private NewsPaperAdapter newsPaperAdapter;
    private int positionId;
    private SetNewspaperInfoModelClass infoClass;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(this, R.layout.fragment_news_paper_list);
        context = getApplicationContext();
        newspapersDetails = new ArrayList<>();

        viewBinding.userlistRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

         viewModel = ViewModelProviders.of(this).get(NewsPaperViewModelClass.class);
         viewModel.loginLiveData.observe(this, this::loginResponse);
        //String id_ = MySharedData.getGeneralSaveSession("vend_id");
        String id_ = MySharedData.getGeneralSaveSession("userId");
        infoClass = new SetNewspaperInfoModelClass(id_);
        viewModel.loginApi(infoClass);
        title = findViewById(R.id.page_title);

        title.setText(R.string.newspaper_toolbar_title);

        recycleFilteredList();
    }

    private void loginResponse(Resource<NewsPaperModelClass> resource) {
        viewBinding.progressBarLog.setVisibility(View.GONE);
        switch (resource.status) {
            case ApplicationConstants.LOADING:
                viewBinding.progressBarLog.setVisibility(View.VISIBLE);
                break;
            case ApplicationConstants.HTTP_SUCCESS:
                int status = resource.data.getStatus();

                switch (status) {
                   // NewsPaperModelClass newsPaperModelClass = response.body();
                    case 200:
                        newspapersDetails = resource.data.getNewspapersDetails();
                        if(newspapersDetails!=null){
                            newsPaperAdapter = new NewsPaperAdapter(this,  newspapersDetails);
                            viewBinding.userlistRecyclerView.setAdapter(newsPaperAdapter);
                            setSearchTextListener();
                        }

                        break;

                }
                //Toast.makeText(context, resource.data.get, Toast.LENGTH_SHORT).show();
                break;
            case ApplicationConstants.SHOW_ONLY_MSG:
                Snackbar.make(viewBinding.getRoot(), resource.message, Snackbar.LENGTH_SHORT).show();
                break;
        }

    }

    public void setSearchTextListener() {
        viewBinding.searchViewUser.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                                              @Override
                                                              public boolean onQueryTextSubmit(String query) {
                                                                  newsPaperAdapter.getFilter().filter(query);
                                                                  return false;
                                                              }

                                                              @Override
                                                              public boolean onQueryTextChange(String newText) {
                                                                  newsPaperAdapter.getFilter().filter(newText);
                                                                  return false;
                                                              }

                                                          }

        );

    }

    public void recycleFilteredList() {
        viewBinding.userlistRecyclerView.addOnItemTouchListener(new RecyclerTouchListner(context,
                viewBinding.userlistRecyclerView, new RecyclerTouchListner.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                NewspapersDetail model = newspapersDetails.get(position);
                //statId =stateFilteredList.get(position).

                positionId = (Integer) view.getId();
//                if (fragment == null) {
//                    fragment = new CustomerScreen();
//                    fragLoadMethod(fragment);
//
//                } else {
//                    fragLoadMethod(fragment);
//                }

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    public class NewsPaperAdapter extends RecyclerView.Adapter<NewsPaperAdapter.ViewHolder> implements Filterable {
        private  List<NewspapersDetail>newspaperFilteredList = new ArrayList<>();

        private Context context;
        private List<NewspapersDetail> newspapersDetail;


        public NewsPaperAdapter(Context context, List<NewspapersDetail> newspapersDetail) {
            this.context = context;
            this.newspapersDetail = newspapersDetail;
            this.newspaperFilteredList = newspapersDetail;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newss_paper_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

            NewspapersDetail newspapersDetaill = newspaperFilteredList.get(position);
            String Base_URL = "http://www.uicreations.com/keepkart/assets/images/";
            holder.viewBinding.newsPaperName.setText(newspapersDetaill.getName());

            Glide.with(context).load(Base_URL+newspapersDetaill.getImages())
                    .error(R.drawable.banner)
                    .into(holder.imageView);

            holder.viewBinding.userNextPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, "position" + position, Toast.LENGTH_SHORT).show();
                    /*if (fragment == null) {
                        fragment = new CustomerScreen();
                        fragLoadMethod(fragment);
                    } else {
                        fragLoadMethod(fragment);
                    }
                    fragmentTransaction.addToBackStack(null);*/
                }
            });
        }

        @Override
        public int getItemCount() {
            if(newspaperFilteredList!=null) {
                return newspaperFilteredList.size();
            }else {
                return 0;
            }
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private NewssPaperListBinding viewBinding;
            private ImageView imageView;
            public ViewHolder(View itemView) {
                super(itemView);
                viewBinding = DataBindingUtil.bind(itemView);
                imageView = itemView.findViewById(R.id.newsPaper_image);
            }
        }
        @Override
        public Filter getFilter() {

            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {

                    String charString = charSequence.toString();
                    FilterResults filterResults = new FilterResults();
                    if (charString.isEmpty()) {

                        filterResults.values = newspapersDetail;

                    } else {

                        ArrayList<NewspapersDetail> filteredList = new ArrayList<>();

                        for (NewspapersDetail state : newspapersDetail) {

                            if(state.getName()!=null)
                            {
                                if (state.getName().toLowerCase().contains(charString)) {

                                    filteredList.add(state);
                                }
                            }
                        }

                        newspaperFilteredList = filteredList;
                        filterResults.values = filteredList;
                        filterResults.count = filteredList.size();
                    }

                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    newspaperFilteredList = (List<NewspapersDetail>) filterResults.values;

                    notifyDataSetChanged();
                }
            };
        }
    }

    public void fragLoadMethod(Fragment fragment) {
        // fragmentTransaction = (Activity)context.getApplicationContext().
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame1, fragment);
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.commit();
    }
    public void backButton(View view)
    {
        if (getFragmentManager().getBackStackEntryCount()>0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }
}
