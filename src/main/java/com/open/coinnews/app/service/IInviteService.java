package com.open.coinnews.app.service;

import com.open.coinnews.app.model.Invite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface IInviteService extends JpaRepository<Invite, Integer>, JpaSpecificationExecutor<Invite> {

    Invite findByToken(String token);

    Invite findByInviteCode(String inviteCode);

    Invite findByRefererCode(String refererCode);
}
