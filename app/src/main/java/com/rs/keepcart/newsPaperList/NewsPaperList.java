package com.rs.keepcart.newsPaperList;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rs.keepcart.R;
import com.rs.keepcart.databinding.FragmentNewsPaperListBinding;

import java.util.ArrayList;
import java.util.List;


public class NewsPaperList extends Fragment {
    SearchView searchView;
    List<NewspapersDetail> newspapersDetails;
    private Fragment fragment;
    private FragmentTransaction fragmentTransaction;
    private FragmentNewsPaperListBinding viewBinding;
    private Context context;
    private NewsPaperViewModelClass viewModel;
    //private NewsPaperAdapter newsPaperAdapter;
    private int positionId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_paper_list, container, false);
        viewBinding = DataBindingUtil.bind(view);
        context = getActivity();
        newspapersDetails = new ArrayList<>();

        /*viewBinding.userlistRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //newsPaperResponse();
//        viewBinding.userlistRecyclerView.setAdapter(new NewsPaperAdapter(getActivity()));
        viewModel = ViewModelProviders.of(this).get(NewsPaperViewModelClass.class);
        viewModel.loginLiveData.observe(this, this::loginResponse);

        setSearchTextListener();
        recycleFilteredList();*/
        return view;
    }

  /*  private void loginResponse(Resource<NewsPaperModelClass> resource) {
        //viewBinding.progressBarLog.setVisibility(View.GONE);
        switch (resource.status) {
            case ApplicationConstants.LOADING:
                //viewBinding.progressBarLog.setVisibility(View.VISIBLE);
                break;
            case ApplicationConstants.HTTP_SUCCESS:
                int status = resource.data.getStatus();

                switch (status) {
                    // NewsPaperModelClass newsPaperModelClass = response.body();
                    case 200:
                        try {
                            newspapersDetails = resource.data.getNewspapersDetails();
                            newsPaperAdapter = new NewsPaperAdapter(context, newspapersDetails);
                            viewBinding.userlistRecyclerView.setAdapter(newsPaperAdapter);
                            setSearchTextListener();
                           //  loginLiveData.setValue(Resource.success(newsPaperModelClass,""));
                            //Toast.makeText(context, "vendor id" + newsPaperModelClass.getVendorid(), Toast.LENGTH_SHORT).show();


                        } catch (Exception e) {
                            Snackbar.make(viewBinding.getRoot(), resource.message, Snackbar.LENGTH_SHORT).show();
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

    *//*public void newsPaperResponse() {
        String id_ = MySharedData.getGeneralSaveSession("userId");
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<NewsPaperModelClass> call = apiInterface.getNewspaper(id_);
        call.enqueue(new Callback<NewsPaperModelClass>() {
            @Override
            public void onResponse(Call<NewsPaperModelClass> call, Response<NewsPaperModelClass> response) {
                try {
                    if (response.isSuccessful()) {
                        NewsPaperModelClass newsPaperModelClass = response.body();
                        if (newsPaperModelClass.getStatus() == 200) {
                            newspapersDetails.addAll(newsPaperModelClass.getNewspapersDetails());
                            newsPaperAdapter = new NewsPaperAdapter(context, newspapersDetails);
                            viewBinding.userlistRecyclerView.setAdapter(newsPaperAdapter);
                        }
                        // loginLiveData.setValue(Resource.success(newsPaperModelClass,""));
                        Toast.makeText(context, "vendor id" + newsPaperModelClass.getVendorid(), Toast.LENGTH_SHORT).show();

                    } else {
                        // loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                    }

                } catch (Exception e) {
                    //loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                }
            }

            @Override
            public void onFailure(Call<NewsPaperModelClass> call, Throwable t) {
                //loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                System.out.println("what is the problem :" + t.getMessage());

            }
        });
    }*//*

    public void setSearchTextListener() {
        viewBinding.searchViewUser.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                                              @Override
                                                              public boolean onQueryTextSubmit(String query) {
                                                                  newsPaperAdapter.getFilter().filter(query);
                                                                  return false;
                                                              }

                                                              @Override
                                                              public boolean onQueryTextChange(String newText) {
                                                                  //newsPaperAdapter.getFilter().filter(newText);
                                                                  if(newText.isEmpty())
                                                                  {
                                                                      newspapersDetails = new ArrayList<>();

                                                                  }else
                                                                  {
                                                                      newsPaperAdapter.getFilter().filter(newText);
                                                                  }
                                                                  return true;

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
        fragmentTransaction = ((HomeActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.commit();
    }

    public class NewsPaperAdapter extends RecyclerView.Adapter<NewsPaperAdapter.ViewHolder> implements Filterable {
        private List<NewspapersDetail> newspaperFilteredList;

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

            NewspapersDetail newspapersDetaill = newspapersDetails.get(position);
            String Base_URL = "http://www.uicreations.com/keepkart/assets/images/";
            holder.viewBinding.newsPaperName.setText(newspapersDetaill.getName());

            Glide.with(context).load(Base_URL + newspapersDetaill.getImages())
                    .error(R.drawable.banner)
                    .into(holder.imageView);

            holder.viewBinding.userNextPage.setOnClickListener(new View.OnClickListener() {
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
            return newspapersDetail.size();
        }

        @Override
        public Filter getFilter() {

            return new Filter() {
                @Override

                protected FilterResults performFiltering(CharSequence charSequence) {

                    String charString = charSequence.toString();
                    // filtermagzinesDetail = magzinesDetail;
                    //viewBinding.searchViewUser.
                    if (charString.isEmpty()) {

                        newspaperFilteredList = newspapersDetail;

                    } else {

                        ArrayList<NewspapersDetail> filteredList = new ArrayList<>();

                        for (NewspapersDetail state : newspapersDetail) {

                            if (state.getName().toLowerCase().contains(charString)) {

                                filteredList.add(state);
                            }
                        }

                        newspapersDetail = filteredList;
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = newspapersDetail;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    newspapersDetail = (List<NewspapersDetail>) filterResults.values;

                    notifyDataSetChanged();
                }
            };
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
    }

*/
}
