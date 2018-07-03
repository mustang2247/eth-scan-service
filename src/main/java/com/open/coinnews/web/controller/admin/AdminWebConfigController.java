package com.open.coinnews.web.controller.admin;

import com.open.coinnews.app.model.WebConfigInfo;
import com.open.coinnews.app.service.WebConfigInfoService;
import com.open.coinnews.basic.auth.annotations.AdminAuth;
import com.open.coinnews.basic.auth.annotations.Token;
import com.open.coinnews.basic.tools.ConfigTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "admin/webConfig")
@AdminAuth(name="网站配置管理", orderNum=1, psn="网站管理", pentity=0, porderNum=1)
public class AdminWebConfigController {

    private static final Logger logger = LoggerFactory.getLogger(AdminWebConfigController.class);

    @Autowired
    private ConfigTools configTools;

    @Autowired
    private WebConfigInfoService webConfigInfoService;

    /** 列表 */
    @AdminAuth(name = "网站配置", orderNum = 1, icon="icon-list")
    @RequestMapping(value="webConfig", method= RequestMethod.GET)
    public String webConfig(Model model, HttpServletRequest request) {

        WebConfigInfo webConfigInfo = webConfigInfoService.findOne(1);
        if (webConfigInfo == null){
            webConfigInfo = new WebConfigInfo();
        }

        webConfigInfo.setFileBaseUrl(configTools.getFileBaseUrl());
        webConfigInfo.setUploadPath(configTools.getFileBaseUrl());
        webConfigInfo.setRewardPerNum(configTools.getRewardPerNum());
        webConfigInfo.setRewardInviteNum(configTools.getRewardInviteNum());
        webConfigInfo.setRewardMaxNum(configTools.getRewardMaxNum());
        webConfigInfo.setTelegramUrl(configTools.getTelegramUrl());

        webConfigInfo.setIpLimitNum(configTools.getIpLimitNum());
        webConfigInfo.setSystemStatus(configTools.getSystemStatus());
        webConfigInfo.setCandyName(configTools.getCandyName());

        webConfigInfo.setCandyTitleCN(configTools.getCandyTitleCN());
        webConfigInfo.setCandyTitleEN(configTools.getCandyTitleEN());

        webConfigInfo.setAppLogo(configTools.getFileBaseUrl() + "/invite/img/logo_pc.svg");
        webConfigInfo.setValue(configTools.getValue());


        model.addAttribute("datas", webConfigInfo);
        return "admin/invite/webConfig";
    }

    @Token(flag=Token.CHECK)
    @RequestMapping(value="webConfig", method=RequestMethod.POST)
    public String update(Model model, WebConfigInfo datas, HttpServletRequest request) {
        try {
            webConfigInfoService.save(datas);

            configTools.setFileBaseUrl(datas.getFileBaseUrl());
            configTools.setUploadPath(datas.getFileBaseUrl());
            configTools.setRewardPerNum(datas.getRewardPerNum());
            configTools.setRewardInviteNum(datas.getRewardInviteNum());
            configTools.setRewardMaxNum(datas.getRewardMaxNum());
            configTools.setTelegramUrl(datas.getTelegramUrl());

            configTools.setIpLimitNum(datas.getIpLimitNum());
            configTools.setSystemStatus(datas.getSystemStatus());
            configTools.setCandyName(datas.getCandyName());

            configTools.setCandyTitleCN(datas.getCandyTitleCN());
            configTools.setCandyTitleEN(datas.getCandyTitleEN());
            configTools.setValue(datas.getValue());

//        configTools.setAppLogo(configTools.getFileBaseUrl() + "/invite/img/logo_pc.svg");

        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("datas", datas);
        return "admin/invite/webConfig";
    }


}
