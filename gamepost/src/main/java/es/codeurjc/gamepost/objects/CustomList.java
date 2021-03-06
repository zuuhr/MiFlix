package es.codeurjc.gamepost.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;

@Entity
public class CustomList<T> implements Serializable{

    //#region Variables

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    
    String name;
    @ManyToMany(targetEntity = ListElement.class) List<T> elements;

    @ManyToOne User user;
    //#endregion

    //#region Constructor

    public CustomList(){}
    
    public CustomList(String name, User user){
        this.name = name;
        this.user = user;
        elements = new ArrayList<T>();
    }

    //#endregion

    //#region Methods

    public boolean addElement(T element){
        if(elements.contains(element))
            return false;

        return elements.add(element);
    }

    public boolean removeElement(T element){
        return elements.remove(element);
    }

    public T getElement(int index){
        return elements.get(index);
    }
    
    public List<T> getAllElements(){
        return elements;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public boolean contains(T element){
        return elements.contains(element);
    }
    //#endregion
}