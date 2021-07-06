package com.example.bookshop.Global;

import com.example.bookshop.Adapter.AllBookAdapter;
import com.example.bookshop.Model.Book;

import java.util.ArrayList;
import java.util.List;

public class Filter extends android.widget.Filter {


    AllBookAdapter searchAdapter;
    List<Book> data;

    public Filter(AllBookAdapter searchAdapter, List<Book> data) {
        this.searchAdapter = searchAdapter;
        this.data = data;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {

        FilterResults filterResults  = new FilterResults();

        if (charSequence != null && charSequence.length()>0){

            charSequence = charSequence.toString().toUpperCase();

            List<Book> data_filer = new ArrayList<>();

            for (int i=0 ; i<data.size() ; i++ ){

                if (data.get(i).getName().toUpperCase().contains(charSequence)){

                    data_filer.add(data.get(i));
                }

            }

            filterResults.count = data_filer.size();
            filterResults.values = data_filer;

        }else {

            filterResults.count = data.size();
            filterResults.values = data;
        }


        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {


        searchAdapter.data = (List<Book>)filterResults.values;
        searchAdapter.notifyDataSetChanged();


    }


}
