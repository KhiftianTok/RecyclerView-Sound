package com.example.pest_repellant.repositories;

import android.arch.lifecycle.MutableLiveData;

import com.example.pest_repellant.model.Book;

import java.util.ArrayList;
import java.util.List;


/**
 * Singleton pattern
 */
public class BookRepository {

    private static BookRepository instance;
    private ArrayList<Book> dataSet = new ArrayList<>();

    public static BookRepository getInstance(){
        if(instance == null){
            instance = new BookRepository();
        }
        return instance;
    }


    // Pretend to get data from a webservice or online source
    public MutableLiveData<List<Book>> getBooks(){
        setBooks();
        MutableLiveData<List<Book>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setBooks(){
        dataSet.add(
                new Book("Nyamuk",
                        "Nyamuk adalah serangga tergolong dalam order Diptera; genera termasuk Anopheles, Culex, Psorophora, Ochlerotatus, Aedes, Sabethes, Wyeomyia, Culiseta, dan Haemagoggus untuk jumlah keseluruhan sekitar 35 genera yang merangkum 2700 spesies. Nyamuk mempunyai dua sayap bersisik, tubuh yang langsing, dan enam kaki panjang; antarspesies berbeda-beda tetapi jarang sekali melebihi 15 mm.Dalam bahasa Inggris, nyamuk dikenal sebagai \"Mosquito\", berasal dari sebuah kata dalam bahasa Spanyol atau bahasa Portugis yang berarti lalat kecil. Penggunaan kata Mosquito bermula sejak tahun 1583. Di Britania Raya nyamuk dikenal sebagai gnats.",
                        "https://banner2.kisspng.com/20180202/bjq/kisspng-mosquito-honey-bee-insect-clip-art-mosquito-5a74128683c7b9.7720269715175563585398.jpg"
                )
        );
        dataSet.add(
                new Book("Tikus",
                        "Tikus adalah  hewan pengerat biasa yang mudah dijumpai di rumah-rumah dengan ekor yang panjang dan pandai memanjat serta melompat. Hewan ini termasuk dalam subsuku Murinae dan berasal dari Asia. Namun, ia lalu menyebar ke Eropa melalui perdagangan sejak awal penanggalan modern dan betul-betul menyebar pada abad ke-6. Selanjutnya ia menyebar ke seluruh penjuru dunia. Tikus rumah pada masa kini cenderung tersebar di daerah yang lebih hangat karena di daerah dingin kalah bersaing dengan tikus got.",
                        "https://pngimage.net/wp-content/uploads/2018/06/raton-animado-png-2.png")
        );
    }
}







