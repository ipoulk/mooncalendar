# MoonCalendar

Interactive web-based calendar with month and year views. For each day, the application displays events, public holidays, and basic astronomical data. Calendar cells are clickable to show detailed information for a selected date.

## Current State
- Spring Boot backend initialized and running.
- MySQL database schema and reference data ingestion implemented.
- CSV-to-MySQL data ingestion implemented (Python) for loading calendar, event, category, translation, and rule tables.
- REST API endpoints and JPA entities under development.
- Frontend (React) planned.

## Repository Layout / Data Model
- DB schema is store in /database/
- Seed CSVs are stored in /database/production/
- Data model was designed upfront (see ER diagram in /docs/)

## Tech Stack
- **Backend:** Java 17, Spring Boot (REST APIs)
- **Database:** MySQL
- **Frontend:** React, HTML/CSS/JavaScript (planned)
- **Tooling:** Git, Maven, IntelliJ IDEA
- **Data Import:** Python (CSV → MySQL)

## Features (Planned / In Progress)
- Month and year navigation
- Daily events and public holidays
- Astronomical data per date (moon-related information)
- Clickable date cells with detailed view
- REST endpoints for date-based queries (e.g. `/api/days/{date}`)

## Data Import (Python)

The project includes a Python-based ingestion tool (`importer.py`) that loads CSV datasets into MySQL.  
It handles:

- Input path and encoding validation  
- NULL and type normalization  
- Foreign key integrity checks with diagnostic output  
- Modular loaders per table (events, categories, translations, rules, languages, etc.)  
- Environment-based configuration using `.env`

### Setup

Run these steps to create a venv and install dependencies:

```bash
python3 -m venv .venv
source .venv/bin/activate
python -m pip install -U pip
pip install -r requirements.txt
```

### Configuration

Create an environment file (for example `.env`) or export these variables. Example keys used by the importer:

```
CSV_ROOT_PATH=../
EVENT_CSV=
EVENT_CATEGORY_CSV=
TRANSLATION_CSV=
DATE_RULE_TYPE_CSV=
EVENT_DATE_RULE_CSV=
FASTING_TYPE_CSV=
EVENT_HAS_IMAGE_CSV=
EVENT_HAS_DOCUMENT_CSV=
DOCUMENT_CSV=
DOCUMENT_HAS_IMAGE_CSV=
DOCUMENT_CATEGORY_CSV=
IMAGE_CSV=
LANGUAGE_CSV=

DB_HOST=
DB_PORT=
DB_USER=
DB_PASSWORD=
DB_NAME=
```

### Run Import

Execute the importer from the project root:

```bash
python3 importer.py
```

## Roadmap

- Finalize relational schema and implement JPA entities
- Implement core REST endpoints (GET by date, list by month/year)
- Add validation and error handling
- Add unit/integration tests
- Build initial React UI and connect to the REST API

