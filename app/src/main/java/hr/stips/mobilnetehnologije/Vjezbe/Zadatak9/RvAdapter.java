package hr.stips.mobilnetehnologije.Vjezbe.Zadatak9;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import hr.stips.mobilnetehnologije.R;


public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvViewHolder> {

    Context context;
    ArrayList<Rvdata> rvdatas;

    public RvAdapter(Context context, ArrayList<Rvdata> rvdatas){
        this.context = context;
        this.rvdatas = rvdatas;//podaci dobiveni iz JSON-a
    }

    View view;
    @Override
    public RvAdapter.RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.from(parent.getContext()).inflate(R.layout.p8_rw_item, parent, false);//instanca View klase koj predstavlja handler na inflateani view
        RvViewHolder rvViewHolder = new RvViewHolder(view);//rwHolder sadrzi propertije koji su handleri na view-ove na inflateanom xml-u
        return rvViewHolder;//ovo je celija - pogledati deklaraciju klase RvViewHolder
    }

    @Override
    public void onBindViewHolder(RvAdapter.RvViewHolder holder, final int position) {//stavljanje podataka na view holder
        final Rvdata rvdata = rvdatas.get(position);
        holder.itemName.setText(rvdata.getName());
        final String imgUrl = rvdata.getImg();
        Glide.with(context).load(imgUrl).thumbnail(0.5f).into(holder.itemImg);

        //da bi bilo klikabilno
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent intent = new Intent(context, Zadatak9_NewActivity.class);
                intent.putExtra("Position", imgUrl);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }


        });



    }


    @Override
    public int getItemCount() {
        return rvdatas.size();
    }

    //deklaracija kalse RvViewHolder
    public class  RvViewHolder extends RecyclerView.ViewHolder{
        TextView itemName;
        ImageView itemImg;
        ConstraintLayout linearLayout;

        public  RvViewHolder(View itemView){
            super(itemView);

            itemName = itemView.findViewById(R.id.article_name);
            itemImg = itemView.findViewById(R.id.item_img);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }

    }
}
