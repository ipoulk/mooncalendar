# Database module for SQL queries and database utilities

import csv
import mysql.connector as mysql
from pathlib import Path

SQL_INSERT_TRANSLATION = """
    INSERT INTO translation(
        id,
        en,
        de,
        description,
        comment
    )
    VALUES (%s, %s, %s, %s, %s)
    ON DUPLICATE KEY UPDATE
        en = VALUES(en),
        de = VALUES(de),
        description = VALUES(description),
        comment = VALUES(comment)
"""