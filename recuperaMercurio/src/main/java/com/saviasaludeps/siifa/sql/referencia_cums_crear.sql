
MERGE INTO cums1 AS target
USING (SELECT 
    &id_cums AS id_cums,
    &expediente_cum AS expediente_cum,
    &consecutivo_cum AS consecutivo_cum,
    &nombre AS nombre,
    &descripcion AS descripcion,
    &codigo_atc AS codigo_atc,
    &incluido_pbs AS incluido_pbs
) AS source
ON (target.id_cums = source.id_cums)
WHEN MATCHED THEN
    UPDATE SET 
        expediente_cum = source.expediente_cum,
        consecutivo_cum = source.consecutivo_cum,
        nombre = source.nombre,
        descripcion = source.descripcion,
        codigo_atc = source.codigo_atc,
        incluido_pbs = source.incluido_pbs,
        fecha_actualizacion = GETDATE()
WHEN NOT MATCHED THEN
    INSERT (id_cums, expediente_cum, consecutivo_cum, nombre, descripcion, codigo_atc, incluido_pbs, fecha_creacion)
    VALUES (source.id_cums, source.expediente_cum, source.consecutivo_cum, source.nombre, source.descripcion, source.codigo_atc, source.incluido_pbs, GETDATE());