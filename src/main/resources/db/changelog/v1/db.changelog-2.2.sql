--changeset merkodanov:1
ALTER TABLE data_source_error_log
ALTER COLUMN message TYPE VARCHAR(700);

ALTER TABLE data_source_error_log
ALTER COLUMN signature_of_method TYPE VARCHAR(700);