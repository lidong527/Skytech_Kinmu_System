package sky.co.jp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sky.co.jp.bean.GetButtonCdBean;
import sky.co.jp.form.OthersForm;
import sky.co.jp.mapper.GetBottonCdMapper;
import sky.co.jp.mapper.SessionMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SonoHokaController {

    private static final String BUTTON_TYPE = "BUTTON_TYPE";

    @Autowired(required = false)
    GetBottonCdMapper getBottonCdMapper;

    @Autowired(required = false)
    SessionMapper sessionMapper;

    @PostMapping("/others")
    public String setOhtersForm(Model model, HttpServletRequest request,HttpSession session) {
        HeaderController header = new HeaderController();

        String staffBg = header.CheckCookie(request,session,sessionMapper);
        if(staffBg=="FALSE"){
            return "redirect:sky-tech";
        }
        String permBg = header.CheckPermission(request,session,sessionMapper,getBottonCdMapper);
        if (permBg == "FALSE"){
            return "redirect:sky-tech";
        }

        String msg;
        List<GetButtonCdBean> getButtonCdBeans = new ArrayList<GetButtonCdBean>();
        getButtonCdBeans = getBottonCdMapper.selectContent12ById(BUTTON_TYPE);

        if (getButtonCdBeans.size() == 0) {
            msg = "この画面のファンクションなし。";
            List<OthersForm> template = new ArrayList<OthersForm>();
            OthersForm othersForm = new OthersForm();
            othersForm.setButtonName(msg);
            othersForm.setButtonURL("");
            template.add(othersForm);
            model.addAttribute("OthersForm", template);
            return "zaimu/others";
        }else{
            List<OthersForm> template = new ArrayList<OthersForm>();

            for(GetButtonCdBean tempBean : getButtonCdBeans){
                OthersForm othersForm = new OthersForm();
                othersForm.setButtonName(tempBean.getCODE_CONTENT());
                othersForm.setButtonURL(tempBean.getCODE_CONTENT2());
                template.add(othersForm);
            }
            model.addAttribute("OthersForm", template);
            return "zaimu/others";
        }
    }
}
