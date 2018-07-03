package com.open.coinnews.app.service;

import com.open.coinnews.app.model.WebConfigInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WebConfigInfoService extends JpaRepository<WebConfigInfo, Integer>, JpaSpecificationExecutor<WebConfigInfo> {


    @Query("SELECT a.value FROM WebConfigInfo a where a.id=1")
    Double findValue();

}
