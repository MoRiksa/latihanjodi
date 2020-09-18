/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Jodi
 */
public class Kabupaten {
    private int kodeBPS;
    private String namaKabupaten;
    private int idProvinsi;
    private Provinsi provinsi;

    /**
     * @return the kodeBPS
     */
    public int getKodeBPS() {
        return kodeBPS;
    }

    /**
     * @param kodeBPS the kodeBPS to set
     */
    public void setKodeBPS(int kodeBPS) {
        this.kodeBPS = kodeBPS;
    }

    /**
     * @return the namaKabupaten
     */
    public String getNamaKabupaten() {
        return namaKabupaten;
    }

   
    public void setNamaKabupaten(String namaKabupaten) {
        this.namaKabupaten = namaKabupaten;
    }

    
    public Provinsi getProvinsi() {
        return provinsi;
    }

   
    public void setProvinsi(Provinsi provinsi) {
        this.provinsi = provinsi;
    }

    /**
     * @return the idProvinsi
     */
    public int getIdProvinsi() {
        return idProvinsi;
    }

    /**
     * @param idProvinsi the idProvinsi to set
     */
    public void setIdProvinsi(int idProvinsi) {
        this.idProvinsi = idProvinsi;
    }

   

    
}
