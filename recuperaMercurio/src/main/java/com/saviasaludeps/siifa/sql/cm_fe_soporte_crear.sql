INSERT INTO cm_fe_soportes(
    cm_fe_rips_cargas_id, cm_facturas_id, gn_empresas_id,
    mae_tipo_soporte_id, mae_tipo_soporte_codigo, mae_tipo_soporte_valor, 
    archivo_nombre, archivo_ruta, archivo, archivo_existe, 
    usuario_crea, terminal_crea, fecha_hora_crea
)VALUES(
    &cm_fe_rips_cargas_id, &cm_facturas_id, &gn_empresas_id,
    &mae_tipo_soporte_id, &mae_tipo_soporte_codigo, &mae_tipo_soporte_valor, 
    &archivo_nombre, &archivo_ruta, &archivo, TRUE, 
    &usuario_crea, &terminal_crea, SYSDATE()
)