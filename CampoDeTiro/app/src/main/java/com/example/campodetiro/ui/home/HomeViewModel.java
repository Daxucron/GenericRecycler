package com.example.campodetiro.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.campodetiro.model.Producto;
import com.example.campodetiro.model.Usuario;
import com.example.campodetiro.ui.home.recycler.ExpandableItem;
import com.example.campodetiro.ui.home.recycler.Item;
import com.example.campodetiro.ui.home.recycler.SimpleItem;
import com.example.campodetiro.ui.home.recycler.transformer.ModelToItemTransformer;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {


    private final MutableLiveData<List<Item>> items;

    public HomeViewModel() {
        items = new MutableLiveData<>();
    }

    public LiveData<List<Item>> getItemsListLiveData() {
        return items;
    }

    public void loadData()
    {
        List<Item> itemsDummy = new ArrayList<>();
        itemsDummy.add(new SimpleItem("Texto 1"));
        List<Item> subItems = new ArrayList<>();
        subItems.add(new SimpleItem("Desplegado 2.1"));
        itemsDummy.add(new ExpandableItem("Texto 2", subItems));

        List<Producto> productList = new ArrayList<>();
        productList.add(new Producto("Melón","Un melón grande", 22.0,1));
        productList.add(new Producto("Jabón"," Jabópn pequeño grande", 2.0,1));
        Usuario user = new Usuario("1","Carlos","carlos@espinosa.com",30,productList);

        List<Item> createdItems = new ArrayList<>();
        createdItems.add(new ModelToItemTransformer(user).transform());
        items.setValue(createdItems);
    }
}