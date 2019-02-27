package com.example.pest_repellant.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.pest_repellant.model.Book;
import com.example.pest_repellant.repositories.BookRepository;

import java.util.List;


public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<Book>> mBooks;
    private BookRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if(mBooks != null){
            return;
        }
        mRepo = BookRepository.getInstance();
        mBooks = mRepo.getBooks();
    }

    public LiveData<List<Book>> getBooks(){
        return mBooks;
    }


    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
}
