package com.team.donation.Fragment.BottomBarFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.team.donation.Activity.AdminMainActivity;
import com.team.donation.Activity.LoginActivity;
import com.team.donation.Activity.OrganizerMainActivity;
import com.team.donation.Activity.UserMainActivity;
import com.team.donation.Adapter.MoneySecondAdapter;
import com.team.donation.Fragment.AddMoneyFragment;
import com.team.donation.Model.Accessories;
import com.team.donation.Model.Money;
import com.team.donation.R;
import com.team.donation.Utils.GlobalVariables;
import com.team.donation.databinding.FragmentMoneyBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoneyFragment extends Fragment {

    private FragmentMoneyBinding binding;
    private Context context;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;

    private ArrayList<Money> moneyArrayList;
    private MoneySecondAdapter adapter;


    public MoneyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_money,container,false);

        init();

        checkuser();
       // configureRV();

        getAllmoney();
        binding.addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                AddMoneyFragment clientDetailsFragment = new AddMoneyFragment();
                ft.replace(R.id.frame_layout, clientDetailsFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });






        return binding.getRoot();
    }

    private void getAllmoney() {

        DatabaseReference userRef = databaseReference.child("Money");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    moneyArrayList.clear();
                    for (DataSnapshot data:dataSnapshot.getChildren()
                    ) {
                        Money money = data.getValue(Money.class);
                        moneyArrayList.add(money);

                    }
                    Log.d("TAG", "onDataChange: "+moneyArrayList.size());
                    //adapter.notifyDataSetChanged();
                    adapter = new MoneySecondAdapter(context,moneyArrayList);
                    binding.moneyRV.setLayoutManager(new LinearLayoutManager(context));
                    binding.moneyRV.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

 /*   private void configureRV() {

        adapter = new MoneySecondAdapter(context,moneyArrayList);
        binding.moneyRV.setLayoutManager(new LinearLayoutManager(context));
        binding.moneyRV.setAdapter(adapter);

    }*/

    private void init() {

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait...");
        moneyArrayList = new ArrayList<>();

    }


    private void checkuser() {

        SharedPreferences sharedPreferences = context.getSharedPreferences(GlobalVariables.sharedPref, Context.MODE_PRIVATE);

        String userMode = sharedPreferences.getString(GlobalVariables.userMode,"");

        Log.d("TAG", "checkuser: "+userMode);

        switch (userMode){
            case "User":
            case "Admin":
                binding.addNew.setVisibility(View.GONE);
                break;


            default:
                // Amar Mathay Bari Daw :P
        }
    }
}