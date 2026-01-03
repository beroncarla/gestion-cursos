## Descripción del Proyecto

Gestión de Cursos es un proyecto de práctica orientado al diseño y modelado de un sistema académico, enfocado inicialmente en la definición del modelo de datos relacional y la estructura base de una aplicación Java.

El objetivo principal del proyecto es construir una base sólida sobre la cual implementar posteriormente funcionalidades como operaciones CRUD, autenticación de usuarios, control de acceso por roles, análisis de datos y testing.

## Objetivos del Proyecto

Diseñar un modelo de base de datos relacional consistente y escalable.

Aplicar criterios de integridad referencial y buenas prácticas de SQL.

Definir una estructura de proyecto clara basada en arquitectura en capas.

Preparar el sistema para futuras etapas de desarrollo en Java.

Documentar decisiones técnicas de forma profesional.

## Tecnologías Utilizadas

MySQL — diseño y validación del modelo de datos

SQL — definición de tablas, relaciones y restricciones

Java (planificado) — lógica de negocio

Swing (planificado) — interfaz gráfica

## Arquitectura del Sistema

El proyecto fue concebido utilizando una arquitectura en capas, separando responsabilidades desde etapas tempranas del desarrollo:

UI: Interfaz gráfica (Swing)

Service: Lógica de negocio

DAO: Acceso a datos mediante JDBC

Model: Entidades del dominio

Util: Clases de soporte y utilidades

Esta arquitectura permite un desarrollo incremental, facilita el mantenimiento y deja preparada la aplicación para futuras migraciones de la interfaz gráfica.

## Modelo de Base de Datos

El modelo de datos está compuesto por las siguientes tablas:

usuario: gestiona usuarios del sistema y sus roles.

curso: almacena la información de los cursos disponibles.

inscripcion: representa la relación entre usuarios y cursos.

## Características del modelo

Claves primarias autoincrementales.

Relaciones uno a muchos mediante claves foráneas.

Restricciones de unicidad para evitar duplicados.

Implementación de bajas lógicas (activo, estado).

Control de acceso mediante roles (ADMIN, USER)


## Verificación del modelo de datos

El modelo de datos fue validado mediante consultas con JOINs entre las tablas `usuario`, `curso` e `inscripcion`, permitiendo verificar la correcta definición de las relaciones y la integridad referencial.

Ejemplo de validación:
- usuarios inscriptos en cursos
- estado de la inscripción
- fechas de alta

Estas consultas permitieron confirmar el correcto funcionamiento del esquema antes de avanzar con la implementación de la lógica de aplicación.


## Manejo de Transacciones

El sistema implementa manejo manual de transacciones JDBC a nivel de la capa de servicios (Service Layer),
garantizando la atomicidad de los casos de uso críticos.

### Enfoque adoptado

- Las transacciones se controlan exclusivamente desde el Service Layer.
- Las clases de persistencia (JDBC) **no abren ni cierran conexiones**, ni ejecutan commit/rollback.
- Todas las operaciones que forman parte de un mismo caso de uso comparten la misma conexión.

### Caso de uso transaccional

El método `inscribirUsuario(usuarioId, cursoId)` es transaccional, ya que involucra múltiples operaciones:

- Validación de usuario
- Validación de curso
- Verificación de inscripción previa
- Inserción de la inscripción

Si cualquiera de estas operaciones falla, la transacción se revierte completamente mediante `rollback`.

### Beneficios del diseño

- Consistencia de datos garantizada
- Separación clara de responsabilidades
- Código testeable (los tests utilizan Fakes sin dependencia de JDBC)
- Preparado para escalar a frameworks como Spring Transactional
