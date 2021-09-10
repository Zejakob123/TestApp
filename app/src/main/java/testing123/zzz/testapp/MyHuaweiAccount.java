package testing123.zzz.testapp;

import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.service.AccountAuthService;

//singleton
public class MyHuaweiAccount {

    private static AccountAuthService mAuthManager;
    private static AccountAuthParams mAuthParam;

    public static AccountAuthService getmAuthManager() {
        return mAuthManager;
    }

    public static void setmAuthManager(AccountAuthService mAuthManager) {
        MyHuaweiAccount.mAuthManager = mAuthManager;
    }

    public static AccountAuthParams getmAuthParam() {
        return mAuthParam;
    }

    public static void setmAuthParam(AccountAuthParams mAuthParam) {
        MyHuaweiAccount.mAuthParam = mAuthParam;
    }
}
