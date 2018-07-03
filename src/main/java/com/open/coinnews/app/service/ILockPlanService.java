package com.open.coinnews.app.service;

import com.open.coinnews.app.model.LockPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 锁仓计划表
 */
public interface ILockPlanService extends JpaRepository<LockPlan, Integer>, JpaSpecificationExecutor<LockPlan> {

    LockPlan findByToken(String token);

    LockPlan findByTokenAndPrivateTokenAdd(String token, String privateToken);

}
