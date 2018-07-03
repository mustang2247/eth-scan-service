package com.open.coinnews.web.controller.admin;

import com.open.coinnews.app.model.BigBossAddr;
import com.open.coinnews.app.model.TokenTranLog;
import com.open.coinnews.app.service.IBigBossAddrService;
import com.open.coinnews.app.service.ITokenTranLogService;
import com.open.coinnews.basic.auth.annotations.AdminAuth;
import com.open.coinnews.basic.tools.PageableTools;
import com.open.coinnews.basic.tools.ParamFilterTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "admin/tokentranlog")
@AdminAuth(name="18T追踪管理", orderNum=1, psn="网站管理", pentity=0, porderNum=1)
public class AdminTokenTranLogController {

    private static final Logger logger = LoggerFactory.getLogger(AdminTokenTranLogController.class);

    @Autowired
    private ITokenTranLogService itokenTranLogService;

    @Autowired
    private IBigBossAddrService ibigBossAddrService;

    /** 列表 */
    @AdminAuth(name = "18T追踪列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value="list", method= RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        Page<TokenTranLog> datas = itokenTranLogService.findAll(new ParamFilterTools<TokenTranLog>().buildSpecification(model, request), PageableTools.basicPage(page, "desc", "updateTime"));
        model.addAttribute("datas", datas);
        return "admin/invite/tokenTransLog";
    }

    /** 列表 */
    @AdminAuth(name = "大户划地址列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value="operationsLogList", method= RequestMethod.GET)
    public String bigBossAddrList(Model model, Integer page, HttpServletRequest request) {
        Page<BigBossAddr> datas = ibigBossAddrService.findAll(new ParamFilterTools<BigBossAddr>().buildSpecification(model, request), PageableTools.basicPage(page, "desc", "updateTime"));
        model.addAttribute("datas", datas);
        return "admin/invite/bigBossAddr";
    }

}
