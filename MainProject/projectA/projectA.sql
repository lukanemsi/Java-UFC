--ProjectDB--
-- connect 'jdbc:derby://localhost:1527/ProjectDB;create=true';
-- TBC-service
CREATE TABLE users(
    id INTEGER NOT NULL PRIMARY KEY,
    first_name VARCHAR(40),
    last_name VARCHAR(40),
    personal_number VARCHAR(40) UNIQUE,
    balance DECIMAL(15,2)
    );
CREATE TABLE agents(
    id INTEGER NOT NULL PRIMARY KEY, 
    name VARCHAR(40),
    password VARCHAR(40)
    );
CREATE TABLE agent_access(
    row_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    allowed_ip VARCHAR(40),
    agent_id INTEGER,
    FOREIGN KEY (agent_id) REFERENCES agents(id)
);
CREATE TABLE transactions(
    system_transaction_id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    agent_id INTEGER,
    agent_transaction_id VARCHAR(40),
    user_id INTEGER,
    amount DECIMAL(15,3),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(agent_id,agent_transaction_id),
    FOREIGN KEY (agent_id) REFERENCES agents(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- insert Values --
INSERT INTO users VALUES
 (1,'Giorgi','Katsadze','0113108858',0.00), 
 (2,'Luka','Nemsitsveridze','01032614687',0.00), 
 (3,'Lionel','Messi','57123568',0.00), 
 (4, 'Cristiano','Ronaldo','2132346789',0.00), 
 (5,'Guram','Kashia','213780',0.00);

INSERT INTO agents VALUES
(1,'admin','password'),
(2,'admin','password'),
(3,'user','password');

INSERT INTO agent_access(allowed_ip,agent_id) VALUES
('127.0.0.1',1),
('127.0.0.1',2),
('127.0.0.1.0',3);

INSERT INTO transactions(agent_id,agent_transaction_id,user_id,amount) VALUES
(1,'1',1,2000),
(3,'3',3,40000);
