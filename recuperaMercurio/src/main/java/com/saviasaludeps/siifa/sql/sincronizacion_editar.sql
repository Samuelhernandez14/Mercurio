UPDATE cm_fe_mercurio_sincronizaciones
SET
    fecha_hora_fin = &fecha_hora_fin,
    duracion = &duracion,
    estado = &estado,
    descripcion = &descripcion,
    registros_consultados = &registros_consultados,
    registros_actualizados = &registros_actualizados
WHERE id = &id_pre