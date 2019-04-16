package id.ac.unpas.sab.omDB.rest;

import id.ac.unpas.sab.omDB.data.ResponseFilm;
import id.ac.unpas.sab.omDB.data.ResponseOMDB;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
//    @GET("pertemuan_07/akademik.php?{action}")
    @GET("?apikey=955b461b&s=avengers")
    Call<ResponseOMDB> getFilm();

    @GET("?apikey=955b461b&i={params}")
    Call<ResponseFilm> getFilmDetails(@Query("params") String params);

}
