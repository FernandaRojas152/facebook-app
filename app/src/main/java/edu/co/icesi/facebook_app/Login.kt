package edu.co.icesi.facebook_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import edu.co.icesi.facebook_app.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var user1= User("Fernanda", "alfa@gmail.com", "aplicacionesmoviles")
        var user2= User("Xiao", "beta@gmail.com", "aplicacionesmoviles")

        binding.login.setOnClickListener{
            var email = binding.textEmail.text.toString()
            var password = binding.textPassword.text.toString()

            if(email.equals(user1.email) && password.equals(user1.password) || email.equals(user2.email) && password.equals(user2.password)){
                val i= Intent(applicationContext, Home :: class.java).apply{}
                startActivity(i)
            }else{
                Toast.makeText(this, "Ocurrio un error, intenta de nuevo", Toast.LENGTH_SHORT).show()
            }
        }
    }
}