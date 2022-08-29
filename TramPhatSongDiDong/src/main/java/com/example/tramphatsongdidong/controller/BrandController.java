package com.example.tramphatsongdidong.controller;

import com.example.tramphatsongdidong.entity.Brand;
import com.example.tramphatsongdidong.entity.LichSu;
import com.example.tramphatsongdidong.repository.BrandRepository;
import com.example.tramphatsongdidong.repository.LichSuRepository;
import com.example.tramphatsongdidong.repository.ProfilesRepository;
import com.example.tramphatsongdidong.repository.TramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
public class BrandController {
    @Autowired
    private LichSuRepository lichSuRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ProfilesRepository profilesRepository;
    @Autowired
    private TramRepository tramRepository;
    public String index(Model model) {
        return "index";
    }
    @RequestMapping(value ="/brandview" ,method = RequestMethod.GET)
    public String getBrand(Model model){

        List<Brand> brands=brandRepository.findAll();
        model.addAttribute("brands", brands);
        return "brandview";
    }
    @PostMapping("/save")
    public  String addBrand( Brand brand){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd ");
        LocalDateTime current = LocalDateTime.now();
        String date=current.format(formatter);
        LichSu ls=new LichSu();
        ls.setIdUserName(1L);
        brand.setNgayTao(date);
        ls.setNgayEdit(date);
        ls.setIdActive(1L);
        lichSuRepository.save(ls);
        brandRepository.save(brand);
        return "redirect:/brandview";
    }
    @RequestMapping(value = "addbrand")
    public String addUser(Model model) {
        model.addAttribute("brand", new Brand());
        return "insert";
    }

    @RequestMapping(value = "/saveeditbrand")
    public String saveEditUser(Brand brand) {


            Brand brandGet=brand;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd ");
            LocalDateTime current = LocalDateTime.now();
            String date=current.format(formatter);
            brandGet.setNgayCapNhat(date);

            LichSu ls=new LichSu();
            ls.setIdUserName(1L);
            ls.setNgayEdit(date);
            ls.setIdActive(2L);
            lichSuRepository.save(ls);
            brandRepository.save(brandGet);
           return "redirect:/brandview";
    }
    @RequestMapping(value = "/editbrand", method = RequestMethod.GET)
    public String editUser(@RequestParam("id") Long userId, Model model) {
        Optional<Brand> brandEdit = brandRepository.findById(userId);
        brandEdit.ifPresent(brand -> model.addAttribute("brand",brand));
        return "brandupdate";
    }
@RequestMapping(value = "/deletebrand", method = RequestMethod.GET)
public String delete(@RequestParam("id") Long id, Model model) {
            brandRepository.deleteById(id);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd ");
            LocalDateTime current = LocalDateTime.now();
            String date=current.format(formatter);
            LichSu ls=new LichSu();
            ls.setIdUserName(1L);
            ls.setNgayEdit(date);
            ls.setIdActive(3L);
            lichSuRepository.save(ls);
            return "redirect:/brandview";

    }

}
