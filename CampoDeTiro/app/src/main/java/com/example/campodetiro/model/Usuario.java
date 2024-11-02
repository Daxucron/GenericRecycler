package com.example.campodetiro.model;

import com.example.campodetiro.ui.home.recycler.transformer.RecyclerItem;
import com.example.campodetiro.ui.home.recycler.transformer.SubItem;

import java.util.ArrayList;
import java.util.List;
@RecyclerItem
public class Usuario {
    @SubItem(primary = true, name="id")
    private String usuarioId;
    @SubItem (name = "nombre", modifiable = true)
    private String name;
    @SubItem(ignore = true)
    private String email;
    private String apellido;
    @SubItem(modifiable = true)
    private int edad;
    private List<Producto> listaProductos;
    private String[] listaAbsurda = {"palabras","en","una","lista"};
    private List<Integer> listaInt;
    public Usuario() {}

    public Usuario(String usuarioId, String name, String email, int edad, List<Producto> listaProductos) {
        this.usuarioId = usuarioId;
        this.name = name;
        this.email = email;
        this.edad = edad;
        this.listaProductos = listaProductos;
        listaInt = new ArrayList<>();
        listaInt.add(1);
        listaInt.add(9);
        listaInt.add(9);
        listaInt.add(4);
        apellido = "Espinosa";
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public String[] getListaAbsurda() {
        return listaAbsurda;
    }

    public void setListaAbsurda(String[] listaAbsurda) {
        this.listaAbsurda = listaAbsurda;
    }

    public List<Integer> getListaInt() {
        return listaInt;
    }

    public void setListaInt(List<Integer> listaInt) {
        this.listaInt = listaInt;
    }
}
