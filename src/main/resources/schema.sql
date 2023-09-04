DROP TABLE IF EXISTS tbl_prices;


CREATE TABLE tbl_prices (
                                id BIGINT AUTO_INCREMENT  PRIMARY KEY,
                                brand_id BIGINT,
                                start_date  DATETIME ,
                                end_date  DATETIME ,
                                price_list BIGINT,
                                product_id BIGINT,
                                priority BIGINT,
                                price DOUBLE,
                                curr VARCHAR(250) NOT NULL

);


--BRAND_ID: foreign key de la cadena del grupo (1 = ZARA).
--START_DATE , END_DATE: rango de fechas en el que aplica el precio tarifa indicado.
--PRICE_LIST: Identificador de la tarifa de precios aplicable.
--PRODUCT_ID: Identificador código de producto.
--PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
--PRICE: precio final de venta.
--CURR: iso de la moneda.