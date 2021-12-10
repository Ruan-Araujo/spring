DROP TABLE IF EXISTS endereco, paciente, dentista;
CREATE TABLE IF NOT EXISTS endereco (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(64),
    numero VARCHAR(8),
    bairro VARCHAR(64),
    cidade VARCHAR(64));
CREATE TABLE IF NOT EXISTS paciente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(64),
    sobrenome VARCHAR(8),
    rg VARCHAR(16),
    data_cadastro TIMESTAMP WITHOUT TIME ZONE,
    endereco_id INT);
CREATE TABLE IF NOT EXISTS dentista(
    id INT AUTO_INCREMENT PRIMARY KEY,
    numeroMatricula VARCHAR(10),
    nome VARCHAR(64),
    sobrenome VARCHAR(64));