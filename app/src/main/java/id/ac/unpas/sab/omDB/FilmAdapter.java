package id.ac.unpas.sab.omDB;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.ac.unpas.sab.omDB.data.SearchItem;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {

//    private ArrayList<HashMap<String, String>> postList;
    private List<SearchItem> postList;
    private Context context;

//    public FilmAdapter(ArrayList<HashMap<String, String>> postList, MainActivity mainActivity){
//        this.postList = postList;
//        this.mainActivity = mainActivity;
//    }

    public FilmAdapter(List<SearchItem> postList, Context context){
        this.postList = postList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_akademik, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(postList.get(position).getPoster()).into(holder.imgPoster);
        holder.judul.setText(postList.get(position).getTitle());
        holder.tahun.setText(postList.get(position).getYear());
        holder.tipe.setText(postList.get(position).getType());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), WebViewActivity.class);
                i.putExtra("id", postList.get(position).getImdbID());
                v.getContext().startActivity(i);
            }
        });



    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imgPoster;
        TextView judul;
        TextView tahun;
        TextView tipe;

        public ViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.cardLayout);
            imgPoster = view.findViewById(R.id.imgPoster);
            judul = view.findViewById(R.id.textJudul);
            tahun = view.findViewById(R.id.textTahun);
            tipe = view.findViewById(R.id.textTipe);
        }
    }
}
