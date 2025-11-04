-- Script para crear la tabla cums en MySQL
USE cuentas_medicas;

-- Eliminar tabla si existe (solo para desarrollo)
-- DROP TABLE IF EXISTS cums;

-- Crear tabla cums
CREATE TABLE IF NOT EXISTS cums (
    idCums INT PRIMARY KEY,
    expedienteCum INT NOT NULL,
    consecutivoCum INT NOT NULL,
    nombre VARCHAR(500) NOT NULL,
    descripcion VARCHAR(500),
    codigoAtc VARCHAR(20),
    incluidoPbs BOOLEAN NOT NULL DEFAULT false,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Crear índices para mejorar el rendimiento
CREATE INDEX idx_expediente_cum ON cums(expedienteCum);
CREATE INDEX idx_consecutivo_cum ON cums(consecutivoCum);
CREATE INDEX idx_nombre ON cums(nombre(255));
CREATE INDEX idx_codigo_atc ON cums(codigoAtc);

-- Verificar la creación
SELECT 'Tabla cums creada/verificada exitosamente' AS resultado;