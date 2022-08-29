package com.example.tramphatsongdidong.controller;

import com.example.tramphatsongdidong.entity.LichSu;
import com.example.tramphatsongdidong.repository.LichSuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DsLichSu {
    @Autowired
    private LichSuRepository lichSuRepository;
    @RequestMapping(value ="/lichSuView" ,method = RequestMethod.GET)
    public String getLS(Model model){

        List<LichSu> lichSuList=lichSuRepository.findAll();
        model.addAttribute("lichsus", lichSuList);
        return "lichsuview";
    }
    @RequestMapping(value ="/searchByid" ,method = RequestMethod.POST)
    public String searchByIdActive(@RequestParam("idActive") Long id,Model model){
//      Long idu= Long.valueOf(id);
        List<LichSu> lichSuList=lichSuRepository.findByIdActive(id);
        model.addAttribute("lichsus", lichSuList);
        return "searchLichSu";
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
//        try {
//            lichSuRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
