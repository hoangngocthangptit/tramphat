package com.example.tramphatsongdidong.controller;

import com.example.tramphatsongdidong.entity.LichSu;
import com.example.tramphatsongdidong.entity.Profiles;
import com.example.tramphatsongdidong.entity.TramPhat;
import com.example.tramphatsongdidong.repository.LichSuRepository;
import com.example.tramphatsongdidong.repository.ProfilesRepository;
import com.example.tramphatsongdidong.repository.TramRepository;
import com.example.tramphatsongdidong.service.TramPhatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
public class TramController {
    @Autowired
    private LichSuRepository lichSuRepository;
    @Autowired
    private ProfilesRepository profilesRepository;
    @Autowired
    private TramPhatService tramPhatService;
    @Autowired
    private TramRepository tramRepository;
    public String index(Model model) {
        return "index";
    }
    @RequestMapping(value ="/tramphatview" ,method = RequestMethod.GET)
    public String getBrand(Model model){

        List<TramPhat> brands=tramRepository.findAll();
        model.addAttribute("tramphats", brands);
        return "tramview";
    }
    @PostMapping("/saveTramPhat")
    public  String addBrand( TramPhat brand){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd ");
        LocalDateTime current = LocalDateTime.now();
        String date=current.format(formatter);
        LichSu ls=new LichSu();
        brand.setIdBrand(2L);
        ls.setIdUserName(1L);
        ls.setNgayEdit(date);
        ls.setIdActive(1L);
        brand.setNgayTao(date);
        lichSuRepository.save(ls);
        tramRepository.save(brand);
        return "redirect:/tramphatview";
    }
    @RequestMapping(value = "addtram")
    public String addUser(Model model) {
        model.addAttribute("tramphat", new TramPhat());
        return "inserttram";
    }

    @RequestMapping(value = "/saveedittram")
    public String saveEditUser(TramPhat brand) {


        TramPhat brandGet=brand;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd ");
        LocalDateTime current = LocalDateTime.now();
        String date=current.format(formatter);
        LichSu ls=new LichSu();
        ls.setIdUserName(1L);
        ls.setNgayEdit(date);
        ls.setIdActive(2L);
        lichSuRepository.save(ls);
        tramRepository.save(brandGet);
        return "redirect:/tramphatview";
    }
    @RequestMapping(value = "/editTramPhat", method = RequestMethod.GET)
    public String editUser(@RequestParam("id") Long userId, Model model) {
        Optional<TramPhat> brandEdit = tramRepository.findById(userId);
        brandEdit.ifPresent(tramPhat -> model.addAttribute("tramPhat",tramPhat));
        return "tramupdate";
    }
    @RequestMapping(value = "/deletetram", method = RequestMethod.GET)
    public String delete(@RequestParam("id") Long id, Model model) {
        tramRepository.deleteById(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd ");
        LocalDateTime current = LocalDateTime.now();
        String date=current.format(formatter);
        LichSu ls=new LichSu();
        ls.setIdUserName(1L);
        ls.setNgayEdit(date);
        ls.setIdActive(3L);
        lichSuRepository.save(ls);
        return "redirect:/tramphatview";

    }
    @PostMapping("/addProfileTram")
    public  String addProfile(@RequestParam(value="idTram", required=true) Long idTram,
                                              @RequestParam(value="idProfile", required=true) Long idProfile) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime current = LocalDateTime.now();
        String date=current.format(formatter);
        LichSu ls=new LichSu();
        ls.setIdActive(1L);
        ls.setNgayEdit(date);
        ls.setIdUserName(1L);
        lichSuRepository.save(ls);
        String ok=tramPhatService.addProfile(idTram,idProfile);
        return "index";


    }
    @RequestMapping(value = "/addTram",method = RequestMethod.GET)
    public String GiaoDienAdd(@RequestParam("id") Long id, Model model) {
        List<Profiles>profiles=profilesRepository.findAll();
        model.addAttribute("profiles", profiles);

        model.addAttribute("idTram",id);
        return "addTram";
    }


//    @PutMapping("/edit/{id}")
//    public ResponseEntity<TramPhat> updateTutorial(@PathVariable("id") long id, @RequestBody TramPhat tramPhat) {
//        Optional<TramPhat> tramData = tramRepository.findById(id);
//        if (tramData.isPresent()) {
//            TramPhat tramGet=tramData.get();
//            tramGet.setTrangThai(tramPhat.getTrangThai());
//            tramGet.setDiaChi(tramPhat.getDiaChi());
//            tramGet.setNgayTao(tramPhat.getNgayTao());
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            LocalDateTime current = LocalDateTime.now();
//            String date=current.format(formatter);
//            LichSu ls=new LichSu();
//            ls.setIdActive(2L);
//            ls.setNgayEdit(date);
//            ls.setIdUserName(1L);
//            lichSuRepository.save(ls);
//            return new ResponseEntity<>(tramRepository.save(tramGet), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
//        try {
//            tramRepository.deleteById(id);
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd ");
//            LocalDateTime current = LocalDateTime.now();
//            String date=current.format(formatter).toString();
//            LichSu ls=new LichSu();
//            ls.setIdUserName(1L);
//            ls.setNgayEdit(date);
//            ls.setIdActive(3L);
//            lichSuRepository.save(ls);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }





}
