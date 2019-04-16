package id.ac.unpas.sab.omDB;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;

import java.net.MalformedURLException;
import java.net.URL;

import id.ac.unpas.sab.omDB.data.ResponseFilm;
import id.ac.unpas.sab.omDB.rest.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class WebViewActivity extends AppCompatActivity {
    ImageView poster;
    TextView judul;
    TextView tahun;
    TextView genre;
    TextView actor;
    TextView plot;
    APIService api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        poster = findViewById(R.id.imagePosterDetail);
        judul = findViewById(R.id.textJudul);
        tahun = findViewById(R.id.textTahun);
        genre = findViewById(R.id.textGenre);
        actor = findViewById(R.id.textActor);
        plot = findViewById(R.id.textPlot);
        getFilmDetails();

    }

    private void getFilmDetails(){
        String id = getIntent().getStringExtra("id");
        Log.e("id", id);
        Call<ResponseFilm> filmCall = api.getFilmDetails("id");
        filmCall.enqueue(new Callback<ResponseFilm>() {
            @Override
            public void onResponse(Call<ResponseFilm> call, Response<ResponseFilm> response) {
                if (response.body().getResponse().equalsIgnoreCase("true")) {
                    Log.i("data", response.body().toString());
                    try {
                        URL url = new URL(response.body().getPoster());
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setDoInput(true);
                        conn.connect();
                        InputStream is = conn.getInputStream();
                        poster.setImageBitmap(BitmapFactory.decodeStream(is));
                        judul.setText(response.body().getTitle());
                        tahun.setText(response.body().getYear());
                        genre.setText(response.body().getGenre());
                        actor.setText(response.body().getActors());
                        plot.setText(response.body().getPlot());
                    } catch (MalformedURLException e) {

                    } catch (Exception e){

                    }



                }
            }

            @Override
            public void onFailure(Call<ResponseFilm> call, Throwable t) {

            }
        });
    }
}