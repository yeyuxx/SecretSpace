package head.secretspace.utils.fingerprint;

import android.os.Handler;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;

import head.secretspace.ui.activity.login.VerifyLoginActivity;
import head.secretspace.ui.fragment.FingerprintFragment;

/**
 * Created by lucode on 2017/3/16.
 */
public class AuthCallback extends FingerprintManagerCompat.AuthenticationCallback {
    private Handler handler = null;
    public AuthCallback(Handler handler) {
        super();
        this.handler = handler;
    }
    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        super.onAuthenticationError(errMsgId, errString);
        if (handler != null) {
            handler.obtainMessage(VerifyLoginActivity.MSG_AUTH_ERROR, errMsgId, 0).sendToTarget();
        }
    }
    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        super.onAuthenticationHelp(helpMsgId, helpString);
        if (handler != null) {
            handler.obtainMessage(VerifyLoginActivity.MSG_AUTH_HELP, helpMsgId, 0).sendToTarget();
        }
    }
    @Override
    public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        if (handler != null) {
            handler.obtainMessage(VerifyLoginActivity.MSG_AUTH_SUCCESS).sendToTarget();
        }
    }
    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        if (handler != null) {
            handler.obtainMessage(VerifyLoginActivity.MSG_AUTH_FAILED).sendToTarget();
        }
    }

}
