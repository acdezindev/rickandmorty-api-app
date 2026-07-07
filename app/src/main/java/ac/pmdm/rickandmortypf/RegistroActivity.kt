package ac.pmdm.rickandmortypf

import ac.pmdm.rickandmortypf.databinding.ActivityRegistroBinding
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * Activity de registro de nuevos usuarios
 * Permite crear una cuenta con email y contraseña en Firebase Authentication
 */
class RegistroActivity : AppCompatActivity() {

    // ViewBinding para acceder a las vistas del layout
    private lateinit var binding: ActivityRegistroBinding

    // Instancia de Firebase Authentication
    private lateinit var auth: FirebaseAuth

    // Usuario autenticado actualmente (puede ser null)
    private var user: FirebaseUser? = null // ( puede ser null cuando no hay usuario autenticado)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Aplicar el idioma guardado en preferencias
        Idioma.ponerIdioma(this)

        // Configurar EdgeToEdge para la pantalla
        enableEdgeToEdge()

        // Inflar el layout con ViewBinding
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el padding para EdgeToEdge
        ViewCompat.setOnApplyWindowInsetsListener(binding.mainregistro) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // Botón para volver a la pantalla de login
        binding.btnVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Botón para crear una nueva cuenta
        binding.btnRegistrarse.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPass.text.toString()
            createAccount(email, password)

        }

    }

    /**
     * Crea una nueva cuenta de usuario en Firebase Authentication
     * @param email Correo electrónico del usuario
     * @param password Contraseña del usuario
     */
    private fun createAccount(email: String, password: String) {
        // Validar que los campos no estén vacíos
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Crear usuario en Firebase
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    user = auth.currentUser

                    Toast.makeText(this, "Cuenta creada: ${user?.email}", Toast.LENGTH_SHORT).show()
                    updateUI(user)
                } else {

                    Toast.makeText(this, "Error al crear la cuenta", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }


    /**
     * Actualiza la UI según el estado de autenticación del usuario
     * @param user Usuario autenticado o null si no hay sesión
     */

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // Usuario registrado correctamente
            startActivity(Intent(this, PrincipalActivity::class.java))
            finish()
        } else {
            // Error al crear la cuenta
            Toast.makeText(this, "Error al crear la cuenta", Toast.LENGTH_SHORT).show()
        }
    }

}

