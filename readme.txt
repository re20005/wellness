# 📅 Sistema de Gestión de Citas - Wellness

Aplicación web desarrollada con **Spring Boot**  para la gestión de citas entre profesionales y clientes.  
El sistema se ejecuta fácilmente mediante **Docker Compose**, y utiliza **PostgreSQL** como base de datos.

---

## 🚀 Puesta en marcha del proyecto

### 1️⃣ Clonar el repositorio
```bash
git clone https://github.com/re20005/wellness.git
cd wellness
2️⃣ Levantar el entorno con Docker
Ejecuta el siguiente comando para construir y levantar los contenedores:

bash
Copiar código
docker-compose up -d --build
Esto iniciará el backend de la aplicación y el contenedor de base de datos PostgreSQL.

🧩 Configuración de la base de datos
⚠️ Importante: Antes de usar el sistema, debes crear manualmente las tablas en tu gestor de base de datos PostgreSQL.

USERBD: wellnessuser
PASSWORDBD:wellnesspass
Ejecuta las siguientes sentencias SQL:

sql
Copiar código
-- ========================================================
-- TABLA: USUARIO
-- ========================================================
CREATE TABLE usuario (
    id_usuario SERIAL PRIMARY KEY,
    nombre_usuario VARCHAR(100) NOT NULL,
    correo_usuario VARCHAR(100) UNIQUE NOT NULL,
    contrasena_usuario VARCHAR(100) NOT NULL,
    rol_usuario VARCHAR(20) NOT NULL CHECK (rol_usuario IN ('admin', 'profesional', 'cliente')),
    activo BOOLEAN DEFAULT TRUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ========================================================
-- TABLA: PROFESIONAL (1:1 con USUARIO)
-- ========================================================
CREATE TABLE profesional (
    id_profesional SERIAL PRIMARY KEY,
    id_usuario INT UNIQUE NOT NULL REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    especialidad VARCHAR(100),
    telefono VARCHAR(20),
    descripcion TEXT,
    activo BOOLEAN DEFAULT TRUE
);

-- ========================================================
-- TABLA: SERVICIO
-- ========================================================
CREATE TABLE servicio (
    id_servicio SERIAL PRIMARY KEY,
    nombre_servicio VARCHAR(100) NOT NULL,
    descripcion TEXT,
    duracion_minutos INT NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

-- ========================================================
-- TABLA: DISPONIBILIDAD (horarios de los profesionales)
-- ========================================================
CREATE TABLE disponibilidad (
    id_disponibilidad SERIAL PRIMARY KEY,
    id_profesional INT NOT NULL REFERENCES profesional(id_profesional) ON DELETE CASCADE,
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    disponible BOOLEAN DEFAULT TRUE
);

-- ========================================================
-- TABLA: CITA
-- ========================================================
CREATE TABLE cita (
    id_cita SERIAL PRIMARY KEY,
    id_usuario INT NOT NULL REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    id_profesional INT NOT NULL REFERENCES profesional(id_profesional),
    id_servicio INT NOT NULL REFERENCES servicio(id_servicio),
    fecha_cita DATE NOT NULL,
    hora_cita TIME NOT NULL,
    estado VARCHAR(20) NOT NULL CHECK (estado IN ('reservada','reprogramada','cancelada','completada')),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ========================================================
-- TABLA: REPROGRAMACION (historial de cambios en citas)
-- ========================================================
CREATE TABLE reprogramacion (
    id_reprogramacion SERIAL PRIMARY KEY,
    id_cita INT NOT NULL REFERENCES cita(id_cita) ON DELETE CASCADE,
    fecha_anterior DATE,
    hora_anterior TIME,
    fecha_nueva DATE,
    hora_nueva TIME,
    fecha_cambio TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ========================================================
-- TABLA: REPORTE (opcional)
-- ========================================================
CREATE TABLE reporte (
    id_reporte SERIAL PRIMARY KEY,
    tipo_reporte VARCHAR(50),
    fecha_generacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    generado_por INT REFERENCES usuario(id_usuario),
    descripcion TEXT
);
🌐 Acceso a la aplicación
Una vez levantado el entorno, abre en tu navegador:

👉 http://localhost:8080/login

Pasos para ingresar:
Da clic en “Registrarse” para crear una nueva cuenta.

Completa los datos del formulario de registro.

Regresa al login e inicia sesión con tus credenciales.

Una vez dentro, podrás navegar entre las vistas disponibles (inicio, citas, historial, reprogramar cita, etc.).
