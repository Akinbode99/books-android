package com.partum.books;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.partum.books.Network.BooksClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FreeBooksFragment extends Fragment {

    private RecyclerView freebooksRecycler;
    private ArrayList<BookModel> Freebooks;
    private BooksAdapter booksAdapter;
    private SwipeRefreshLayout refreshlist;

    public FreeBooksFragment(){


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.freebooks,container,false);

        freebooksRecycler = view.findViewById(R.id.free_books_recycler);
        refreshlist = view.findViewById(R.id.refresh_free_books);
        Freebooks = new ArrayList<>();

        booksAdapter = new BooksAdapter(Freebooks);
        freebooksRecycler.setHasFixedSize(true);
        freebooksRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

       freebooksRecycler.setAdapter(booksAdapter);

        refreshlist.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light));

        refreshlist.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchBooks();
            }
        });

        return  view;
    }

    @Override
    public void onResume() {
        super.onResume();
      fetchBooks();
    }

    @Override
    public void onStart() {
        super.onStart();
      fetchBooks();
    }

    private void fetchBooks(){
        Call<ResponseBody> call = BooksClient.
                getInstance()
                .getapi().getfreeBooks();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    JSONArray books = null;
                    try {
                        books = new JSONArray(response.body().string());
                        Log.d("BOOKS", String.valueOf(books));
                        if(books != null && books.length() > 0){
                            Freebooks.clear();
                            for(int i =0; i < books.length() ; i++){
                                JSONObject book = books.getJSONObject(i);
                                String title = book.getString("title");
                                String date = book.getString("createdAt");
                                int amount = book.getInt("price");
                                String author = book.getString("author");
                                String photoUrl = book.getString("imageUrl");
                                BookModel freebooks = new BookModel(author,title,date,amount,photoUrl);
                                Freebooks.add(freebooks);
                            }
                        }
                        booksAdapter.notifyDataSetChanged();
                        refreshlist.setRefreshing(false);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    try {
                        JSONObject errorObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), errorObject.getString("message"),Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
              Log.d("REQUEST_FAILED",t.getMessage());
            }
        });
    }
}
