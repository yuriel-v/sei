package io.sei.api.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Materia {
    private String[] materia;
    private String[] name;
    @Id @GeneratedValue
    private int id;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String[] getMateria() {
        return materia;
    }

    public void setMateria(String[] materia) {
        this.materia = materia;
    }
}
