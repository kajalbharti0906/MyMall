package learncodeonline.in.mymall.authentication;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import learncodeonline.in.mymall.MainActivity;
import learncodeonline.in.mymall.R;

import static learncodeonline.in.mymall.authentication.RegisterActivity.onResetPasswordFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {


    public SignInFragment() {
        // Required empty public constructor
    }

    private TextView dontHaveAnAccount;
    private FrameLayout parentFrameLayout;
    private EditText email;
    private EditText password;

    private TextView forgotPassword;

    private ImageButton closebtn;
    private Button signInbtn;

    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    public static Boolean disableCloseBtn = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
         dontHaveAnAccount = view.findViewById(R.id.dont_account);

         forgotPassword = view.findViewById(R.id.sign_in_forgot_password);

         parentFrameLayout = getActivity().findViewById(R.id.register_framelayout);

         email = view.findViewById(R.id.sign_in_email);
         password = view.findViewById(R.id.sign_in_password);

         closebtn = view.findViewById(R.id.sign_in_close_btn);
         signInbtn = view.findViewById(R.id.sign_in_btn);
         progressBar = view.findViewById(R.id.sign_in_progressbar);

         firebaseAuth = FirebaseAuth.getInstance();

         if(disableCloseBtn){
             closebtn.setVisibility(View.GONE);
         }
         else{
             closebtn.setVisibility(View.VISIBLE);
         }

         return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dontHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignUpFragment());
            }

        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResetPasswordFragment = true;
                setFragment(new ResetPasswordFragment());
            }
        });

        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainIntent();
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        signInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailAndPassword();
            }
        });
    }



    private void setFragment(Fragment fragment) {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
            fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
            fragmentTransaction.commit();

    }

    private void checkInputs() {
    if(!TextUtils.isEmpty(email.getText())){
        if(!TextUtils.isEmpty(password.getText())){
           signInbtn.setEnabled(true);
           signInbtn.setTextColor(Color.rgb(255,255,255));
        }else{
            signInbtn.setEnabled(false);
            signInbtn.setTextColor(Color.argb(50,255,255,255));
        }
    }else{
        signInbtn.setEnabled(false);
        signInbtn.setTextColor(Color.argb(50,255,255,255));
    }

    }

    private void checkEmailAndPassword(){

//        Drawable customErrorIcon = getResources().getDrawable(R.mipmap.errorlogo);
//        customErrorIcon.setBounds(0,0,customErrorIcon.getIntrinsicWidth(),customErrorIcon.getIntrinsicHeight());

        if(email.getText().toString().matches(emailPattern)){
            if(password.length() >= 8){

                progressBar.setVisibility(View.VISIBLE);
                signInbtn.setEnabled(false);
                signInbtn.setTextColor(Color.argb(50,255,255,255));

                firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    mainIntent();

                                }else{
                                    progressBar.setVisibility(View.INVISIBLE);
                                    signInbtn.setEnabled(true);
                                    signInbtn.setTextColor(Color.rgb(255,255,255));
                                    String error = task.getException().getLocalizedMessage();
                                    Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }else{
                Toast.makeText(getActivity(),"Incorrect email or password!",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getActivity(),"Incorrect email or password!",Toast.LENGTH_SHORT).show();
        }
    }

    private void mainIntent(){
        if(disableCloseBtn){
            disableCloseBtn = false;
        }
        else {
            Intent mainIntent = new Intent(getActivity(), MainActivity.class);
            startActivity(mainIntent);
        }
        getActivity().finish();
    }
}

