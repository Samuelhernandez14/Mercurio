USE prueba1;
GO

-- Eliminar tabla si existe (solo para desarrollo)
-- DROP TABLE IF EXISTS cums1;
-- GO

-- Crear tabla cums1
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[cums1]') AND type in (N'U'))
BEGIN
    CREATE TABLE [dbo].[cums1] (
        [id] INT IDENTITY(1,1) PRIMARY KEY,
        [id_cums] INT NOT NULL UNIQUE,
        [expediente_cum] INT NULL,
        [consecutivo_cum] INT NULL,
        [nombre] NVARCHAR(500) NULL,
        [descripcion] NVARCHAR(MAX) NULL,
        [codigo_atc] NVARCHAR(50) NULL,
        [incluido_pbs] BIT NULL,
        [fecha_creacion] DATETIME DEFAULT GETDATE(),
        [fecha_actualizacion] DATETIME NULL
    );
    
    -- Crear índices
    CREATE INDEX idx_id_cums ON [dbo].[cums1] (id_cums);
    CREATE INDEX idx_expediente_cum ON [dbo].[cums1] (expediente_cum);
    CREATE INDEX idx_consecutivo_cum ON [dbo].[cums1] (consecutivo_cum);
    
    PRINT 'Tabla cums1 creada exitosamente';
END
ELSE
BEGIN
    PRINT 'La tabla cums1 ya existe';
END
GO