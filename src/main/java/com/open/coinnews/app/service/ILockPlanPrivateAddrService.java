package com.open.coinnews.app.service;

import com.open.coinnews.app.model.LockPlanPrivateAddr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 锁仓计划地址表
 */
public interface ILockPlanPrivateAddrService extends JpaRepository<LockPlanPrivateAddr, Integer>, JpaSpecificationExecutor<LockPlanPrivateAddr> {

}
