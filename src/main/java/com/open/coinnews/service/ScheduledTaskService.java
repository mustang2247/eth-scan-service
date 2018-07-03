package com.open.coinnews.service;

import com.ethereum.token.transfer.listener.Web3jService;
import com.open.coinnews.app.model.BigBossAddr;
import com.open.coinnews.app.model.LockPlan;
import com.open.coinnews.app.model.LockPlanOperationsLog;
import com.open.coinnews.app.model.TokenTranLog;
import com.open.coinnews.app.service.*;
import com.open.coinnews.basic.tools.ConfigTools;
import com.open.coinnews.utils.SmsSendUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 任务处理
 */
@Service
public class ScheduledTaskService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskService.class);

    @Autowired
    private ConfigTools configTools;

    /**
     * 18T变化追踪
     */
    @Autowired
    private ITokenTranLogService tokenTranLogService;

    /**
     * 锁仓计操作记录
     */
    @Autowired
    private ILockPlanOperationsLogService lockPlanOperationsLogService;

    /**
     * 大户划地址列表
     */
    @Autowired
    private IBigBossAddrService bigBossAddrService;


    @Autowired
    private IPhoneService phoneService;

    @Autowired
    private WebConfigInfoService webConfigInfoService;

    /**
     * 锁仓计划
     */
    @Autowired
    private ILockPlanService lockPlanService;

//    @Autowired
//    private Web3jService web3jService;


    /**
     * 开始扫描
     */
    public void startBlockChainScan() {
        Web3jService web3jService = new Web3jService();
        web3jService.init(configTools.getStartBlockNumber(), configTools.getEthNodeUrl(), configTools.getContractAddress(), configTools.getRetryTimes(), configTools.getThreadNumber(), bigBossAddrService);


        web3jService.start((from, to, value, time, fromValue, toValue, txHash) -> {
            logger.info("from:" + from + ", to:" + to + ", value:" + value.doubleValue() + ", time:" + time
                    + ", fromValue:" + fromValue.doubleValue() + ", toValue:" + toValue.doubleValue() + ", txHash:" + txHash + "  local time:  " + new Date().toString());

            if (value.doubleValue() <= 0 || value.doubleValue() > 30){
                return;
            }
            // 处理锁仓 1 锁仓额度  2 记录
//            LockPlan lockPlan = lockPlanService.findByTokenAndPrivateTokenAdd(from, to);
//            if (lockPlan != null){
//                dealLockPlan(lockPlan, from, to, value.doubleValue(), time * 1000,fromValue.doubleValue(),toValue.doubleValue(),txHash);
//            }else {
                // 处理大户交易记录和通知
//                BigBossAddr bigBossAddr = bigBossAddrService.findByPrivateTokenAdd(from);
//                //dealBigBossTx(bigBossAddr,"1","1",1,1231546,10000000.0,10000000.0,"1");
//                if (bigBossAddr != null){
//                    dealBigBossTx(bigBossAddr, from, to, value.doubleValue(), time * 1000,fromValue.doubleValue(),toValue.doubleValue(),txHash);
////                    dealBigBossTx(from, to, value.doubleValue(), time * 1000,fromValue.doubleValue(),toValue.doubleValue(),txHash);
//                }
//            }

            TokenTranLog tokenTranLog = tokenTranLogService.findByToToken(to);
            if (tokenTranLog == null){
                dealBigBossTx(from, to, value.doubleValue(), time * 1000,fromValue.doubleValue(),toValue.doubleValue(),txHash);
            }

        });
    }

    private void dealBigBossTx( String from, String to, double value, long time, double fromValue,
                               double toValue,
                               String txHash) {
        try {
            if (tokenTranLogService.findByTxHash(txHash) == null){
                TokenTranLog tokenTranLog = new TokenTranLog(from, to, txHash, value, fromValue, new Date(), new Date(time));
                tokenTranLogService.saveAndFlush(tokenTranLog);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 处理大户交易记录和通知
     * @param bigBossAddr
     * @param from      发送者
     * @param to        接收者
     * @param value     交易量
     * @param time      交易时间
     * @param fromValue 发送者数量
     * @param toValue   接收者数量
     * @param txHash    单号
     */
    private void dealBigBossTx(BigBossAddr bigBossAddr, String from, String to, double value, long time, double fromValue,
                               double toValue,
                               String txHash) {
        try {
            if (tokenTranLogService.findByTxHash(txHash) == null){
                TokenTranLog tokenTranLog = new TokenTranLog(from, to, txHash, value, fromValue, new Date(), new Date(time));
                List<String> phoneList =  phoneService.findAllPhone();//获取所需要发送信息的手机号
                Double nowvalue= webConfigInfoService.findValue();//获取当前设置value
//                判断交易值
                if(value >= nowvalue){
                    for(int i=0;i<phoneList.size();i++){
                        //发送短信通知
                        SmsSendUtil.sendSmsMessage("86"+phoneList.get(i),bigBossAddr.getPrivateTokenAdd()+"有大量交易："+value);
                    }
                }
                tokenTranLogService.saveAndFlush(tokenTranLog);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 处理锁仓 1 锁仓额度  2 记录
     * @param lockPlan
     * @param from      发送者
     * @param to        接收者
     * @param value     交易量
     * @param time      交易时间
     * @param fromValue 发送者数量
     * @param toValue   接收者数量
     * @param txHash    单号
     */
    private void dealLockPlan(LockPlan lockPlan, String from, String to, double value, long time, double fromValue,
                              double toValue,
                              String txHash) {

        try {
//        2 记录
            if (lockPlanOperationsLogService.findByTxHash(txHash) == null){

                try {
                    //        1 锁仓额度
                    lockPlan.setParticipateNum(lockPlan.getParticipateNum() + value);
                    lockPlan.setUpdateTime(new Date(time));
                    lockPlanService.saveAndFlush(lockPlan);
                }catch (Exception e){
                    e.printStackTrace();
                }

                LockPlanOperationsLog lockPlanOperationsLog = new LockPlanOperationsLog(from, to,  txHash, value, new Date(time), new Date());
                lockPlanOperationsLogService.saveAndFlush(lockPlanOperationsLog);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}