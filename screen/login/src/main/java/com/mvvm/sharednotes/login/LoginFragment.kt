package com.mvvm.sharednotes.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mvvm.sharednotes.login.databinding.FragmentLoginBinding
import com.mvvm.sharednotes.login.view.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    @Inject
    internal lateinit var inputMethodManager: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.keyBoardVisibilityState.observe(this) { isVisible ->
            if (!isVisible) inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)
        }

        viewModel.loginState.observe(this) { state ->
            if (state.isSuccessfullyLogined) Toast.makeText(
                requireContext(),
                "${state.user?.email}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.emailField.addTextChangedListener {
            binding.emailInputLayout.error = null
            viewModel.userInput = it.toString()
        }
    }
}