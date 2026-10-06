# Spring Boot DevSecOps Lab

Aplicacion deliberadamente vulnerable para prácticas controladas de SAST, SCA,
secret scanning, análisis de contenedores y DAST.

> **Advertencia:** ejecutar únicamente en `localhost` o en una red de laboratorio
> aislada. No desplegar en Internet ni reutilizar credenciales reales.

## Requisitos

- JDK 21
- Maven 3.9+
- Docker, opcional
- Docker Desktop, para los análisis locales reproducibles

## Iniciar la aplicación

```bash
mvn clean verify
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`.

## Endpoints del laboratorio

```text
GET  /api/products/search?name=Laptop
POST /api/comments/preview
GET  /api/admin/users/1
POST /api/auth/login
```

Ejemplo para la vista previa:

```bash
curl -X POST http://localhost:8080/api/comments/preview \
  -H "Content-Type: application/json" \
  -d '{"comment":"Comentario de prueba"}'
```

Ejemplo de autenticación:

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"usuario","password":"prueba"}'
```

## Reportes locales de seguridad con Docker

Los reportes locales se generan en `reports/local/`. La carpeta se conserva en el
repositorio mediante `.gitkeep`, pero los resultados generados no se versionan.

En Windows PowerShell:

```powershell
.\scripts\run-security-local.ps1
```

El script ejecuta los tres análisis en Docker y termina con error si alguno
detecta hallazgos por encima del umbral configurado:

- Semgrep usa `auto` y el ruleset local `.semgrep.yml`, y genera JSON y SARIF.
- OWASP Dependency-Check genera HTML y JSON con fallo desde CVSS 7.
- SpotBugs compila el proyecto y deja `spotbugs.xml`.

También se puede ejecutar un servicio individual:

```powershell
New-Item -ItemType Directory -Force reports/local
docker compose -f docker-compose.security.yml run --rm semgrep
docker compose -f docker-compose.security.yml run --rm dependency-check
docker compose -f docker-compose.security.yml run --rm spotbugs
```

El docente dispone de `docs/GUIA-DOCENTE.md`, que contiene el catálogo de
hallazgos y las pruebas sugeridas. Se recomienda entregar inicialmente a los
estudiantes el resto del repositorio sin dicho documento.

## Nota personales