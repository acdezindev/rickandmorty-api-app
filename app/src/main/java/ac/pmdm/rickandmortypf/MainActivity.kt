package ac.pmdm.rickandmortypf

import ac.pmdm.rickandmortypf.databinding.ActivityMainBinding
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


/**
 * Activity principal de autenticación
 * Gestiona el inicio de sesión de usuarios con Firebase Authentication
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // Instancia de Firebase Authentication
    private lateinit var auth: FirebaseAuth

    // Usuario autenticado actualmente (puede ser null)
    private var user : FirebaseUser? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Aplicar el idioma guardado en preferencias
        Idioma.ponerIdioma(this)

        // Inflar el layout con ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // Botón para iniciar sesión
        binding.btnInicio.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            signIn(email, password)
        }

        // Botón para ir al registro de nuevos usuarios
        binding.btnRegistrarse.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

    }


    /**
     * Inicia sesión con email y contraseña en Firebase Authentication
     * @param email Correo electrónico del usuario
     * @param password Contraseña del usuario
     */

        private fun signIn(email: String, password: String) {
        // Validar que los campos no estén vacíos
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return
            }

        // Autenticar con Firebase
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        user = auth.currentUser
                        Toast.makeText(this, "Bienvenido ${user?.email}", Toast.LENGTH_SHORT).show()
                        updateUI(user)
                    } else {
                        Toast.makeText(this, "Error de autenticación", Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }

    /**
     * Actualiza la UI según el estado de autenticación del usuario
     * @param user Usuario autenticado o null si no hay sesión
     */
        private fun updateUI(user: FirebaseUser?) {
        // Usuario autenticado correctamente
            if (user != null) {
                startActivity(Intent(this, PrincipalActivity::class.java))
                finish()
            } else {
                // Usuario no autenticado
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }


    }
