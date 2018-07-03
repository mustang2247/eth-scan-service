package com.open.coinnews.app.service;

import com.open.coinnews.app.model.LockPlanOperationsLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 锁仓计操作记录
 */
public interface ILockPlanOperationsLogService extends JpaRepository<LockPlanOperationsLog, Integer>, JpaSpecificationExecutor<LockPlanOperationsLog> {

    LockPlanOperationsLog findByTxHash(String txhash);

}
