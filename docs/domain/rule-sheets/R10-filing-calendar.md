# R10 — Filing calendar by ID digits

**Source:** Decreto 2229 de 22-dic-2023 (permanent calendar) · DUT 1625 de 2016
art. 1.6.1.13.2.15

## Values

| Tax year | Filed in | Window |
|---|---|---|
| 2023 | 2024 | 12 Aug – 24 Oct 2024 |
| 2024 | 2025 | 12 Aug – 24 Oct 2025 |
| 2025 | 2026 | 12 Aug – 26 Oct 2026 |

Tax year 2025, by the last two digits of the NIT — all dates in 2026:

| Digits | Due | Digits | Due |
|---|---|---|---|
| 01–02 | 12 Aug | 51–52 | 17 Sep |
| 03–04 | 13 Aug | 53–54 | 18 Sep |
| 05–06 | 14 Aug | 55–56 | 21 Sep |
| 07–08 | 18 Aug | 57–58 | 22 Sep |
| 09–10 | 19 Aug | 59–60 | 23 Sep |
| 11–12 | 20 Aug | 61–62 | 24 Sep |
| 13–14 | 21 Aug | 63–64 | 25 Sep |
| 15–16 | 24 Aug | 65–66 | 28 Sep |
| 17–18 | 25 Aug | 67–68 | 1 Oct |
| 19–20 | 26 Aug | 69–70 | 2 Oct |
| 21–22 | 27 Aug | 71–72 | 5 Oct |
| 23–24 | 28 Aug | 73–74 | 6 Oct |
| 25–26 | 31 Aug | 75–76 | 7 Oct |
| 27–28 | 1 Sep | 77–78 | 8 Oct |
| 29–30 | 2 Sep | 79–80 | 9 Oct |
| 31–32 | 3 Sep | 81–82 | 13 Oct |
| 33–34 | 4 Sep | 83–84 | 14 Oct |
| 35–36 | 7 Sep | 85–86 | 15 Oct |
| 37–38 | 8 Sep | 87–88 | 16 Oct |
| 39–40 | 9 Sep | 89–90 | 19 Oct |
| 41–42 | 10 Sep | 91–92 | 20 Oct |
| 43–44 | 11 Sep | 93–94 | 21 Oct |
| 45–46 | 14 Sep | 95–96 | 22 Oct |
| 47–48 | 15 Sep | 97–98 | 23 Oct |
| 49–50 | 16 Sep | 99–00 | 26 Oct |

## Formula

    window_opens  = 7th business day of August of the filing year
    window_closes = 17th business day of October of the filing year
    due_date      = the business day assigned to the taxpayer's digit pair, distributing the
                    50 pairs in ascending order across the window

## Conditions

- The calendar is permanent. Decreto 2229 de 2023 fixed it in business days *"y en adelante
  para cada año subsiguiente"*, so no new deadline decree is issued each season and the table
  for any future year is produced by the formula above.
- The digits are the last two of the NIT, excluding the verification digit.
- Fifty pairs, 01–02 through 99–00 in ascending order; 99–00 is the last.
- Deriving a year's dates requires the Colombian public-holiday calendar for that filing year.
  It is a parameter of the engine, versioned by year like every other legal value.
- Payment is a single instalment for taxpayers who are not grandes contribuyentes.
- Grandes contribuyentes personas naturales follow a different schedule — three instalments,
  declaration due the 14th business day of April — and are out of scope.
- **Open:** the tax year 2025 window closing on 26 Oct 2026 comes from the business-day count,
  against 24 Oct in the two prior seasons. Confirm against the definitive DIAN calendar.

## Test case

**Given** a taxpayer whose NIT ends in 01, tax year 2025
**When** the calendar resolves → 12 August 2026, the first day of the window

**Given** a taxpayer whose NIT ends in 50, tax year 2025
**When** the calendar resolves → 16 September 2026

**Given** a taxpayer whose NIT ends in 00, tax year 2025
**When** the calendar resolves → 26 October 2026, the last day of the window

**Given** the same taxpayer and a filing year whose holiday calendar is not loaded
**When** the calendar resolves → the engine refuses rather than guessing a date
