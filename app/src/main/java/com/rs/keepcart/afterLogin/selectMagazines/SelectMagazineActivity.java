package com.rs.keepcart.afterLogin.selectMagazines;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.rs.keepcart.R;
import com.rs.keepcart.afterLogin.SelectedInfoModelClass;
import com.rs.keepcart.afterLogin.selectNewspaper.SelectNewsPaperActivity;
import com.rs.keepcart.afterLogin.selectNewspaper.SelectNewsPaperViewModel;
import com.rs.keepcart.afterLogin.selectNewspaper.SelectedNewsPaperModelClass;
import com.rs.keepcart.afterLogin.selectNewspaper.SelectedNewsPaperViewModelClass;
import com.rs.keepcart.dashboard.HomeActivity;
import com.rs.keepcart.databinding.ActivitySelectMagazineBinding;
import com.rs.keepcart.databinding.SelectMagazinesListDesignBinding;
import com.rs.keepcart.magazines.SetMagazinesInfoClass;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.utills.ApplicationConstants;
import com.rs.keepcart.utills.MySharedData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SelectMagazineActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivitySelectMagazineBinding viewBinding;
    public String TAG = SelectMagazineActivity.class.getSimpleName();
    private SelectMagazinesViewModel viewModel;
    private  SetMagazinesInfoClass infoClass ;
    private List<String> magzinesDetail;
    private List<String> listSelected;
    private MagazinesAdapter magazinesAdapter;
    private Context context = this;
    private SelectedInfoModelClass infoModelClass;
    private Set<String> list;
    private String id_;
    private StringBuilder sb;
    private SelectedMagazinesViewModelClass viewModelSelected;
    JSONObject jsonObject;
    private  String convertedString;
    private ArrayList<Boolean> listB ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      viewBinding = DataBindingUtil.setContentView(this,R.layout.activity_select_magazine);
        magzinesDetail = new ArrayList<>();
        listSelected = new ArrayList<>();
        listB = new ArrayList<>();
        getViewModel();
        // id_ = /*MySharedData.getGeneralSaveSession("userId")*/"131";
        id_ = MySharedData.getGeneralSaveSession("userId");
        infoClass = new SetMagazinesInfoClass(id_);
        viewModel.loginApi(infoClass);
        viewBinding.userlistRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        viewBinding.continueApp.setOnClickListener(this);
        viewBinding.skip.setOnClickListener(this);
    }
    public void getViewModel() {
        viewModel = ViewModelProviders.of(this).get(SelectMagazinesViewModel.class);
        viewModel.loginLiveData.observe(this, this::loginResponse);

        viewModelSelected = ViewModelProviders.of(this).get(SelectedMagazinesViewModelClass.class);
        viewModelSelected.loginLiveData.observe(this, this::loginRespons);

    }
    private void loginResponse(Resource<SelectMagazinesModelClass> resource) {
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
                        try {
                        magzinesDetail = resource.data.getMagazineList();
                        magazinesAdapter = new MagazinesAdapter(context, magzinesDetail);
                            for(int i =0; i<magazinesAdapter.getItemCount();i++){
                                listB.add(false);
                            }
                        viewBinding.userlistRecyclerView.setAdapter(magazinesAdapter);

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

    private void loginRespons(Resource<SelectedMagazineModelClass> resource) {
        viewBinding.progressBarLog.setVisibility(View.GONE);
        switch (resource.status) {
            case ApplicationConstants.LOADING:
                viewBinding.progressBarLog.setVisibility(View.VISIBLE);
                break;
            case ApplicationConstants.HTTP_SUCCESS:
                int status = resource.data.getStatus();
                if (status == 200) {
                    try {
                        Toast.makeText(context, "item saved here please check", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, HomeActivity.class);
                        startActivity(intent);

                    } catch (Exception e) {
                        Snackbar.make(viewBinding.getRoot(), resource.message, Snackbar.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar.make(viewBinding.getRoot(), "Please Select Magazines First", Snackbar.LENGTH_SHORT).show();
                }
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.continue_app:
                  infoModelClass = new SelectedInfoModelClass(id_, convertedString);
                  viewModelSelected.loginApiSelect(infoModelClass);
//                Intent inD = new Intent(SelectMagazineActivity.this, HomeActivity.class);
//                startActivity(inD);
                break;
            case R.id.skip:
                Intent intent  = new Intent(SelectMagazineActivity.this,HomeActivity.class);
                startActivity(intent);
                break;
        }
    }

    public class MagazinesAdapter extends RecyclerView.Adapter<MagazinesAdapter.ViewHolder> {

        private Context context;
        private List<String> magzinesDetailk;



        public MagazinesAdapter(Context context, List<String> magzinesDetail) {
            this.context = context;
            this.magzinesDetailk = magzinesDetail;
            jsonObject = new JSONObject();

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_magazines_list_design, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.viewbinding.magazineName.setText(magzinesDetailk.get(position));
            if(listB.get(position)){
                holder.viewbinding.checkMagazines.setChecked(true);
            }else {
                holder.viewbinding.checkMagazines.setChecked(false);
            }
            holder.viewbinding.checkMagazines.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listB.set(position, holder.viewbinding.checkMagazines.isChecked());
                    if (holder.viewbinding.checkMagazines.isChecked()) {
                        listSelected.add(String.valueOf(position + 1));
                        list = new HashSet<>();
                        sb = new StringBuilder();
                        list.addAll(listSelected);
                        try {
                            for (String s : list) {
                                //list_string += s;
                                sb.append(s).append(",");
                                if (!s.equals("")) {
                                    convertedString = sb.toString();
                                } else {
                                }
                                convertedString = convertedString.replaceAll(",$", "");
                            }
//                        jsonObject.put("magazines_id", sb);
//                        jsonObject.put("reg_id", id_);
                            System.out.println("Size of converted array list convertedString 1 :" + convertedString);
                            System.out.println("Size of converted array list list.toString()list.toString()  1 :" + list.toString());
                            System.out.println("Size of converted array list listSelected.toString()listSelected.toString() 2 :" + listSelected.toString());
                            System.out.println("Size of converted array list :" + sb);

                        } catch (Exception e) {
                            System.out.println("what is the prob :" + e.getMessage());
                        }
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return magzinesDetailk.size();
        }



        public class ViewHolder extends RecyclerView.ViewHolder {
            private SelectMagazinesListDesignBinding viewbinding;

            public ViewHolder(View itemView) {
                super(itemView);
                viewbinding = DataBindingUtil.bind(itemView);
            }
        }
    }
}
