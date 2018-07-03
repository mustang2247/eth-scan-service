package com.open.coinnews.web.controller.admin;

import com.open.coinnews.app.model.LockPlan;
import com.open.coinnews.app.model.LockPlanOperationsLog;
import com.open.coinnews.app.model.LockPlanPrivateAddr;
import com.open.coinnews.app.service.ILockPlanOperationsLogService;
import com.open.coinnews.app.service.ILockPlanPrivateAddrService;
import com.open.coinnews.app.service.ILockPlanService;
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
import java.util.List;

@Controller
@RequestMapping(value = "admin/lockplan")
@AdminAuth(name="锁仓计划管理", orderNum=1, psn="网站管理", pentity=0, porderNum=1)
public class AdminLockPlanController {

    private static final Logger logger = LoggerFactory.getLogger(AdminLockPlanController.class);

    private static final String SIXMONTHSRATE = "5%";

    private static final String  ONEYEARRATE = "7%";

    private static final String  TWOYEARSRATE = "10%";

    @Autowired
    private ILockPlanService ilockPlanService;

    @Autowired
    private ILockPlanPrivateAddrService ilockPlanPrivateAdService;

    @Autowired
    private ILockPlanOperationsLogService ilockPlanOpService;

    /** 锁仓计划列表 */
    @AdminAuth(name = "锁仓计划列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value="list", method= RequestMethod.GET)
    public String list(Model model,Integer page, HttpServletRequest request) {
        Page<LockPlan> datas = ilockPlanService.findAll(new ParamFilterTools<LockPlan>().buildSpecification(model, request), PageableTools.basicPage(page, "desc", "updateTime"));
        List<LockPlan>  list = datas.getContent();
        for(LockPlan lp : list) {
            if(null != lp.getRate()){
                if ("1".equals(lp.getRate())) {
                    lp.setRate(SIXMONTHSRATE);
                }else if ("2".equals(lp.getRate())) {
                    lp.setRate(ONEYEARRATE);
                }else if ("3".equals(lp.getRate())) {
                    lp.setRate(TWOYEARSRATE);
                }
            }else{
                //如果利率为null,则默认为一年的利率
                lp.setRate(ONEYEARRATE);
            }

        }
        model.addAttribute("datas", datas);
        return "admin/invite/lockPlan";
    }


    /** 私有地址列表 */
    @AdminAuth(name = "私有地址列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value="addrList", method= RequestMethod.GET)
    public String addrList(Model model, Integer page, HttpServletRequest request) {
        Page<LockPlanPrivateAddr> datas = ilockPlanPrivateAdService.findAll(new ParamFilterTools<LockPlanPrivateAddr>().buildSpecification(model, request), PageableTools.basicPage(page,"desc","updateTime"));
        model.addAttribute("datas", datas);
        return "admin/invite/privateAddr";
    }

    /** 列表 */
    @AdminAuth(name = "锁仓操作记录列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value="operationsLogList", method= RequestMethod.GET)
    public String operationsLogList(Model model, Integer page, HttpServletRequest request) {
        Page<LockPlanOperationsLog> datas = ilockPlanOpService.findAll(new ParamFilterTools<LockPlanOperationsLog>().buildSpecification(model, request), PageableTools.basicPage(page, "desc", "updateTime"));
        model.addAttribute("datas", datas);
        return "admin/invite/operationsLog";
    }


}
