SELECT *
FROM cm_fe_mercurio_sincronizaciones
WHERE estado = 1
ORDER BY periodo DESC
LIMIT 1