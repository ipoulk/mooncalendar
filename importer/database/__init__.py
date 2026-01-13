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

SQL_INSERT_DATE_RULE_TYPE = """
    INSERT INTO date_rule_type(
        id,
        code,
        name,
        name_translation_id,
        description,
        description_translation_id,
        comment,
        comment_translation_id
    )
    VALUES (%s, %s, %s, %s, %s, %s,%s,%s)
    ON DUPLICATE KEY UPDATE
        code = VALUES(code),
        name = VALUES(name),
        name_translation_id = VALUES(name_translation_id),
        description = VALUES(description),
        description_translation_id = VALUES(description_translation_id),
        comment = VALUES(comment),
        comment_translation_id = VALUES(comment_translation_id)
"""

SQL_INSERT_FASTING_TYPE = """
    INSERT INTO fasting_type(
        id,
        code,
        name,
        name_translation_id,
        description,
        description_translation_id,
        comment,
        comment_translation_id,
        meat,
        dairy,
        fish,
        wineoil
    )
    VALUES (%s, %s, %s, %s, %s, %s,%s, %s, %s, %s, %s, %s)
    ON DUPLICATE KEY UPDATE
        code = VALUES(code),
        name = VALUES(name),
        name_translation_id = VALUES(name_translation_id),
        description = VALUES(description),
        description_translation_id = VALUES(description_translation_id),
        comment = VALUES(comment),
        comment_translation_id = VALUES(comment_translation_id),
        meat = VALUES(meat),
        dairy = VALUES(dairy),
        fish = VALUES(fish),
        wineoil = VALUES(wineoil)
"""

SQL_INSERT_EVENT_CATEGORY = """
    INSERT INTO event_category(
        id,
        parent_category_id,
        code,
        name,
        name_translation_id,
        description,
        description_translation_id,
        comment,
        comment_translation_id
    )
    VALUES (%s, %s, %s, %s, %s, %s, %s,%s,%s)
    ON DUPLICATE KEY UPDATE
       parent_category_id = VALUES(parent_category_id),
       code = VALUES(code),
       name = VALUES(name),
       name_translation_id = VALUES(name_translation_id),
       description = VALUES(description),
       description_translation_id = VALUES(description_translation_id),
       comment = VALUES(comment),
       comment_translation_id = VALUES(comment_translation_id)
"""

SQL_INSERT_EVENT= """
    INSERT INTO event(
        id,
        code,
        is_season,
        event_category_id,
        name,
        name_translation_id,
        description,
        description_translation_id,
        comment,
        comment_translation_id
    )
    VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
    ON DUPLICATE KEY UPDATE
        code = VALUES(code),
        is_season = VALUES(is_season),
        event_category_id=VALUES(event_category_id),
        name= VALUES(name),
        name_translation_id=VALUES(name_translation_id),
        description=VALUES(description),
        description_translation_id=VALUES(description_translation_id),
        comment=VALUES(comment),
        comment_translation_id=VALUES(comment_translation_id)
"""

SQL_INSERT_EVENT_DATE_RULE= """
    INSERT INTO event_date_rule(
        id,
        code,
        date_rule_type_id,
        fasting_type_id,
        event_id,
        start_month,
        start_day,
        end_month,
        end_day,
        description,
        description_translation_id,
        comment,
        comment_translation_id
     )
    VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)
    ON DUPLICATE KEY UPDATE
        code=VALUES(code),
        date_rule_type_id=VALUES(date_rule_type_id),
        fasting_type_id=VALUES(fasting_type_id),
        event_id=VALUES(event_id),
        start_month=VALUES(start_month),
        start_day=VALUES(start_day),
        end_month=VALUES(end_month),
        end_day=VALUES(end_day),
        description=VALUES(description),
        description_translation_id=VALUES(description_translation_id),
        comment= VALUES(comment),
        comment_translation_id=VALUES(comment_translation_id)
 """

SQL_INSERT_LANGUAGE= """
    INSERT INTO language(
        id,
        code,
        name,
        name_translation_id,
        description,
        description_translation_id,
        comment,
        comment_translation_id
        )
    VALUES (%s,%s,%s,%s,%s,%s,%s,%s)
    ON DUPLICATE KEY UPDATE
        code=VALUES(code),
        name=VALUES(name),
        name_translation_id=VALUES(name_translation_id),
        description=VALUES(description),
        description_translation_id=VALUES(description_translation_id),
        comment=VALUES(comment),
        comment_translation_id=VALUES(comment_translation_id)         
 """

SQL_INSERT_DOCUMENT_CATEGORY= """
    INSERT INTO document_category(
        id,
        code,
        name,
        name_translation_id,
        description,
        description_translation_id,
        comment,
        comment_translation_id
     )
    VALUES (%s,%s,%s,%s,%s,%s,%s,%s)
    ON DUPLICATE KEY UPDATE
        code=VALUES(code),
        name=VALUES(name),
        name_translation_id=VALUES(name_translation_id),
        description=VALUES(description),
        description_translation_id=VALUES(description_translation_id),
        comment=VALUES(comment),
        comment_translation_id=VALUES(comment_translation_id)
 """
