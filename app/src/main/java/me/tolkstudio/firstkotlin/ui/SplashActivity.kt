package me.tolkstudio.firstkotlin.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.firebase.ui.auth.AuthUI
import me.tolkstudio.firstkotlin.R
import me.tolkstudio.firstkotlin.databinding.ActivitySplashBinding
import me.tolkstudio.firstkotlin.model.NoAuthException
import me.tolkstudio.firstkotlin.viewmodel.SplashViewModel
import org.koin.android.viewmodel.ext.android.viewModel

private const val RC_SIGN_IN = 42
private const val START_DELAY = 1000L

class SplashActivity : BaseActivity<Boolean>() {

    override val viewModel: SplashViewModel by viewModel()

    override val ui: ActivitySplashBinding
            by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    override val layoutRes: Int = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper())
                .postDelayed({
                    viewModel.requestUser()
                }, START_DELAY)
    }

    override fun renderData(data: Boolean) {
      if (data) startMainActivity()
    }

    override fun renderError(error: Throwable) {
        when (error) {
            is NoAuthException -> startLoginActivity()
            else -> error.message?.let { showError(it) }
        }
    }

    private fun startLoginActivity() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setLogo(R.drawable.common_google_signin_btn_icon_dark_focused)
                        .setTheme(R.style.LoginStyle)
                        .setAvailableProviders(
                                listOf(
                                        AuthUI.IdpConfig.EmailBuilder().build(),
                                        AuthUI.IdpConfig.GoogleBuilder().build()
                                )
                        )
                        .build(),
                RC_SIGN_IN)
    }

    private fun startMainActivity() {
        startActivity(MainActivity.getStartIntent(this))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN && resultCode != Activity.RESULT_OK) {
            finish()
        }
    }
}
