package com.partum.books.Network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BookServices {


    @GET("/api/freeBooks")
    Call<ResponseBody> getfreeBooks();

    @GET("/api/paidBooks")
    Call<ResponseBody> getPaidBooks();
}


