package com.example.tramphatsongdidong.service;

import com.example.tramphatsongdidong.entity.Profiles;
import com.example.tramphatsongdidong.entity.TramPhat;
import com.example.tramphatsongdidong.repository.ProfilesRepository;
import com.example.tramphatsongdidong.repository.TramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TramPhatImpl implements TramPhatService {
    @Autowired
    private ProfilesRepository profilesRepository;
    @Autowired
    private TramRepository tramRepository;
    @Override
    public String addProfile(Long idTram, Long idProfile) {
        String mes="";
        Profiles profiles=profilesRepository.findById(idProfile).get();

        if(profiles==null){
            mes="idProfile ko tồn tại";
        }
        else {
            TramPhat tram=tramRepository.findById(idTram).get();
            List<Profiles> list=tram.getListProfiles();
            list.add(profiles);
            tram.setListProfiles(list);
            tramRepository.save(tram);
            mes="đã thêm thành công profiles";

        }
        return  mes;
    }
}
