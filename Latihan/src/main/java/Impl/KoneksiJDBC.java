package Impl;

import Model.Kabupaten;
import Model.Kecamatan;
import Model.Provinsi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class KoneksiJDBC {
    
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public void getNamaAnggota() {
    Connection conn = null;
        try {
            conn = dataSource.getConnection();
           // conn = 
           //    DriverManager.getConnection("jdbc:mysql://192.168.100.250/bmt_v1?" + 
             //          "user=root&password=passwordnyaRoot&serverTimezone=UTC"
               //        );
            
            String sql = "SELECT namaProvinsi, kodeBPS From provinsi ";
            PreparedStatement preparedStatement= 
                    conn.prepareStatement(sql);
            
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                System.err.println(result.getInt("kodeBPS")+"  :  "+result.getString("namaProvinsi"));
                
            }
                    
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        finally{
            try {
                if(conn !=null && !conn.isClosed()){
                conn.close();
        }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
       }
       
    
    public List<Provinsi> getListProvinsi(){
    String SQL = "SELECT namaProvinsi, kodeBPS as idProvinsi from provinsi";
    List<Provinsi> prop = jdbcTemplate.query(SQL,(BeanPropertyRowMapper.newInstance(Provinsi.class)));
        
    return prop;
    }
    
    public Optional<Provinsi> getProvinsiById(int id){
    String SQL = "SELECT namaProvinsi, kodeBPS as idProvinsi from provinsi where kodeBPS = ? ";
    Object param[] = {id};  
        try {
             return Optional.of(jdbcTemplate.queryForObject(SQL, param,BeanPropertyRowMapper.newInstance(Provinsi.class)));  
        } catch (Exception e) {
            return Optional.empty();
        }
    }
    
    public List<Kabupaten> getListKabupaten(Optional<String> nama){
    String SQL = "SELECT k.kodeBPS as idkab, k.namaKabupaten as namakab, p.namaProvinsi as namaprov, "
            + "p.kodeBPS as idprov FROM kabupaten k JOIN provinsi p on k.kodeProvinsi = p.kodeBPS where 1=1";
    
    
    Object[] param=new Object[1];
    if(nama.isPresent()){
        SQL += " and k.namaKabupaten like CONCAT('%',?,'%')";
        param[0] = nama.get();}
        
        return jdbcTemplate.query(SQL,param,(rs,rowNum)->{
        Kabupaten kab = new Kabupaten();
        kab.setKodeBPS(rs.getInt("idkab"));
        kab.setNamaKabupaten(rs.getString("namakab"));
        Provinsi prov = new Provinsi();
        prov.setIdProvinsi(rs.getInt("idprov"));
        prov.setNamaProvinsi(rs.getString("namaprov"));
        kab.setProvinsi(prov);
        return kab;
     });
//    }else{
//     return jdbcTemplate.query(SQL,(rs,rowNum)->{
//        Kabupaten kab = new Kabupaten();
//        kab.setKodeBPS(rs.getInt("idKab"));
//        kab.setNamaKabupaten(rs.getString("namaKab"));
//        Provinsi prov = new Provinsi();
//        prov.setIdProvinsi(rs.getInt("idProv"));
//        prov.setNamaProvinsi(rs.getString("namaProv"));
//        kab.setProvinsi(prov);
//        return kab;
//     });
    }
    public List<Kecamatan> getListKecamatann(Optional<String> nama){
    String SQL = "SELECT kec.kodeBps as idkec, kec.namaKecamatan as namakec, kab.namaKabupaten as namakab, "
            + "kab.kodeBPS as idkab, prov.namaProvinsi as namaprov, prov.kodeBPS as idprov "
            + "FROM kecamatan kec "
            + "JOIN kabupaten kab on kec.kodeKabupaten = kab.kodeBps  "
            + "JOIN provinsi prov on kab.kodeProvinsi = prov.kodeBPS "
            + "where 1=1";
    
    Object[] param=new Object[1];
    if(nama.isPresent()){
        SQL += " and kec.namaKecamatan like CONCAT('%', ?, '%')";
        param[0] = nama.get();}
        
    return jdbcTemplate.query(SQL,param, (rs,rowNum)->{
        Kecamatan kec = new Kecamatan();
        kec.setKodeBPS(rs.getInt("idkec"));
        kec.setNamaKecamatan(rs.getString("namakec"));
        Kabupaten kab = new Kabupaten();
        kab.setKodeBPS(rs.getInt("idkab"));
        kab.setNamaKabupaten(rs.getString("namakab"));
        kec.setKabupaten(kab);
        Provinsi prov = new Provinsi();
        prov.setIdProvinsi(rs.getInt("idprov"));
        prov.setNamaProvinsi(rs.getString("namaprov"));
        kec.setProvinsi(prov);
        return kec;
     });
    }
    
    public List<Kecamatan> getListKecamatan(){
    String SQL = "SELECT kec.kodeBps as idkec, kec.namaKecamatan as namakec, kab.namaKabupaten as namakab, "
            + "kab.kodeBPS as idkab, prov.namaProvinsi as namaprov, prov.kodeBPS as idprov "
            + "FROM kecamatan kec "
            + "JOIN kabupaten kab on kec.kodeKabupaten = kab.kodeBps  "
            + "JOIN provinsi prov on kab.kodeProvinsi = prov.kodeBPS "
            + "where 1=1";
        
    return jdbcTemplate.query(SQL,(rs,rowNum)->{
        Kecamatan kec = new Kecamatan();
        kec.setKodeBPS(rs.getInt("idkec"));
        kec.setNamaKecamatan(rs.getString("namakec"));
        Kabupaten kab = new Kabupaten();
        kab.setKodeBPS(rs.getInt("idkab"));
        kab.setNamaKabupaten(rs.getString("namakab"));
        kec.setKabupaten(kab);
        Provinsi prov = new Provinsi();
        prov.setIdProvinsi(rs.getInt("idprov"));
        prov.setNamaProvinsi(rs.getString("namaprov"));
        kec.setProvinsi(prov);
        return kec;
     });
    }
    
     public Optional<Kecamatan> getKecamatanById(int id){
    String SQL = "SELECT * from kecamatan where kodeBPS = ? ";
    Object param[] = {id};  
        try {
             return Optional.of(jdbcTemplate.queryForObject(SQL, param,BeanPropertyRowMapper.newInstance(Kecamatan.class)));  
        } catch (Exception e) {
            return Optional.empty();
        }
    }
                
    public List<Kabupaten> getListKabupatenn(int id){
    String SQL = "SELECT k.kodeBPS as idkab, k.namaKabupaten as namakab, p.namaProvinsi as namaprov, "
            + "p.kodeBPS as idprov FROM kabupaten k JOIN provinsi p on k.kodeProvinsi = p.kodeBPS where kodeProvinsi= ? ";
        Object[] param={id};
        return jdbcTemplate.query(SQL,param,(rs,rowNum)->{
        Kabupaten kab = new Kabupaten();
        kab.setKodeBPS(rs.getInt("idkab"));
        kab.setNamaKabupaten(rs.getString("namakab"));
        Provinsi prov = new Provinsi();
        prov.setIdProvinsi(rs.getInt("idprov"));
        prov.setNamaProvinsi(rs.getString("namaprov"));
        kab.setProvinsi(prov);
        return kab;
     });
    }
    public List<Kabupaten> getListKabupatenn(){
    String SQL = "SELECT k.kodeBPS as idkab, k.namaKabupaten as namakab, p.namaProvinsi as namaprov, "
            + "p.kodeBPS as idprov FROM kabupaten k JOIN provinsi p on k.kodeProvinsi = p.kodeBPS where 1=1";
        
    return jdbcTemplate.query(SQL,(rs,rowNum)->{
        Kabupaten kab = new Kabupaten();
        kab.setKodeBPS(rs.getInt("idkab"));
        kab.setNamaKabupaten(rs.getString("namakab"));
        Provinsi prov = new Provinsi();
        prov.setIdProvinsi(rs.getInt("idprov"));
        prov.setNamaProvinsi(rs.getString("namaprov"));
        kab.setProvinsi(prov);
        return kab;
     });
    }

//     return kab;
//    });
//}
    
    public Optional<Kabupaten> getKabupatenById(int id){
    String SQL = "SELECT kodeBPS, namaKabupaten, kodeProvinsi as idProvinsi from kabupaten where kodeBPS = ? ";
    Object param[] = {id};  
        try {
             return Optional.of(jdbcTemplate.queryForObject(SQL, param,BeanPropertyRowMapper.newInstance(Kabupaten.class)));  
        } catch (Exception e) {
            return Optional.empty();
        }
    }
    
    public void insertorupdateKabupaten(Kabupaten kabupaten){
    Optional<Kabupaten> data= getKabupatenById(kabupaten.getKodeBPS());
        if (data.isPresent()) {
            updateKabupaten(kabupaten);
        }else{
            insertKabupaten(kabupaten);
        }
    
  
    }
    public void insertKecamatan(Kecamatan kecamatan){
    String sql = "INSERT INTO kecamatan (kodeBPS,namaKecamatan,kodeKabupaten) VALUES (?,?,?)";
    Object param[] = {kecamatan.getKodeBPS(),kecamatan.getNamaKecamatan(),kecamatan.getIdKabupaten()};
    jdbcTemplate.update(sql,param);
    }
    
    public void updateKecamatan(Kecamatan kecamatan){
    String sql = "UPDATE kecamatan SET namaKecamatan=?,kodeKabupaten=? WHERE kodeBPS=?";
    Object param[] = {kecamatan.getNamaKecamatan(),kecamatan.getIdKabupaten(),kecamatan.getKodeBPS(),};
    jdbcTemplate.update(sql,param);
    }
    
    public void insertKabupaten(Kabupaten kabupaten){
    String sql = "INSERT INTO kabupaten (kodeBPS,namaKabupaten,kodeProvinsi) VALUES (?,?,?)";
    Object param[] = {kabupaten.getKodeBPS(),kabupaten.getNamaKabupaten(),kabupaten.getIdProvinsi()};
    jdbcTemplate.update(sql,param);
    }
    
    public void updateKabupaten(Kabupaten kabupaten){
    String sql = "UPDATE kabupaten SET namaKabupaten=?, kodeProvinsi=? WHERE kodeBPS=?";
    Object param[] = {kabupaten.getNamaKabupaten(),kabupaten.getIdProvinsi(),kabupaten.getKodeBPS()};
    jdbcTemplate.update(sql,param);
    }
    
    public void insertOrUpdateProvinsi(Provinsi provinsi){
    Optional<Kabupaten> data= getKabupatenById(provinsi.getIdProvinsi());
        if (data.isPresent()) {
            updateProvinsi(provinsi);
        }else{
            insertProvinsi(provinsi);
        }
    
  
    }
    
    public void insertProvinsi(Provinsi provinsi){
    String sql = "INSERT INTO provinsi (kodeBPS,namaProvinsi) VALUES (?,?)";
    Object param[] = {provinsi.getIdProvinsi(),provinsi.getNamaProvinsi()};
    jdbcTemplate.update(sql,param);
    }
    
    public void updateProvinsi(Provinsi provinsi){
    String sql = "UPDATE provinsi SET namaProvinsi=? WHERE kodeBPS=?";
    Object param[] = {provinsi.getNamaProvinsi(),provinsi.getIdProvinsi()};
    jdbcTemplate.update(sql,param);
    }
    
     public void deleteProvinsi(Provinsi provinsi){
    String sql = "DELETE FROM from provinsi WHERE kodeBPS=?";
    Object param[] = {provinsi.getIdProvinsi()};
    jdbcTemplate.update(sql,param);
    }

}

