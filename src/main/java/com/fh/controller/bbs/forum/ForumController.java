package com.fh.controller.bbs.forum;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.Role;
import com.fh.util.Const;
import com.fh.util.PageData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by guodongyang on 16/5/5.
 */
@Controller
@RequestMapping(value="/bbs")
public class ForumController  extends BaseController {

    /**
     * 列表
     */
    @RequestMapping(value="/list")
    public ModelAndView list(Page page){
        logBefore(logger, "列表bbs");
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try{
            pd = this.getPageData();
            String KEYW = pd.getString("keyword");
            if(null != KEYW && !"".equals(KEYW)){
                KEYW = KEYW.trim();
                pd.put("KEYW", KEYW);
            }
            page.setPd(pd);
            List<PageData> varList = new ArrayList<PageData>();//列出bbs列表
            for(int i=0;i<5;i++){
                PageData vpd = new PageData();
                vpd.put("province","北京");	//1
                vpd.put("city","北京");	//2
                vpd.put("type","bbs");	//3
                vpd.put("author","gdd");	//4
                vpd.put("ct","2015-04-15 16:20:20");	//5
                vpd.put("clicks","10");	//6
                vpd.put("reply","5");	//6
                vpd.put("replyts","2015-04-15 18:20:20");	//6
                varList.add(vpd);
            }
            mv.setViewName("bbs/forum/forum_list");
            mv.addObject("varList", varList);
            mv.addObject("pd", pd);
            mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
        } catch(Exception e){
            logger.error(e.toString(), e);
        }
        return mv;
    }

    /**
     * 显示用户列表
     */
    @RequestMapping(value="/listUsers")
    public ModelAndView listUsers(Page page){
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try{
            pd = this.getPageData();

            String USERNAME = pd.getString("USERNAME");

            if(null != USERNAME && !"".equals(USERNAME)){
                USERNAME = USERNAME.trim();
                pd.put("USERNAME", USERNAME);
            }

            page.setPd(pd);
            List<PageData> userList = new ArrayList<PageData>();//列出bbs列表
            for(int i=0;i<5;i++){
                PageData users = new PageData();
                users.put("USER_ID","04762c0b28b643939455c7800c2e2412");
                users.put("EMAIL","18766666666@qq.com");
                users.put("PHONE","18766666666");
                users.put("USERNAME","gdd");
                users.put("NAME","gdd");
                users.put("ROLE_NAME","特级会员");
                users.put("END_TIME","2017-12-12 00:00:00");
                users.put("YEARS","2");
                users.put("LAST_LOGIN","2016-05-01 00:00:00");
                users.put("IP","127.0.0.1");
                users.put("STATUS","0");
                userList.add(users);
            }
            List<Role> roles = new ArrayList<Role>();
            Role role1 = new Role();
            role1.setROLE_ID("55896f5ce3c0494fa6850775a4e29ff6");
            role1.setROLE_NAME("特级会员");
            Role role2 = new Role();
            role2.setROLE_ID("55896f5ce3c0494fa6850775a4e29ff6");
            role2.setROLE_NAME("中级会员");
            Role role3 = new Role();
            role3.setROLE_ID("55896f5ce3c0494fa6850775a4e29ff6");
            role3.setROLE_NAME("高级会员");
            Role role4 = new Role();
            role4.setROLE_ID("55896f5ce3c0494fa6850775a4e29ff6");
            role4.setROLE_NAME("初级会员");
            roles.add(role1);
            roles.add(role2);
            roles.add(role3);
            roles.add(role4);
            mv.setViewName("bbs/forum/forum_users_list");
            mv.addObject("userList", userList);
            mv.addObject("roleList", roles);
            mv.addObject("pd", pd);
            mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
        } catch(Exception e){
            logger.error(e.toString(), e);
        }

        return mv;
    }

    /* ===============================权限================================== */
    public Map<String, String> getHC(){
        Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
        Session session = currentUser.getSession();
        return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
    }

}
