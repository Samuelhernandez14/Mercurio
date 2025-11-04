INSERT INTO cums (
    idCums,
    expedienteCum,
    consecutivoCum,
    nombre,
    descripcion,
    codigoAtc,
    incluidoPbs
)
VALUES (
    &id_cums,
    &expediente_cum,
    &consecutivo_cum,
    &nombre,
    &descripcion,
    &codigo_atc,
    &incluido_pbs
)
ON DUPLICATE KEY UPDATE
    expedienteCum = VALUES(expedienteCum),
    consecutivoCum = VALUES(consecutivoCum),
    nombre = VALUES(nombre),
    descripcion = VALUES(descripcion),
    codigoAtc = VALUES(codigoAtc),
    incluidoPbs = VALUES(incluidoPbs)