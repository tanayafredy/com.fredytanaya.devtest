package com.fredytanaya.devtest.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fredytanaya.devtest.Model.Tbtest;
import com.fredytanaya.devtest.Repository.TbtestRepository;

@Service
public class TbtestService {

    @Autowired
    TbtestRepository tbtestRepository;

        public Tbtest CreateTest(Tbtest tb) {
            return tbtestRepository.save(tb);
        }
        public Tbtest GetTestById(Long id) {
            return tbtestRepository.findById(id).get();
        }
        public void DeleteTest(Long id) {
            tbtestRepository.deleteById(id);
        }
        public Tbtest UpdateTest(Long id, Tbtest tb) {
            Tbtest tbOld = tbtestRepository.findById(id).get();
            tbOld.setName(tb.getName()); 
            tbOld.setPhone(tb.getPhone()); 
            return tbtestRepository.save(tb);
        }
    
}
