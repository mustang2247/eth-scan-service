package com.open.coinnews.web.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.open.coinnews.app.model.LockPlan;
import com.open.coinnews.app.service.ILockPlanPrivateAddrService;
import com.open.coinnews.app.service.ILockPlanService;
import com.open.coinnews.basic.tools.ConfigTools;
import com.open.coinnews.utils.IPAddressUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 锁仓计划
 */
@Controller
public class LockPlanController {
    private static final Logger logger = LoggerFactory.getLogger(LockPlanController.class);

    @Autowired
    private ILockPlanService lockPlanService;

    @Autowired
    private ConfigTools configTools;

    @Autowired
    private ILockPlanPrivateAddrService lockPlanPrivateAddrService;
    /**
     * 网站首页
     * @param model
     * @return
     * 锁仓首页
     */
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String index(Model model, @RequestParam(value = "l", required = false) String l) {

//        model.addAttribute("code", code);
        model.addAttribute("rewardPerNum", configTools.getRewardPerNum());
        model.addAttribute("rewardInviteNum", configTools.getRewardInviteNum());
        model.addAttribute("rewardMaxNum", configTools.getRewardMaxNum());
        model.addAttribute("telegramUrl", configTools.getTelegramUrl());
        model.addAttribute("systemStatus", configTools.getSystemStatus());

        model.addAttribute("candyName", configTools.getCandyName());
        model.addAttribute("candyTitleCN", configTools.getCandyTitleCN());
        model.addAttribute("candyTitleEN", configTools.getCandyTitleEN());

//        if (l == null || l.isEmpty() || l.equals("en")){
//            return "invite/index_en";
//        }else {
            return "invite/index";
//        }
    }

    /**
     * 锁仓个人页
     */
    @RequestMapping(value = "lock", method = {RequestMethod.GET, RequestMethod.POST})
    public String invite(HttpServletRequest request, Model model, @RequestParam(value = "code") String code, @RequestParam(value = "l", required = false) String l) {

//        logger.info(code, l);
        if (code != null && code.startsWith("/")){
            code = code.substring(1);
        }

        model.addAttribute("code", code);
        model.addAttribute("l", l);
//        model.addAttribute("url", code);
        model.addAttribute("rewardPerNum", configTools.getRewardPerNum());
        model.addAttribute("rewardInviteNum", configTools.getRewardInviteNum());
        model.addAttribute("rewardMaxNum", configTools.getRewardMaxNum());
        model.addAttribute("telegramUrl", configTools.getTelegramUrl());
        model.addAttribute("systemStatus", configTools.getSystemStatus());

        model.addAttribute("candyName", configTools.getCandyName());
        model.addAttribute("candyTitleCN", configTools.getCandyTitleCN());
        model.addAttribute("candyTitleEN", configTools.getCandyTitleEN());

        LockPlan oldData = lockPlanService.findByToken(code);
        if(oldData != null){
            model.addAttribute("url", oldData.getPrivateTokenAdd());
            model.addAttribute("inviteCount", oldData.getParticipateNum());
            model.addAttribute("inviteReward", oldData.getEarnedReward());
        }else {
            model.addAttribute("inviteCount", 0);
            model.addAttribute("inviteReward", 0);
        }

//        if (l == null || l.isEmpty() || l.equals("en")){
//            return "invite/invite_en";
//        }else {
            return "invite/invite";
//        }
    }

    /**
     * 保存token值
     * @param data
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject save(LockPlan data, HttpServletRequest httpServletReques) {
        logger.info(JSON.toJSONString(data));
        JSONObject resultObject = new JSONObject();
        Integer code = -1;
        try {
            if (data.getToken() != null && !data.getToken().isEmpty())
            {
                LockPlan oldData = lockPlanService.findByToken(data.getToken());
                if(oldData != null){
                    data = oldData;
                    code = 0;
                }else {
                    String ip = IPAddressUtil.getClientIpAddress(httpServletReques);
                    data.setIp(ip);
                    data.setCreateTime(new Date());
                    data.setUpdateTime(new Date());
                    //收益率等级默认为2级
                    data.setRate("2");
//                    SELECT * FROM table ORDER BY RAND() LIMIT 5
                    Integer count =  (int) (Math.random() * lockPlanPrivateAddrService.count());//new Random(lockPlanPrivateAddrService.count()).nextInt();
                    if (count <= 0){
                        count = 1;
                    }
                    String privateToken = lockPlanPrivateAddrService.getOne(count).getPrivateTokenAdd();
                    if (privateToken != null && privateToken != ""){
                        data.setPrivateTokenAdd(privateToken);
                        lockPlanService.save(data);

                        code = 0;
                    }else {
                        code = -1;
                    }
                }

                if (code != -1){
                    resultObject.put("token", data.getToken());
//                    resultObject.put("privateToken", data.getToken());
                    resultObject.put("l", data.getL());
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            code = -1;
        }

        resultObject.put("code", code);
        return resultObject;
    }

//    /**
//     * 更新邀请
//     * @param data
//     * @return
//     */
//    @RequestMapping(value = "updateInviteCount", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public String updateInviteCount(@RequestParam(value = "data") String data) {
//
//        if (configTools.getSystemStatus() != 0){
//            return "-1";
//        }
//
//        if (data != null && data.startsWith("/")){
//            data = data.substring(1);
//        }
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("result", lockPlanPrivateAddrService.updateInviteCount(data));
//        jsonObject.put("rewardPerNum", configTools.getRewardPerNum());
//        jsonObject.put("rewardInviteNum", configTools.getRewardInviteNum());
//        jsonObject.put("candyName", configTools.getCandyName());
//
//        return jsonObject.toJSONString();
//    }

}
