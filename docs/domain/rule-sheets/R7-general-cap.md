# R7 — General 40% / 1.340 UVT cap on the general schedule

**Source:** art. 336 num. 3 E.T. (Ley 2277 de 2022 art. 7) · base per DUT 1625 de 2016
art. 1.2.1.20.4 · DIAN Res. 000120 de 2024

## Values

| Tax year | 1.340 UVT |
|---|---|
| 2023 | $56.832.000 |
| 2024 | $63.067.000 |
| 2025 | $66.731.000 |

## Formula

    base           = general_schedule_income − incrngo
    cap            = min(0.40 × base, 1340 × uvt(tax_year))
    allowed_inside = min(total_inside, cap)
    lost_to_cap    = total_inside − allowed_inside

## Conditions

- Applies to the general schedule only.
- INCRNGO is subtracted before the base, so it is never subject to the cap.
- Individual caps (R4, R5, R6) resolve first; the global cap applies to their capped sum.
- **Inside the cap:** 25% exempt labor income (R4), prepaid health, mortgage interest,
  AFC / voluntary pension, GMF (R6), art. 387 dependents (R5), severance pay (R15), and any other
  exemption or deduction of the schedule, except those expressly excluded by law: the 72 UVT per
  dependent deduction (art. 336 num. 3) and the 1% electronic-invoice deduction (art. 336 num. 5).
- **Outside the cap:** 72 UVT per dependent under art. 336 num. 3 (R5), the 1% electronic-invoice
  deduction under art. 336 num. 5, and pension exempt income of the pensions schedule.
- The ceiling is always 1.340 × the year's UVT. A secondary source printed the TY2023 figure as
  $53.832.000; the arithmetic gives $56.832.000.

## Test case

**Given** a base of $100.000.000 and $52.000.000 of items inside the cap, tax year 2025
**When** the cap is applied → 40% ($40.000.000) governs, `allowed_inside` = $40.000.000 and
`lost_to_cap` = $12.000.000

**Given** a base of $200.000.000 and $70.000.000 of items inside the cap, tax year 2025
**When** the cap is applied → the 1.340 UVT ceiling ($66.731.000) governs, `allowed_inside` =
$66.731.000 and `lost_to_cap` = $3.269.000

**Given** the first case plus one dependent claimed under art. 336 num. 3 ($3.586.000 for 2025)
**When** the cap is applied → `allowed_inside` stays $40.000.000 and the $3.586.000 is deducted in
full, outside the cap
