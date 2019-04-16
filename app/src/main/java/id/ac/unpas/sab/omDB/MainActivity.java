package id.ac.unpas.sab.omDB;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import id.ac.unpas.sab.omDB.data.ResponseOMDB;
import id.ac.unpas.sab.omDB.rest.APIClient;
import id.ac.unpas.sab.omDB.rest.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listAkademik;
    LinearLayoutManager linearLayoutManager;
    APIService api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listAkademik = findViewById(R.id.listAkademik);
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        listAkademik.setLayoutManager(linearLayoutManager);
//        new GetAkademikAsyncTask().execute();

        api = APIClient.getClient().create(APIService.class);
        getFilm();


    }

    private void getFilm(){
        Call<ResponseOMDB> filmCall = api.getFilm();
        filmCall.enqueue(new Callback<ResponseOMDB>() {
            @Override
            public void onResponse(Call<ResponseOMDB> call, Response<ResponseOMDB> response) {
                if (response.body().getSearch() != null) {
                    Log.i("data", response.body().getSearch().toString());
                    listAkademik.setAdapter(new FilmAdapter(response.body().getSearch(), MainActivity.this));
                }
            }

            @Override
            public void onFailure(Call<ResponseOMDB> call, Throwable t) {

            }
        });
    }
    
//    private class GetAkademikAsyncTask extends AsyncTask<String, String, String>{
//        ProgressDialog progressDialog;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progressDialog = new ProgressDialog(MainActivity.this);
//            progressDialog.setMessage("Loading......");
//            progressDialog.setCancelable(false);
//            progressDialog.show();
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            String respon = "";
//            try {
//                String url = "http://sab.if-unpas.org/pertemuan_07/akademik.php?action=get_akademik";
//                respon = CustomeHttp.executeHttpGet(url);
//            } catch (Exception e){
//                respon = e.toString();
//            }
//
//            return respon;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            progressDialog.dismiss();
//            try {
//                Log.e("masuk", "RESPON result -> "+result);
//                JSONObject object = new JSONObject(result);
//                ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
//                if(object.getString("success").equalsIgnoreCase("1")){
//                    JSONArray array = object.getJSONArray("data");
//                    HashMap<String, String> map;
//                    for (int i = 0; i < array.length(); i++){
//                        JSONObject jsonObject = array.getJSONObject(i);
//                        map = new HashMap<>();
//                        map.put("img_url", jsonObject.getString("img_url"));
//                        map.put("singkatan", jsonObject.getString("singkatan"));
//                        map.put("nama", jsonObject.getString("nama"));
//                        map.put("url", jsonObject.getString("url"));
//                        arrayList.add(map);
//                    }
//                }
////                listAkademik.setAdapter(new FilmAdapter(arrayList, MainActivity.this));
//            } catch (Exception e){
//                Log.e("masuk", "-> "+e.getMessage());
//            }
//        }
//    }
}

