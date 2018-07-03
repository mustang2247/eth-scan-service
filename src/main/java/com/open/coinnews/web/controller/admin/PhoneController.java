package com.open.coinnews.web.controller.admin;

/**
 * @author guominghai
 * @Description:
 * @Date Created in 13:57 2018/5/25
 */

import com.open.coinnews.app.model.Phone;
import com.open.coinnews.app.service.IPhoneService;
import com.open.coinnews.basic.auth.annotations.AdminAuth;
import com.open.coinnews.basic.auth.annotations.Token;
import com.open.coinnews.basic.auth.tools.TokenTools;
import com.open.coinnews.basic.exception.SystemException;
import com.open.coinnews.basic.tools.ConfigTools;
import com.open.coinnews.basic.tools.PageableTools;
import com.open.coinnews.basic.tools.ParamFilterTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统配置
 */
@Controller
@RequestMapping(value = "admin/phone")
@AdminAuth(name="手机号管理", orderNum=1, psn="网站管理", pentity=0, porderNum=1)
public class PhoneController {


    private static final Logger logger = LoggerFactory.getLogger(AdminInviteController.class);

    @Autowired
    private IPhoneService phoneService;

    @Autowired
    private ConfigTools configTools;


    /** 列表 */
    @AdminAuth(name = "手机号列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value="list", method= RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        Page<Phone> datas = phoneService.findAll(new ParamFilterTools<Phone>().buildSpecification(model, request), PageableTools.basicPage(page, "desc", "id"));
        model.addAttribute("datas", datas);
        return "admin/phone/list";
    }

    @Token(flag = Token.READY)
    @AdminAuth(name = "添加成员", orderNum = 2, icon = "icon-plus")
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model, HttpServletRequest request) {
        Phone phone = new Phone();
        model.addAttribute("phone", phone);
        return "admin/phone/add";
    }

    /**
     * 添加POST
     */
    @Token(flag = Token.CHECK)
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(HttpServletRequest request) {
        String phone = request.getParameter("phone");
        String token = request.getParameter("token");
        if (TokenTools.isNoRepeat(request)) {
            if (phone != null && !"".equalsIgnoreCase(phone)) {
                Integer p = phoneService.findByPhone(phone);
                if (p > 0) {
                    throw new SystemException("手机号【" + phone + "】已经存在！");
                } else {
                    Phone  phone1 = new Phone();
                    phone1.setPhone(phone);
                    phone1.setToken(token);
                    phoneService.save(phone1);
                }
            }
        }
        return "redirect:/admin/phone/list";
    }

    /*@Token(flag=Token.READY)
    @AdminAuth(name="修改手机号", orderNum=3, type="2")
    @RequestMapping(value="update/{id}", method=RequestMethod.GET)
    public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
        Phone phone = phoneService.findOne(id);

        logger.info(JSON.toJSONString(phone));

        model.addAttribute("phones", phone);
        model.addAttribute("cateList", phoneService.findAll());

        return "admin/phone/update";
    }*/

    /*@Token(flag=Token.CHECK)
    @RequestMapping(value="update/{id}", method=RequestMethod.POST)
    public String update( @PathVariable Integer id, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            Phone art = phoneService.findOne(id);
            double value = Double.parseDouble(request.getParameter("value"));
            //MyBeanUtils.copyProperties(phone, art, new String[]{"id","token","phone","value"});
            art.setValue(value);
            logger.info(JSON.toJSONString(art));
            phoneService.save(art);
        }
        return "redirect:/admin/phone/list";
    }*/


    @AdminAuth(name="删除手机号", orderNum=4, type="2")
    @RequestMapping(value="delete/{id}", method=RequestMethod.POST)
    public @ResponseBody
    String delete(@PathVariable Integer id) {
        try {
            logger.info(id.toString());
            phoneService.deleteById(id);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }
}
