package com.open.coinnews.app.service;

import com.open.coinnews.app.model.TokenTranLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 18T变化追踪
 */
public interface ITokenTranLogService extends JpaRepository<TokenTranLog, Integer>, JpaSpecificationExecutor<TokenTranLog> {

    TokenTranLog findByTxHash(String txhash);
    TokenTranLog findByToToken(String toToken);
}
