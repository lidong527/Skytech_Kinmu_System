package sky.co.jp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sky.co.jp.bean.AccountBean;
import sky.co.jp.exception.DisableException;
import sky.co.jp.mapper.AccountMapper;
import sky.co.jp.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {

@Autowired(required = false)
AccountMapper mapper;
    @Override
    public void test(String userMail, String password) {

    }

    @Override
    public void login(String userMail, String password) {
        AccountBean a = mapper.selectByuserMailAndPassword(userMail,password);
        if (a == null){
            throw new DisableException("メールアドレス、パスワードの入力に誤りがあるか登録されていません");
        }

    }
    @Override
    public void logind(String userMail, String password) {
        AccountBean account = mapper.selectPAsswordByUserMail(userMail);

        if (account  == null || !account.getPASSWORD().equals(password)){
            throw new DisableException("メールアドレス、パスワードの入力に誤りがあるか登録されていません");
        }else{
            if (account.getINITIALIZATION_FLG()==true){
                throw new DisableException("0");}
        }

    }
}
