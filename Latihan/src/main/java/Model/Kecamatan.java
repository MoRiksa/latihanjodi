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
public class Kecamatan {
    private int kodeBPS;
    private String namaKecamatan;
    private int idKabupaten;
    private Kabupaten kabupaten;
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
     * @return the namaKecamatan
     */
    public String getNamaKecamatan() {
        return namaKecamatan;
    }

    /**
     * @param namaKecamatan the namaKecamatan to set
     */
    public void setNamaKecamatan(String namaKecamatan) {
        this.namaKecamatan = namaKecamatan;
    }

    /**
     * @return the idKecamatan
     */
    public int getIdKabupaten() {
        return idKabupaten;
    }

    /**
     * @param idKecamatan the idKecamatan to set
     */
    public void setIdKabupaten(int idKabupaten) {
        this.idKabupaten = idKabupaten;
    }

    /**
     * @return the kabupaten
     */
    public Kabupaten getKabupaten() {
        return kabupaten;
    }

    /**
     * @param kabupaten the kabupaten to set
     */
    public void setKabupaten(Kabupaten kabupaten) {
        this.kabupaten = kabupaten;
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

    /**
     * @return the provinsi
     */
    public Provinsi getProvinsi() {
        return provinsi;
    }

    /**
     * @param provinsi the provinsi to set
     */
    public void setProvinsi(Provinsi provinsi) {
        this.provinsi = provinsi;
    }
}
