    $(document).ready (function() {
        $.getJSON("/listprovinsijson",function (data){
        let cekBoxDiv = '';
            $.each(data, function (key,val){
                cekBoxDiv += "<option value="+val.idProvinsi+">"+val.namaProvinsi+"</option>";
            });
        $("#dataprovinsi").html(cekBoxDiv);
        });
    })
    
        $(document).ready (function() {
        $.getJSON("/listkabjson",function (data){
        let cekBoxDiv = '';
            $.each(data, function (key,val){
                cekBoxDiv += "<option value="+val.kodeBPS+">"+val.namaKabupaten+"</option>";
            });
        $("#datakab").html(cekBoxDiv);
        });
    })
    
    $(document).ready (function() {
        $.getJSON("/listkecmatanjson",function (data){
        let cekBoxDiv = '';
            $.each(data, function (key,val){
                cekBoxDiv += "<option value="+val.kodeBPS+">"+val.namaKecamatan+"</option>";
            });
        $("#datakec").html(cekBoxDiv);
        });
    })
    
    function getKab(id){
$(document).ready (function() {
        $.getJSON("/listkabjson/"+id,function (data){
        let cekBoxDiv = '';
            $.each(data, function (key,val){
                cekBoxDiv += "<option value="+val.kodeBPS+">"+val.namaKabupaten+"</option>";
            });
        $("#datakabup").html(cekBoxDiv);
        });
    })
}