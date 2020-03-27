package learncodeonline.in.mymall;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    public SignUpFragment() {
        // Required empty public constructor
    }

    private TextView alreadyHaveAnAccount;
    private FrameLayout parentFrameLayout;
    private EditText email;
    private EditText fullName;
    private EditText password;
    private EditText confirmPassword;
    private FirebaseFirestore firebaseFirestore;

    private ImageButton closebtn;
    private Button signUpbtn;

    private ProgressBar progressBar;

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    private FirebaseAuth firebaseAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
         alreadyHaveAnAccount = view.findViewById(R.id.already_account);
         parentFrameLayout = getActivity().findViewById(R.id.register_framelayout);

         email = view.findViewById(R.id.sign_up_email);
         fullName = view.findViewById(R.id.sign_up_full_name);
         password = view.findViewById(R.id.sign_up_password);
         confirmPassword = view.findViewById(R.id.sign_up_confirm_password);

         closebtn = view.findViewById(R.id.sign_up_close_btn);
         signUpbtn = view.findViewById(R.id.sign_up_btn);

         progressBar = view.findViewById(R.id.sign_up_progressbar);

         firebaseAuth = FirebaseAuth.getInstance();
         firebaseFirestore = FirebaseFirestore.getInstance();
         return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignInFragment());
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
        fullName.addTextChangedListener(new TextWatcher() {
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
        confirmPassword.addTextChangedListener(new TextWatcher() {
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


        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkEmailAndPassword();
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_right);
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
    private void checkInputs(){
        if(!TextUtils.isEmpty(email.getText())){
          if(!TextUtils.isEmpty(fullName.getText())){
              if(!TextUtils.isEmpty(password.getText()) && password.length() >= 8){
                 if(!TextUtils.isEmpty(confirmPassword.getText())){
                   signUpbtn.setEnabled(true);
                   signUpbtn.setTextColor(Color.rgb(255,255,255));
                 }else{
                     signUpbtn.setEnabled(false);
                     signUpbtn.setTextColor(Color.argb(50,255,255,255));
                 }
              }else{
                  signUpbtn.setEnabled(false);
                  signUpbtn.setTextColor(Color.argb(50,255,255,255));
              }
          }else{
              signUpbtn.setEnabled(false);
              signUpbtn.setTextColor(Color.argb(50,255,255,255));
          }
        }else{
           signUpbtn.setEnabled(false);
           signUpbtn.setTextColor(Color.argb(50,255,255,255));
        }
    }
    private void checkEmailAndPassword(){

        Drawable customErrorIcon = getResources().getDrawable(R.mipmap.errorlogo);
        customErrorIcon.setBounds(0,0,customErrorIcon.getIntrinsicWidth(),customErrorIcon.getIntrinsicHeight());

        if(email.getText().toString().matches(emailPattern)){
          if(password.getText().toString().equals(confirmPassword.getText().toString())){

              progressBar.setVisibility(View.VISIBLE);
              signUpbtn.setEnabled(false);
              signUpbtn.setTextColor(Color.argb(50,255,255,255));

              firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                      .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                             if(task.isSuccessful()){

                                 HashMap<Object,String> userdata = new HashMap<>();
                                 userdata.put("fullname",fullName.getText().toString());

                                 firebaseFirestore.collection("USERS")
                                         .add(userdata)
                                         .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                             @Override
                                             public void onComplete(@NonNull Task<DocumentReference> task) {
                                                if(task.isSuccessful()){
                                                    mainIntent();
                                                }else{
                                                    progressBar.setVisibility(View.INVISIBLE);
                                                    signUpbtn.setEnabled(true);
                                                    signUpbtn.setTextColor(Color.rgb(255,255,255));
                                                    String error = task.getException().getLocalizedMessage();
                                                    Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
                                                }
                                             }
                                         });
                             }else{
                                 progressBar.setVisibility(View.INVISIBLE);
                                 signUpbtn.setEnabled(true);
                                 signUpbtn.setTextColor(Color.rgb(255,255,255));
                                 String error = task.getException().getLocalizedMessage();
                                 Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
                             }
                          }
                      });

          }else{
             confirmPassword.setError("Password doesn't matched!",customErrorIcon);
          }
        }else{
           email.setError("Invalid Email!",customErrorIcon);
        }
    }
    private void mainIntent(){
        Intent mainIntent = new Intent(getActivity(),MainActivity.class);
        startActivity(mainIntent);
        getActivity().finish();
    }
}
