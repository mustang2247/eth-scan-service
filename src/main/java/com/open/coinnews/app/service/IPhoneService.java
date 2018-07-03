package com.open.coinnews.app.service;

import com.open.coinnews.app.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author guominghai
 * @Description:
 * @Date Created in 14:18 2018/5/25
 */
public interface IPhoneService extends JpaRepository<Phone, Integer>, JpaSpecificationExecutor<Phone> {

    @Query("SELECT count(a) FROM Phone a WHERE a.phone=?1")
    Integer findByPhone(String phone);

    @Query("SELECT a.phone FROM Phone a")
    List<String> findAllPhone();

}
