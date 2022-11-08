-- ProjectBDB
-- connect 'jdbc:derby://localhost:1527/ProjectBDB;create=true';
-- TBC-service-B
CREATE TABLE payments(
	payment_id INTEGER NOT NULL PRIMARY KEY,
	user_id INTEGER,
	amount DECIMAL(15,3),
	transaction_id BIGINT,
	request_date TIMESTAMP,
	response_date TIMESTAMP,
	code SMALLINT,
	status SMALLINT,
	CONSTRAINT check_status CHECK (status BETWEEN 0 AND 2)
);
