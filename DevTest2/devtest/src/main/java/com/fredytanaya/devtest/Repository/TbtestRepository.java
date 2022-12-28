package com.fredytanaya.devtest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fredytanaya.devtest.Model.Tbtest;

@Repository
public interface TbtestRepository extends JpaRepository<Tbtest, Long> {

}
