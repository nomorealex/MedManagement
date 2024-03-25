-- Create the database
CREATE DATABASE medfarmaDB;

-- Create the tables
CREATE TABLE utentes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    idade INTEGER,
    genero VARCHAR(10),
    localizacao VARCHAR(100)
);

CREATE TABLE medicamentos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    descricao TEXT
);

CREATE TABLE utentes_medicamentos (
    id SERIAL PRIMARY KEY,
    id_utente INTEGER REFERENCES utentes(id),
    id_medicamento INTEGER REFERENCES medicamentos(id),
    hora TIME,
    quantidade INTEGER
);

-- Insert sample data (optional)
INSERT INTO utentes (nome, idade, genero, localizacao) VALUES ('João', 30, 'Masculino', 'São Paulo');
INSERT INTO utentes (nome, idade, genero, localizacao) VALUES ('Maria', 25, 'Feminino', 'Rio de Janeiro');

INSERT INTO medicamentos (nome, descricao) VALUES ('Paracetamol', 'Analgésico');
INSERT INTO medicamentos (nome, descricao) VALUES ('Dipirona', 'Analgésico e antitérmico');

INSERT INTO utentes_medicamentos (id_utente, id_medicamento, hora, quantidade) VALUES (1, 1, '08:00:00', 1);
INSERT INTO utentes_medicamentos (id_utente, id_medicamento, hora, quantidade) VALUES (2, 1, '10:00:00', 1);
