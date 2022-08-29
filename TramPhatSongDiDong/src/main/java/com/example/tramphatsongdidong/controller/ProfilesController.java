package com.example.tramphatsongdidong.controller;

import com.example.tramphatsongdidong.entity.*;
import com.example.tramphatsongdidong.repository.BrandRepository;
import com.example.tramphatsongdidong.repository.LichSuRepository;
import com.example.tramphatsongdidong.repository.ProfilesRepository;
import com.example.tramphatsongdidong.repository.TramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfilesController {
    @Autowired
    private LichSuRepository lichSuRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ProfilesRepository profilesRepository;
    @Autowired
    private TramRepository tramRepository;
    @RequestMapping(value ="/profilesview" ,method = RequestMethod.GET)
    public String getProfile(Model model){

        List<Profiles> brands=profilesRepository.findAll();
        model.addAttribute("profiles", brands);
        return "profilesView";
    }
    @PostMapping("/saveProfile")
    public  String addBrand( Profiles brand){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd ");
        LocalDateTime current = LocalDateTime.now();
        String date=current.format(formatter);
        LichSu ls=new LichSu();
        ls.setIdUserName(1L);
        ls.setNgayEdit(date);
        brand.setNgayTao(date);
        ls.setIdActive(1L);
        lichSuRepository.save(ls);
        profilesRepository.save(brand);
        return "redirect:/profilesview";
    }
    @RequestMapping(value = "addProfile")
    public String addUser(Model model) {
        model.addAttribute("profile", new Profiles());
        return "insertProfile";
    }

    @RequestMapping(value = "/saveeditprofile")
    public String saveEditUser(Profiles brand) {


        Profiles brandGet=brand;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd ");
        LocalDateTime current = LocalDateTime.now();
        String date=current.format(formatter);
        brandGet.setNgayCapNhat(date);
        LichSu ls=new LichSu();
        ls.setIdUserName(1L);
        ls.setNgayEdit(date);
        ls.setIdActive(2L);
        lichSuRepository.save(ls);
        profilesRepository.save(brandGet);
        return "redirect:/profilesView";
    }
    @RequestMapping(value = "/editprofile", method = RequestMethod.GET)
    public String editUser(@RequestParam("id") Long userId, Model model) {
        Optional<Profiles> brandEdit = profilesRepository.findById(userId);
        brandEdit.ifPresent(profile -> model.addAttribute("profile",profile));
        return "profileupdate";
    }
    @RequestMapping(value = "/deleteprofile", method = RequestMethod.GET)
    public String delete(@RequestParam("id") Long id, Model model) {
        profilesRepository.deleteById(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd ");
        LocalDateTime current = LocalDateTime.now();
        String date=current.format(formatter);
        LichSu ls=new LichSu();
        ls.setIdUserName(1L);
        ls.setNgayEdit(date);
        ls.setIdActive(3L);
        lichSuRepository.save(ls);
        return "redirect:/profilesview";

    }
    @PostMapping("/findName")
    public String findByName(@RequestParam (value = "name") String name,Model model){
        List<Profiles> profiles=profilesRepository.findProfile(name);
        model.addAttribute("profiles", profiles);
        return "profilesView";
    }

}
