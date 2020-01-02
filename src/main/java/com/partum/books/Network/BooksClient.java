package com.partum.books.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BooksClient {
    private static final String BaseUrl = "http://128.199.155.35";

    private static BooksClient minstance;
    private Retrofit mretrofit;

    private BooksClient() {
        mretrofit = new Retrofit.Builder().
                baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized BooksClient getInstance() {
        if (minstance == null) {
            minstance = new BooksClient();
        }
        return minstance;
    }

    public BookServices getapi() {
        return mretrofit.create(BookServices.class);
    }
}
