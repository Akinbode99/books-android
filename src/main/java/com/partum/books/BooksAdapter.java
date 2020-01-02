package com.partum.books;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder>{


    private ArrayList<BookModel> datalist;
    private View.OnClickListener mClickListener;



    public  BooksAdapter(ArrayList<BookModel> datalist){
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.book,viewGroup, false);
        RecyclerView.ViewHolder holder = new BooksViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onClick(view);
            }
        });
        return (BooksViewHolder)holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder booksViewHolder, int i) {


            booksViewHolder.price.setText("N "+datalist.get(i).getPrice());
            booksViewHolder.author.setText(datalist.get(i).getAuthorName());
            booksViewHolder.date.setText(reFormatDate(datalist.get(i).getCreationDate()));
            booksViewHolder.title.setText(datalist.get(i).getBookTitle());
           Picasso.get().load(datalist.get(i).getPhotoUrl()).into(booksViewHolder.bookCover);



    }

    @Override
    public int getItemCount() {
        return (datalist != null) ? datalist.size() : 0;
    }

    public class BooksViewHolder extends RecyclerView.ViewHolder {
        private TextView author, title, date, price;
        private ImageView bookCover;
        public BooksViewHolder(@NonNull View itemView) {
            super(itemView);
             author = itemView.findViewById(R.id.author);
             title= itemView.findViewById(R.id.book_title);
             date = (TextView) itemView.findViewById(R.id.publish_date);
             price = (TextView) itemView.findViewById(R.id.price);
             bookCover = itemView.findViewById(R.id.book_cover_image);

        }
    }


    public void setClickListener(View.OnClickListener callback) {
        mClickListener = callback;
    }


    public void clear() {
        datalist.clear();
        notifyDataSetChanged();
    }

    private String reFormatDate(String serverDate){
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
            DateFormat wantedformat = new SimpleDateFormat("MMM dd, yyyy HH:mm a");
            Date date = format.parse(serverDate);
            return wantedformat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

}

