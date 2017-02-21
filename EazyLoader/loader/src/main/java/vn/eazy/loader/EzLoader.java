package vn.eazy.loader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.kaopiz.kprogresshud.KProgressHUD;

import vn.eazy.core.loader.LoaderUtils;

/**
 * Created by Harry on 2/21/17.
 */

public class EzLoader implements LoaderUtils {
    private KProgressHUD progressHUD;

    @Override
    public void showLoading(Context context) {
        showLoading("", "", null, TYPE.SPIN, context);
    }

    @Override
    public void showLoaing(@NonNull String title, TYPE type, Context context) {
        showLoading(title, "", null, type, context);
    }

    @Override
    public void showLoading(@NonNull String title, Context context) {
        showLoading(title, "", null, TYPE.SPIN, context);
    }

    @Override
    public void showLoading(@NonNull String title, @NonNull String detail, View view, TYPE type, Context context) {
        if (progressHUD != null) {
            progressHUD = null;
        }
        progressHUD = KProgressHUD.create(context);

        if (!TextUtils.isEmpty(title)) {
            progressHUD.setLabel(title);
        }

        if (!TextUtils.isEmpty(detail)) {
            progressHUD.setDetailsLabel(detail);
        }

        if (view == null) {
            switch (type) {
                case SPIN:
                    progressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
                    break;
                case ANNULAR:
                    progressHUD.setStyle(KProgressHUD.Style.ANNULAR_DETERMINATE);
                    break;
                case PIE:
                    progressHUD.setStyle(KProgressHUD.Style.PIE_DETERMINATE);
                    break;
                case BAR:
                    progressHUD.setStyle(KProgressHUD.Style.BAR_DETERMINATE);
                    break;
            }
        } else {
            progressHUD.setCustomView(view);
        }
        progressHUD.show();
    }

    @Override
    public void showLoading(@NonNull String title, @NonNull String detail, Context context) {
        showLoading(title, detail, null, TYPE.SPIN, context);
    }

    @Override
    public void showLoading(@NonNull String title, @NonNull View view, Context context) {
        showLoading(title, "", view, TYPE.SPIN, context);
    }

    @Override
    public void hideLoading() {
        if (progressHUD != null && progressHUD.isShowing()) {
            progressHUD.dismiss();
        }
    }
}
