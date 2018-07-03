package com.open.coinnews.web.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.open.coinnews.app.model.Invite;
import com.open.coinnews.app.service.IInviteService;
import com.open.coinnews.basic.auth.annotations.AdminAuth;
import com.open.coinnews.basic.auth.annotations.Token;
import com.open.coinnews.basic.auth.tools.TokenTools;
import com.open.coinnews.basic.tools.ConfigTools;
import com.open.coinnews.basic.tools.MyBeanUtils;
import com.open.coinnews.basic.tools.PageableTools;
import com.open.coinnews.basic.tools.ParamFilterTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "admin/invite")
@AdminAuth(name="邀请管理", orderNum=1, psn="网站管理", pentity=0, porderNum=1)
public class AdminInviteController {

    private static final Logger logger = LoggerFactory.getLogger(AdminInviteController.class);

    @Autowired
    private IInviteService inviteService;

    @Autowired
    private ConfigTools configTools;

    /** 列表 */
    @AdminAuth(name = "用户列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value="list", method= RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        Page<Invite> datas = inviteService.findAll(new ParamFilterTools<Invite>().buildSpecification(model, request), PageableTools.basicPage(page, "desc", "inviteReward"));
        model.addAttribute("datas", datas);
        return "admin/invite/list";
    }


    @Token(flag=Token.READY)
    @AdminAuth(name="修改用户", orderNum=3, type="2")
    @RequestMapping(value="update/{id}", method=RequestMethod.GET)
    public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
        Invite article = inviteService.findOne(id);

        logger.info(JSON.toJSONString(article));

        model.addAttribute("article", article);
        model.addAttribute("cateList", inviteService.findAll());

        return "admin/invite/update";
    }

    @Token(flag=Token.CHECK)
    @RequestMapping(value="update/{id}", method=RequestMethod.POST)
    public String update(Model model, @PathVariable Integer id, Invite article, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            Invite art = inviteService.findOne(id);
            MyBeanUtils.copyProperties(article, art, new String[]{"id","token", "refererCode", "inviteCode", "l"});

            logger.info(JSON.toJSONString(art));
            inviteService.save(art);
        }
        return "redirect:/admin/invite/list";
    }

    @AdminAuth(name="删除用户", orderNum=4, type="2")
    @RequestMapping(value="delete/{id}", method=RequestMethod.POST)
    public @ResponseBody
    String delete(@PathVariable Integer id) {
        try {
            logger.info(id.toString());
            inviteService.delete(id);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }


}
