package com.example.kriptoparaapp.service;

import com.example.kriptoparaapp.model.CryptoModel;
import io.reactivex.Observable;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {

    @GET("prices?key=3342bb58879676fbd308ba7618594c53")
    Observable<List<CryptoModel>> getData();

    //Call<List<CryptoModel>> getData();

}
