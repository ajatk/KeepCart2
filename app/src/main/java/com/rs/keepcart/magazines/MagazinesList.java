package com.rs.keepcart.magazines;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rs.keepcart.R;
import com.rs.keepcart.databinding.FragmentMagazinesListBinding;
import com.rs.keepcart.databinding.MagazinesListDesignBinding;
import com.rs.keepcart.fragments.CustomerScreen;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.utills.ApplicationConstants;
import com.rs.keepcart.utills.DatabaseHandlerForModelClass;
import com.rs.keepcart.utills.RecyclerTouchListner;

import java.util.ArrayList;
import java.util.List;


public class MagazinesList extends Fragment {
    public static final String Base_URL = "http://www.uicreations.com/keepkart/assets/images/";
    DatabaseHandlerForModelClass dbM;
    private Fragment fragment;
    private FragmentTransaction fragmentTransaction;
    private FragmentMagazinesListBinding viewBinding;
    private Context context;
    private MagazinesViewModelClass viewModel;
    private List<MagzinesDetail>magzinesDetail;
    private List<MagzinesDetail>magzinesDetailParce;

    private List<Collection> collectionList;
    private MagazinesAdapter magazinesAdapter;
    private SetMagazinesInfoClass infoClass;
    private  int i ;
    private TextView title;
    private Bundle bundle;


    public List<MagzinesDetail> getMagzines_Details() {
        return magzines_Details;
    }

    public void setMagzines_Details(List<MagzinesDetail> magzines_Details) {
        this.magzines_Details = magzines_Details;
        if(magzines_Details!=null)
        {
            bundleRespons();
        }

    }

    private List<MagzinesDetail> magzines_Details;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_magazines_list, container, false);
        viewBinding = DataBindingUtil.bind(view);
        context = getActivity();

        magzinesDetail = new ArrayList<>();
        magzinesDetailParce = new ArrayList<>();
        collectionList = new ArrayList<>();

        /*title = findViewById(R.id.page_title);
        title.setText(R.string.newspaper_toolbar_title);*/
        recycleFilteredList();
        setSearchTextListener();
        return view;
    }


     private void bundleRespons() {
         viewBinding.magazinelistRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
         magazinesAdapter = new MagazinesAdapter(context, magzines_Details);
         viewBinding.magazinelistRecyclerView.setAdapter(magazinesAdapter);

     }
         public void setSearchTextListener(){
            viewBinding.searchViewUser.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               magazinesAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

//                if(newText.isEmpty())
//                {
//                   magzinesDetail = new ArrayList<>();
//
//                }else
//                {
                    magazinesAdapter.getFilter().filter(newText);
            //    }
                return true;
            }
            }
        );

    }
    public void recycleFilteredList()
    {
        viewBinding.magazinelistRecyclerView.addOnItemTouchListener(new RecyclerTouchListner(context,
                viewBinding.magazinelistRecyclerView, new RecyclerTouchListner.ClickListener() {
            @Override
            public void onClick(View view, int position) {


                if(fragment==null)
                {
                    fragment = new CustomerScreen();
                    fragLoadMethod(fragment);

                }else {
                    fragLoadMethod(fragment);
                }
            }
            @Override
            public void onLongClick(View view, int position) {
            }
        }));

    }

    public class MagazinesAdapter extends RecyclerView.Adapter<MagazinesAdapter.ViewHolder> implements Filterable{
        private Context context;


        public List<MagzinesDetail> filtermagzinesDetail = new ArrayList<>();
        private List<MagzinesDetail> magzinesDetaillis;


        public MagazinesAdapter(FragmentActivity activity) {
            this.context = activity;
        }

        public MagazinesAdapter(Context context, List<MagzinesDetail> magzinesDetailParce) {
            this.context = context;
            this.magzinesDetaillis = magzinesDetailParce;
            this.filtermagzinesDetail = magzinesDetailParce;

        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.magazines_list_design,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {

           //Collection collectionDetail =collectionListD.get(position);
           MagzinesDetail magzinesDetail = filtermagzinesDetail.get(position);
            //collectionDetail = magzinesDetail.get(position).getCollection();
            Glide.with(context).load(Base_URL+magzinesDetail.getImages())
                    .error(R.drawable.banner)
                    .into(holder.viewBinding.magazineImage);
            holder.viewBinding.magazineName.setText(magzinesDetail.getName());

            holder.viewBinding.magazineImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "position"+position, Toast.LENGTH_SHORT).show();
                    if(fragment == null)
                    {
                        fragment = new CustomerScreen();
                        fragLoadMethod(fragment);
                    } else
                    {
                        fragLoadMethod(fragment);
                    }
                    fragmentTransaction.addToBackStack(null);
                }
            });
        }

        @Override
        public int getItemCount() {
            return filtermagzinesDetail.size()/*collectionListD.size()*/;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private MagazinesListDesignBinding viewBinding;
            public ViewHolder(View itemView) {
                super(itemView);
                viewBinding = DataBindingUtil.bind(itemView);
            }
        }
        @Override
        public Filter getFilter() {

            return new Filter() {
                FilterResults filterResults;
                String charString;
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {

                    charString = charSequence.toString();
                    filterResults = new FilterResults();
                   // filtermagzinesDetail = magzinesDetail;
                    //viewBinding.searchViewUser.
                    if (charString.isEmpty()) {

                        filterResults.values = magzinesDetaillis;

                    } else {

                        List<MagzinesDetail> filteredList = new ArrayList<>();

                        for (MagzinesDetail state : magzinesDetaillis) {

                            if (state.getName().toLowerCase().contains(charString)) {
                                filteredList.add(state);
                            }
                        }

                        filtermagzinesDetail = filteredList;

                        filterResults.values = filteredList;
                        filterResults.count = filteredList.size();
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    filtermagzinesDetail = (List<MagzinesDetail>) filterResults.values;

                    notifyDataSetChanged();
                }
            };
        }
    }
    public void fragLoadMethod(Fragment fragment)
    {
        // fragmentTransaction = (Activity)context.getApplicationContext().
        fragmentTransaction = ((ActivityMagazines)context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame1, fragment);
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.commit();
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
