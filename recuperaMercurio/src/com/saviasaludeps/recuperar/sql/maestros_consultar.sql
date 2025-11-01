SELECT 
    mae.id, 
    mae.nombre,
    mae.valor
FROM gn_maestros AS mae
WHERE mae.valor = &valor_pre
AND mae.activo = 1
