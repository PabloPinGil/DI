import androidx.appcompat.app.AppCompatActivity;


public class DashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Inicializar referencia a Firebase Realtime Database
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("users");

        // Listener para leer datos de Firebase
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                StringBuilder usersData = new StringBuilder();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String userName = userSnapshot.child("name").getValue(String.class);
                    usersData.append("Nombre: ").append(userName).append("\n");
                }
                // Mostrar los datos en un TextView
                TextView dataTextView = findViewById(R.id.dataTextView);
                dataTextView.setText(usersData.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Firebase", "Error al leer datos", databaseError.toException());
            }
        };

        // Añadir el listener a la referencia de la base de datos
        databaseRef.addListenerForSingleValueEvent(userListener);

        // Configurar botón de Logout
        findViewById(R.id.logoutButton).setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
            finish();
        });
    }
}