package sky.co.jp.business;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface IKojinBusiness {

    String kojin(Model model, HttpServletRequest request, HttpSession session);
}
