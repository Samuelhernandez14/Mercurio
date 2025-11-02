SELECT 
    car.id, 
    car.tipo, 
    fac.id AS factura_id,
    car.numero_nota,
    car.estado,
    car.soporte_mercurio,
    emp.id AS empresa_id,
    emp.nit AS empresa_nit
FROM cm_fe_rips_cargas AS car
INNER JOIN gn_empresas AS emp ON emp.id = car.gn_empresas_id
INNER JOIN cm_facturas AS fac ON fac.numero_facturado = car.factura_numero AND fac.nit = emp.nit
WHERE car.soporte_mercurio = &soporte_mercurio_pre
AND DATE(car.fecha_hora_crea) = &fecha_pre


