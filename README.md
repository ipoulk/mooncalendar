# MoonCalendar

Interactive web-based calendar with month and year views. For each day, the application displays events, public holidays, and basic astronomical data. Calendar cells are clickable to show detailed information for a selected date.

## Current State
- Spring Boot backend initialized and running.
- MySQL database schema and reference data in progress.
- CSV-to-MySQL data ingestion implemented (Python) for loading calendar, event, category, translation, and rule tables.
- REST API endpoints and JPA entities under development.
- Frontend (React) planned.

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

```bash
python3 -m venv .venv
source .venv/bin/activate
python -m pip install -U pip
pip install -r requirements.txt

