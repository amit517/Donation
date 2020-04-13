package com.team.donation.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import com.fxn.OnBubbleClickListener;
import com.team.donation.Fragment.BottomBarFragments.AboutUsFragment;
import com.team.donation.Fragment.BottomBarFragments.AccessoriesFragment;
import com.team.donation.Fragment.BottomBarFragments.MoneyFragment;
import com.team.donation.Fragment.BottomBarFragments.OrgOwnFragment;
import com.team.donation.Fragment.BottomBarFragments.UserFragment;
import com.team.donation.R;
import com.team.donation.databinding.ActivityOrganizerMainBinding;

public class OrganizerMainActivity extends AppCompatActivity {

    private ActivityOrganizerMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_organizer_main);

        replaceFragment(new MoneyFragment());

        binding.bottomNavBar.addBubbleListener(new OnBubbleClickListener() {
            @Override
            public void onBubbleClick(int i) {
                Log.d("ERR", "onBubbleClick: "+i);

                switch (i){
                    case R.id.money:
                        replaceFragment(new MoneyFragment());
                        break;
                    case R.id.accessories:
                        replaceFragment(new AccessoriesFragment());
                        break;

                    case R.id.own_post:
                        replaceFragment(new OrgOwnFragment());
                        break;
                    case R.id.user_profile:
                        replaceFragment(new UserFragment());
                        break;
                    case R.id.about:
                        replaceFragment(new AboutUsFragment());
                        break;

                        default: // DashBoard Fragment
                            replaceFragment(new MoneyFragment());
                }

            }
        });

    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 1) {
            super.onBackPressed();
            //additional code

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Exit Charitable")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            replaceFragment(new MoneyFragment());
                        }
                    })
                    .setCancelable(false)
                    .show();

        } else {
            getSupportFragmentManager().popBackStack();
        }

    }
}