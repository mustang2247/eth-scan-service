package com.open.coinnews.app.service;

import com.open.coinnews.app.model.BigBossAddr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 大户划地址列表
 */
public interface IBigBossAddrService extends JpaRepository<BigBossAddr, Integer>, JpaSpecificationExecutor<BigBossAddr> {

    BigBossAddr findByPrivateTokenAdd(String addr);

}
