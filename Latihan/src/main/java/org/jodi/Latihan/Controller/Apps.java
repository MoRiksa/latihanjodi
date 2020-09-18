/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jodi.Latihan.Controller;

import Impl.KoneksiJDBC;
import Model.Kabupaten;
import Model.Kecamatan;
import Model.Provinsi;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Jodi
 */
@Controller
public class Apps {
    @Autowired
    private KoneksiJDBC koneksiJDBC;
    
    @GetMapping(path = "/")
    public String HomeUI(ModelMap model){
        model.put("nama", "Selamat Datang");
    return "home";
    }
    
     @GetMapping(path = "/about")
    public ModelAndView AboutUI(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("ab", "Selamat Datang di About");
    return new ModelAndView("about",map);
    }
    
    @GetMapping(path = "/listprovinsi")
    public ModelAndView listProvinsi(){
        Map<String,List<Provinsi>> map = new HashMap<>();
        map.put("listprov", koneksiJDBC.getListProvinsi());
    return new ModelAndView("listprovinsi",map);
    }
    
    @GetMapping(path = "/listprovinsijson")
    public ResponseEntity<List<Provinsi>> ListProvinsiJSON(){
        return ResponseEntity.ok().body(koneksiJDBC.getListProvinsi());
    }
    
    @GetMapping(path = "/listkecamatanjson")
    public ResponseEntity<List<Kecamatan>> ListKecamatanJSON(){
        return ResponseEntity.ok().body(koneksiJDBC.getListKecamatan());
    }
    
    @GetMapping(path = "/listkabjson")
    public ResponseEntity<List<Kabupaten>> ListKabupatenJSON(){
        return ResponseEntity.ok().body(koneksiJDBC.getListKabupatenn());
    }
    @GetMapping(path = "/listkabjson/{id}")
    public ResponseEntity<List<Kabupaten>> ListKabupatenJSON(@PathVariable("id") int id ){
        return ResponseEntity.ok().body(koneksiJDBC.getListKabupatenn(id));
    }
    
    @GetMapping(path = "/listkecamatan")
    public ModelAndView listKecamatan(){
        Map<String,List<Kecamatan>> map = new HashMap<>();
        map.put("listkec", koneksiJDBC.getListKecamatan());
    return new ModelAndView("listkecamatan",map);
    }
    
    @GetMapping(path = "/kecamatandetail/{id}")
    public String kecamatanDetail(@PathVariable("id") Integer idx, Model model){
//        Map<String, Provinsi> map = new HashMap<>();
//        map.put("prop", koneksiJDBC.getProvinsiById(idx).get());
//    return new ModelAndView("detailpropinsi",map);

    model.addAttribute("kec",koneksiJDBC.getKecamatanById(idx).get());
    return "formkecamatan";
    }
    
    @GetMapping(path = "/provinsidetail/{id}")
    public String provinsiDetail(@PathVariable("id") Integer idx, Model model){
//        Map<String, Provinsi> map = new HashMap<>();
//        map.put("prop", koneksiJDBC.getProvinsiById(idx).get());
//    return new ModelAndView("detailpropinsi",map);

    model.addAttribute("provinsi",koneksiJDBC.getProvinsiById(idx).get());
    return "formprovinsi";
    }
    
    @GetMapping(path = "/kabupatendetail/{id}")
    public String kabupatenDetail(@PathVariable("id") Integer idx, Model model){
//        Map<String, Provinsi> map = new HashMap<>();
//        map.put("prop", koneksiJDBC.getProvinsiById(idx).get());
//    return new ModelAndView("detailpropinsi",map);

    model.addAttribute("provinsi",koneksiJDBC.getListProvinsi());
        

    model.addAttribute("kabupaten",koneksiJDBC.getKabupatenById(idx).get());
    return "formkabupaten";
    }
    
    @GetMapping(path = "/listkabupaten")
    public ModelAndView listKabupaten(){
        Map<String,List<Kabupaten>> map = new HashMap<>();
        map.put("listkab", koneksiJDBC.getListKabupatenn());
    return new ModelAndView("listkabupaten",map);
    }
    
   
      
    @PostMapping(path = "/savekab")
    public String saveProv(@Valid @ModelAttribute Kabupaten kabupaten,BindingResult result){
        if (result.hasErrors()) {
            return "formkabupaten";
            
        }
        koneksiJDBC.updateKabupaten(kabupaten);
    return "redirect:listkabupaten";
    }
    
    @PostMapping(path = "/savekec")
    public String saveKec(@ModelAttribute Kecamatan kecamatan,BindingResult result){
        if (result.hasErrors()) {
            return "formkecamatan";
            
        }
        koneksiJDBC.insertKecamatan(kecamatan);
    return "redirect:listkecamatan";
    }
    
    @PostMapping(path = "/editkec")
    public String editKec(@ModelAttribute Kecamatan kecamatan,BindingResult result){
        if (result.hasErrors()) {
            return "formkecamatan";
            
        }
        koneksiJDBC.updateKecamatan(kecamatan);
    return "redirect:listkecamatan";
    }
    
    @GetMapping(path = "/formprovinsi")
    public String formProvinsi(Model model){
        model.addAttribute("provinsi",new Provinsi());
    return "formprovinsi";
    }
    
    @GetMapping(path = "/formkecamatant")
    public String formKecamatant(Model model){
        model.addAttribute("kecamatan",new Kecamatan());
    return "formkecamatant";
    }
    
    @PostMapping(path = "/saveprov")
    public String saveProv(@Valid @ModelAttribute Provinsi provinsi,BindingResult result){
        if (result.hasErrors()) {
            return "formprovinsi";
            
        }
        koneksiJDBC.insertProvinsi(provinsi);
    return "redirect:listprovinsi";
    }
    
    @GetMapping(path = "/listkabupatencari")
    public ModelAndView listKabupatenCari(@RequestParam(name = "nama")Optional<String> namaParam){
        Map<String,List<Kabupaten>> map = new HashMap<>();
        map.put("listkab", koneksiJDBC.getListKabupaten(namaParam));
    return new ModelAndView("listkabupaten",map);
    }
    
    @GetMapping(path = "/listkecamatancari")
    public ModelAndView listKecamatanCari(@RequestParam(name = "nama")Optional<String> namaParam){
        Map<String,List<Kecamatan>> map = new HashMap<>();
        map.put("listkec", koneksiJDBC.getListKecamatann(namaParam));
    return new ModelAndView("listkecamatan",map);
    }
    

}
