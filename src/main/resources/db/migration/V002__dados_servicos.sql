INSERT INTO group_services (deleted, name) 
VALUES (b'0', 'MOTOR'), (b'0', 'GERADOR'), (b'0', 'USCA/QTA (FISÍCO/ESTADO)');

INSERT INTO services (group_id, name, description)
VALUES 
(1, 'Motor necessita de lavagem', 'Limpeza completa do motor para garantir desempenho ideal'),
(1, 'Óleo Lubrificante', 'Verificação e substituição do óleo lubrificante do motor'),
(1, 'Filtro de óleo lubrificante', 'Troca do filtro de óleo para melhorar a eficiência do motor'),
(1, 'Sistema de Lubrificação', 'Manutenção do sistema de lubrificação para evitar desgaste'),
(1, 'Radiador', 'Checagem e limpeza do radiador para controle de temperatura'),
(1, 'Combustível', 'Verificação do sistema de combustível para assegurar funcionamento contínuo'),
(1, 'Filtro de óleo combustível', 'Substituição do filtro de combustível para manter a qualidade do combustível'),
(1, 'Filtro Coalecente (Raccor)', 'Verificação do filtro para separação de água do combustível'),
(1, 'Tanque de Combustível', 'Inspeção do tanque de combustível em busca de vazamentos e contaminações'),
(1, 'Sistema de injeção', 'Manutenção do sistema de injeção para garantir performance de combustível'),
(1, 'Bomba dágua', 'Inspeção e manutenção da bomba dágua para resfriamento eficiente'),
(1, 'Filtro de ar', 'Substituição do filtro de ar para melhorar a qualidade do ar admitido'),
(1, 'Abraçadeiras (Condições e Reaperto)', 'Verificação e reaperto de abraçadeiras para evitar vazamentos'),
(1, 'Mangueiras (Condições e Reaperto)', 'Inspeção e reaperto de mangueiras para evitar falhas no sistema'),
(1, 'Funcionamento das turbinas', 'Verificação do funcionamento das turbinas para garantir a eficiência do motor'),
(1, 'Cabeçotes', 'Inspeção dos cabeçotes do motor em busca de desgaste e danos'),
(1, 'Válvulas', 'Manutenção das válvulas para evitar vazamento de gases e perda de potência'),
(1, 'Correias', 'Verificação do estado e tensão das correias do motor'),
(1, 'Suportes do motor (Coxins)', 'Inspeção dos suportes do motor para evitar vibrações excessivas'),
(1, 'Escapamento', 'Verificação do sistema de escapamento em busca de vazamentos e eficiência'),
(1, 'Alternador da Bateria', 'Teste do alternador para garantir o carregamento correto da bateria'),
(1, 'Bateria', 'Inspeção da bateria em busca de falhas ou necessidade de substituição'),
(1, 'Motor Partida', 'Teste do motor de partida para garantir o correto funcionamento ao ligar'),
(1, 'Sistema de Pré Aquecimento', 'Verificação do sistema de pré aquecimento para facilitar partidas a frio'),
(1, 'Sistema de desumidificação', 'Manutenção do sistema de desumidificação para evitar acúmulo de umidade'),
(1, 'Chicote do Motor', 'Inspeção dos cabos e chicote do motor em busca de falhas elétricas'),
(1, 'Sensores do motor', 'Verificação dos sensores do motor para garantir precisão nas leituras');

INSERT INTO services (group_id, name, description)
VALUES 
(2, 'Reaperto da Baseta do Gerador', 'Reaperto da base do gerador para garantir estabilidade e segurança'),
(2, 'Limpeza do Gerador', 'Limpeza geral do gerador para melhorar a eficiência e evitar acúmulo de sujeira'),
(2, 'Isolação do Gerador', 'Verificação da isolação do gerador para evitar curto-circuitos e falhas'),
(2, 'Ponte de Diodo verificada', 'Inspeção da ponte de diodo para garantir o correto funcionamento do gerador'),
(2, 'Fiação do Regulador de Tensão reapertada', 'Reaperto da fiação do regulador de tensão para assegurar conexão adequada'),
(2, 'Coxinho do Regulador de Tensão', 'Inspeção e manutenção dos suportes do regulador de tensão para evitar vibrações'),
(2, 'Verificação do TC', 'Checagem do transformador de corrente (TC) para garantir precisão na medição'),
(2, 'Bobina Auxiliar Gerador / Regulador de Tensão', 'Inspeção da bobina auxiliar e regulador de tensão para manter a estabilidade do gerador');

INSERT INTO services (group_id, name, description)
VALUES 
(3, 'Estado e Limpeza do Painel', 'Verificação do estado físico e limpeza geral do painel de controle'),
(3, 'Identificações do Painel', 'Checagem das identificações no painel para garantir a correta rotulagem dos componentes'),
(3, 'Medidores Painel (Voltímetro, Amperímetro, etc.)', 'Verificação dos medidores do painel, como voltímetro e amperímetro, para garantir a precisão'),
(3, 'Componentes Frontais do Painel (Chaves, Botões, etc.)', 'Inspeção dos componentes frontais do painel, como chaves e botões, para garantir o funcionamento correto'),
(3, 'Componentes Internos do Painel (Bornes, Relés, etc.)', 'Verificação e manutenção dos componentes internos do painel, como bornes e relés'),
(3, 'Conexões Elétricas (Estado das Ligações de Comando)', 'Inspeção das conexões elétricas para garantir a integridade das ligações de comando'),
(3, 'Proteções de Surto/Aterramento', 'Verificação das proteções contra surtos e do aterramento para assegurar a segurança do sistema'),
(3, 'Carregador de Bateria', 'Checagem do carregador de bateria para garantir o funcionamento adequado'),
(3, 'Regulador de Tensão', 'Inspeção do regulador de tensão para assegurar o controle correto da voltagem'),
(3, 'Regulador de Velocidade', 'Verificação do regulador de velocidade para garantir a estabilidade do sistema'),
(3, 'Contator/Disjuntor', 'Inspeção dos contactores e disjuntores para garantir a proteção elétrica do sistema'),
(3, 'Bobinas do Disjuntor', 'Checagem das bobinas do disjuntor para assegurar que estejam funcionando corretamente'),
(3, 'USCA (Aparência, Conexões, Bornes, etc.)', 'Verificação da aparência, conexões e bornes da USCA para garantir o bom estado e funcionamento.');
