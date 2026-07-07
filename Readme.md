# 🚀 Rick and Morty Episode Tracker

Aplicación Android que permite visualizar y gestionar los episodios de Rick and Morty,
con autenticación de usuarios, almacenamiento en la nube y estadísticas de visualización.
> Desarrollada en Kotlin como proyecto de portafolio.  
> Implementa consumo de API REST, autenticación Firebase y gestión de episodios vistos.

---

## 📱 Capturas de pantalla

### Tema Claro

|      Pantalla Login      |       Pantalla Registro     |        Lista Episodios         |  Detalle Episodio    | 
|:------------------------:|:--:|:------------------------------:|:---:
| ![Login](Images/img.png) | ![Registro](Images/img_1.png)  | ![Episodios](Images/img_3.png) | ![Detalle](Images/img_12.png)  | 

|       Menú Lateral        |           Ajustes            |           Statics.           |             Splash.             |
|:-------------------------:|:----------------------------:|:----------------------------:|:-------------------------------:|
| ![Menú](Images/img_4.png) | ![Ajustes](Images/img_6.png) | ![Ajustes](Images/img_2.png) | ![Personajes](Images/img_8.png) |


## 🎥 Demostración en video

---
## ✨ Características principales

### 🔐 Autenticación
- Registro de nuevos usuarios con email y contraseña
- Inicio de sesión seguro con Firebase Authentication
- Cierre de sesión desde el menú de ajustes

### 📺 Gestión de episodios
- Lista completa de episodios desde la **API de Rick and Morty**
- **RecyclerView** con diseño optimizado
- Filtro para mostrar **solo episodios vistos**
- Vista detallada con **personajes del episodio** en grid

### 📊 Estadísticas
- Contador de episodios vistos vs totales
- Porcentaje de progreso visual
- Gráfico circular interactivo

### ⚙️ Ajustes
- Cambio de idioma: **Español / Inglés**
- **Modo oscuro / claro** con persistencia en SharedPreferences
- Cierre de sesión

### 📱 Navegación
- Menú lateral (Navigation Drawer)
- Navegación fluida entre fragmentos

---

## 🛠️ Tecnologías utilizadas

- **Kotlin** - Lenguaje de programación
- **Android SDK** - Desarrollo de la app (API 24 - 36)
- **Material Design 3** - Componentes y estilos
- **ViewBinding** - Acceso seguro a vistas
- **RecyclerView** - Listado de episodios y personajes
- **Retrofit** - Consumo de la API de Rick and Morty
- **Firebase Authentication** - Registro e inicio de sesión
- **Firestore** - Base de datos en la nube
- **SharedPreferences** - Persistencia de ajustes (idioma, tema)
- **Coil** - Carga de imágenes de personajes
- **Corrutinas** - Operaciones asíncronas

---


## Instalación

1. Clona el repositorio
2. Abre el proyecto en Android Studio
3. Sincroniza Gradle y ejecuta la app

---
##  Autor

**Abraham C.**  
[GitHub](https://github.com/acdezindev) | [LinkedIn](https://www.linkedin.com/in/AbrahamCdev)

