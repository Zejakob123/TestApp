package testing123.zzz.testapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder>{

    List<kit> kitList;
    Context context;

    public RecycleViewAdapter(List<kit> kitList, Context context) {
        this.kitList = kitList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_kit, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_kitName.setText(kitList.get(position).getKitName());
        holder.tv_kitType.setText(kitList.get(position).getKitType());
        holder.tv_assWeek.setText(kitList.get(position).getAss_week());
        holder.iv_kitPic.setImageResource(kitList.get(position).getKit_pic());
        holder.parentLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //leave each huawei kit out here
                //send the control to the EditOneItem activity
                /*Intent intent = new Intent(context, AddEditOne.class);
                intent.putExtra("id", presidentList.get(position).getId());
                context.startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return kitList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_kitPic;
        TextView tv_kitName;
        TextView tv_kitType;
        TextView tv_assWeek;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_kitPic = itemView.findViewById(R.id.kit_picture);
            tv_kitName = itemView.findViewById(R.id.kit_name);
            tv_kitType = itemView.findViewById(R.id.kit_type);
            tv_assWeek = itemView.findViewById(R.id.ass_week);
            parentLayout = itemView.findViewById(R.id.oneLineKitLayout);
        }
    }
}
