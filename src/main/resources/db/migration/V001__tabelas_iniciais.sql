CREATE TABLE group_services (
    deleted BIT NOT NULL,
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE orders (
    end TIME(6),
    generator_protect_frequencia TINYINT,
    generator_protect_nivel_tanque_diesel TINYINT,
    generator_protect_tensao TINYINT,
    motor_protect_alta_temperatura TINYINT,
    motor_protect_baixa_pressa_oleo TINYINT,
    motor_protect_ruidos_ou_vibracoes_anormais TINYINT,
    numero INTEGER,
    start TIME(6),
    client_id BIGINT,
    id BIGINT NOT NULL AUTO_INCREMENT,
    scheduled_date DATETIME(6),
    technician_id BIGINT,
    bairro VARCHAR(255),
    cidade VARCHAR(255),
    corrente_consumo_combustivel VARCHAR(255),
    corrente_fator_potencia VARCHAR(255),
    corrente_frequencia VARCHAR(255),
    corrente_potencia VARCHAR(255),
    corrente_potenciae VARCHAR(255),
    corrente_potenciar VARCHAR(255),
    corrente_pre_aquecimento VARCHAR(255),
    corrente_pressa_oleo VARCHAR(255),
    corrente_t_bateriacd VARCHAR(255),
    corrente_t_bateriacl VARCHAR(255),
    corrente_t_bateriaec VARCHAR(255),
    corrente_t_bateriaip VARCHAR(255),
    corrente_t_faser VARCHAR(255),
    corrente_t_fases VARCHAR(255),
    corrente_t_faset VARCHAR(255),
    corrente_temperatura VARCHAR(255),
    corrente_temperatura_ad VARCHAR(255),
    escopo_dos_servicos VARCHAR(255),
    general_observations VARCHAR(255),
    gmg_t_faser VARCHAR(255),
    gmg_t_fases VARCHAR(255),
    gmg_t_faset VARCHAR(255),
    operation_time_assimir_carga VARCHAR(255),
    operation_time_falta_de_rede VARCHAR(255),
    operation_time_resfriamento_gerador VARCHAR(255),
    operation_time_retorno_de_rede VARCHAR(255),
    rede_t_faser VARCHAR(255),
    rede_t_fases VARCHAR(255),
    rede_t_faset VARCHAR(255),
    rua VARCHAR(255),
    status ENUM ('ABERTO', 'ANDAMENTO', 'CANCELADO', 'FINALIZADO'),
    type ENUM ('CORRETIVA', 'PREVENTIVA'),
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1001;

CREATE TABLE service_in_order (
    id BIGINT NOT NULL AUTO_INCREMENT,
    order_id BIGINT,
    service_id BIGINT,
    observation VARCHAR(255),
    url_photo_after VARCHAR(255),
    url_photo_before VARCHAR(255),
    verification_after ENUM ('NOK', 'OK'),
    verification_before ENUM ('NOK', 'OK'),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE services (
    group_id BIGINT,
    id BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255),
    name VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

ALTER TABLE service_in_order
ADD CONSTRAINT FKg3cn38v3helguaweevojmrag5 FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE service_in_order
ADD CONSTRAINT FKow94geeoe6065074ni8i6rl65 FOREIGN KEY (service_id) REFERENCES services (id);

ALTER TABLE services
ADD CONSTRAINT FKoqp539e8k2vhxmgikg8trr33w FOREIGN KEY (group_id) REFERENCES group_services (id);
