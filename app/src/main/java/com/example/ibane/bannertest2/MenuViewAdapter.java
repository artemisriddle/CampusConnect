package com.example.ibane.bannertest2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by jesllagr on 10/25/15.
 */
public class MenuViewAdapter extends RecyclerView.Adapter<MenuViewAdapter.ViewHolder> {

    private List<MenuItem> itemList;
    private Context context;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private List<MenuItem> itemList;
        public TextView menuOption;
        public ImageView optionPhoto;

        public ViewHolder(View itemView, List<MenuItem> itemList) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.itemList = itemList;
            menuOption = (TextView)itemView.findViewById(R.id.menu_option);
            optionPhoto = (ImageView)itemView.findViewById(R.id.option_photo);
        }

        @Override
        public void onClick(View view) {
            switch(getLayoutPosition()){
                case 0:
                    context.startActivity(new Intent(context, ClassSchedule.class));
                    break;
                case 1:
                    context.startActivity(new Intent(context, StudentTranscript.class));
                    break;
                case 2:
                    context.startActivity(new Intent(context, StudentInfo.class));
                    break;
                case 3:
                    context.startActivity(new Intent(context, ClassRegistration.class));
                    break;
                case 4:
                    startApplication("com.google.android.gm");
                    break;
                case 5:
                    context.startActivity(new Intent(context, MapView.class));
                    break;
                case 6:
                    context.startActivity(new Intent(context, BillPay.class));
                    break;
                case 7:
                    startApplication("com.blackboard.android");
                    break;
            }

        }
    }

    public MenuViewAdapter(Context context, List<MenuItem> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View layoutView = inflater.inflate(R.layout.card_view, parent, false);
        ViewHolder rcv = new ViewHolder(layoutView, itemList);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.menuOption.setText(itemList.get(position).getName());
        holder.optionPhoto.setImageResource(itemList.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public void startApplication(String packageName){
        Toast toast;
        try
        {

            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            List<ResolveInfo> resolveInfoList = context.getPackageManager().queryIntentActivities(intent, 0);

            for(ResolveInfo info : resolveInfoList)
                if(info.activityInfo.packageName.equalsIgnoreCase(packageName))
                {
                    launchApp(info.activityInfo.packageName, info.activityInfo.name);
                    return;
                }
        }
        catch(Exception e)
        {
            if(packageName.equals("com.blackboard.android")){
                Toast.makeText(context, "Please make sure that Blackboard is installed on your device", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(context, "An error occurred", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void launchApp(String packageName, String name)
    {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName(packageName, name));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
