package com.team.donation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.team.donation.Model.Money;
import com.team.donation.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Amit on 13,April,2020
 * kundu.amit517@gmail.com
 */
public class OwnMoneyAdapter extends RecyclerView.Adapter<OwnMoneyAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Money> moneyArrayList;

    public OwnMoneyAdapter(Context context, ArrayList<Money> moneyArrayList) {
        this.context = context;
        this.moneyArrayList = moneyArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_money_own,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        
        Money money = moneyArrayList.get(position);
        String amount = String.valueOf(money.getAskedAmount());
        holder.ammountasked.setText(amount);
        holder.dateMoney11.setText(money.getPostedDate());
        
        holder.seeAllDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "vclick", Toast.LENGTH_SHORT).show();
            }
        });
        
    }

    @Override
    public int getItemCount() {
        return moneyArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ammountasked,dateMoney11;
        Button seeAllDonation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            ammountasked = itemView.findViewById(R.id.ammountasked);
            dateMoney11 = itemView.findViewById(R.id.dateMoney11);
            seeAllDonation = itemView.findViewById(R.id.seeAllDonation);
            


        }
    }
}