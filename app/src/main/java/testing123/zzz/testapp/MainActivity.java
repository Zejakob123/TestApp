package testing123.zzz.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.account.service.AccountAuthService;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "HuaweiIdActivity";
    private AccountAuthService mAuthManager;
    private AccountAuthParams mAuthParam;
    private boolean access;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // activity_main is the name of the custom layout file.
        setContentView(R.layout.activity_main);
        access = false;

        findViewById(R.id.id_token_mode_sign_in_btn).setOnClickListener(this);
        findViewById(R.id.autho_mode_sign_int_btn).setOnClickListener(this);
        findViewById(R.id.silent_sign_in_btn).setOnClickListener(this);
        findViewById(R.id.sign_out_btn).setOnClickListener(this);
        findViewById(R.id.cancel_autho_btn).setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * Codelab Code
     * Pull up the authorization interface by getSignInIntent
     */
    private void signIn() {
        MyHuaweiAccount.setmAuthParam(new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
                .setEmail()
                .setIdToken()
                .createParams());
    //                .setAccessToken()

        MyHuaweiAccount.setmAuthManager(AccountAuthManager.getService(MainActivity.this, MyHuaweiAccount.getmAuthParam()));
        startActivityForResult(MyHuaweiAccount.getmAuthManager().getSignInIntent(), Constant.REQUEST_SIGN_IN_LOGIN);
    }

    private void signInCode() {
        MyHuaweiAccount.setmAuthParam(new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
                .setProfile()
                .setAuthorizationCode()
                .createParams());
        MyHuaweiAccount.setmAuthManager(AccountAuthManager.getService(MainActivity.this, MyHuaweiAccount.getmAuthParam()));
        startActivityForResult(MyHuaweiAccount.getmAuthManager().getSignInIntent(), Constant.REQUEST_SIGN_IN_LOGIN_CODE);
    }

    /**
     * Codelab Code
     * sign Out by signOut
     */
    private void signOut() {
        Task<Void> signOutTask = MyHuaweiAccount.getmAuthManager().signOut();
        signOutTask.addOnSuccessListener(aVoid -> Log.i(TAG, "signOut Success")).addOnFailureListener(e -> Log.i(TAG, "signOut fail"));
        access = false;
    }

    /**
     * Codelab Code
     * Silent SignIn by silentSignIn
     */
    private void silentSignIn() {
        Task<AuthAccount> task = MyHuaweiAccount.getmAuthManager().silentSignIn();
        task.addOnSuccessListener(authAccount -> Log.i(TAG, "silentSignIn success"));
        task.addOnFailureListener(e -> {
            //if Failed use getSignInIntent
            if (e instanceof ApiException) {
                ApiException apiException = (ApiException) e;
                signIn();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_token_mode_sign_in_btn:
                signIn();
                if(access == true){
                    Intent intent1 = new Intent(MainActivity.this, MenuPage.class);
                    startActivity(intent1);
                }
                break;
            case R.id.autho_mode_sign_int_btn:
                signInCode();
                if(access == true){
                    Intent intent2 = new Intent(MainActivity.this, MenuPage.class);
                    startActivity(intent2);
                }
                break;
            case R.id.silent_sign_in_btn:
                silentSignIn();
                if(access = true){
                    Intent intent3 = new Intent(MainActivity.this, MenuPage.class);
                    startActivity(intent3);
                }
                break;
            case R.id.sign_out_btn:
                signOut();
                break;
            case R.id.cancel_autho_btn:
                cancelAuthorization();
                break;
            default:
                break;
        }
    }

    private void cancelAuthorization() {
        MyHuaweiAccount.setmAuthParam(new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
                .setProfile()
                .setAuthorizationCode()
                .createParams());
        MyHuaweiAccount.setmAuthManager(AccountAuthManager.getService(MainActivity.this, MyHuaweiAccount.getmAuthParam()));
        Task<Void> task = MyHuaweiAccount.getmAuthManager().cancelAuthorization();
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                access = false;
                Log.i(TAG, "cancelAuthorization success");
            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.i(TAG, "cancelAuthorization failure：" + e.getClass().getSimpleName());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_SIGN_IN_LOGIN) {
            //login success
            //get user message by parseAuthResultFromIntent
            Task<AuthAccount> authAccountTask = AccountAuthManager.parseAuthResultFromIntent(data);
            if (authAccountTask.isSuccessful()) {
                AuthAccount authAccount = authAccountTask.getResult();
                Log.i(TAG, authAccount.getDisplayName() + " signIn success ");
                Log.i(TAG, "AccessToken: " + authAccount.getAccessToken());
                access = true;
            } else {
                Log.i(TAG, "signIn failed: " + ((ApiException) authAccountTask.getException()).getStatusCode());
            }
        }
        if (requestCode == Constant.REQUEST_SIGN_IN_LOGIN_CODE) {
            //login success
            Task<AuthAccount> authAccountTask = AccountAuthManager.parseAuthResultFromIntent(data);
            if (authAccountTask.isSuccessful()) {
                AuthAccount authAccount = authAccountTask.getResult();
                Log.i(TAG, "signIn get code success.");
                Log.i(TAG, "ServerAuthCode: " + authAccount.getAuthorizationCode());
                access = true;
                /**** english doc:For security reasons, the operation of changing the code to an AT must be performed on your server. The code is only an example and cannot be run. ****/
                /**********************************************************************************************/
            } else {
                Log.i(TAG, "signIn get code failed: " + ((ApiException) authAccountTask.getException()).getStatusCode());
            }
        }
    }




}